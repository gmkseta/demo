package com.example.demo.crud

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository


abstract class AbstractCrudService<T>(
    private val repository: JpaRepository<T, Long>
): CrudService<T> {
    override fun create(entity: T): T = repository.save(entity)

    override fun findById(id: Long): T? = repository.findById(id).orElse(null)

    override fun update(entity: T): T = repository.save(entity)

    override fun delete(id: Long) = repository.deleteById(id)

    override fun findAll(): List<T> = repository.findAll().toList()

    override fun findByPage(pageNumber: Int, pageSize: Int): Page<T> {
        val pageRequest = PageRequest.of(pageNumber, pageSize)

        return repository.findAll(pageRequest)
    }
}

interface CrudService<T> {
    fun create(entity: T): T
    fun findById(id: Long): T?
    fun update(entity: T): T
    fun delete(id: Long)
    fun findAll(): List<T>
    fun findByPage(pageNumber: Int, pageSize: Int): Page<T>
}