package com.example.demo.crud

object CrudDto {
    interface CreateRequestDto<T> {
        fun toEntity(): T
    }



    interface UpdateRequestDto<T> {
        fun applyTo(entity: T): T
    }


    interface CrudResponseDto<T> {
        fun listFromEntity(entities: List<T>): Any
        fun fromEntity(entity: T): Any
    }
}