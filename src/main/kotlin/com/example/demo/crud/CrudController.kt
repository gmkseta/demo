package com.example.demo.crud


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

abstract class CrudController<T, C: CrudDto.CreateRequestDto<T>, U: CrudDto.UpdateRequestDto<T>>(
    private val service: CrudService<T>,
    private val responseDto: CrudDto.CrudResponseDto<T>
) {

    @GetMapping
    fun index(): ResponseEntity<Any> {
        val entities = service.findAll()

        return ResponseEntity.ok(responseDto.listFromEntity(entities))
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): ResponseEntity<Any> {
        val entity = service.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id $id not found")
        return ResponseEntity.ok(responseDto.fromEntity(entity))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: C): T {
        return service.create(request.toEntity())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: U): T {
        val entity = service.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id $id not found")

        return service.update(request.applyTo(entity))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

}



