package com.programmationmb.projet_mobile.data.repository

import com.programmationmb.projet_mobile.data.local.dao.ProductDao
import com.programmationmb.projet_mobile.data.local.entities.ProductEntity
import com.programmationmb.projet_mobile.data.model.Book
import com.programmationmb.projet_mobile.data.remote.FakeStoreApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProductRepository(
    private val api: FakeStoreApi,
    private val productDao: ProductDao
) {

    val productsFlow: Flow<List<Book>> = productDao.getProducts().map { list ->
        list.map { it.toDomain() }
    }

    suspend fun refreshProducts() = withContext(Dispatchers.IO) {
        val remote = api.getProducts()
        val entities = remote.map { it.toEntity() }
        productDao.upsertAll(entities)
    }

    suspend fun getBookById(id: Int): Book? = withContext(Dispatchers.IO) {
        val cached = productDao.getProductById(id).firstOrNull()
        if (cached != null) return@withContext cached.toDomain()

        val remote = api.getProduct(id)
        val entity: ProductEntity = remote.toEntity()
        productDao.upsert(entity)
        entity.toDomain()
    }
}
