package com.example.demo.model

import javax.persistence.Entity
import com.example.demo.crud.CrudDto
@Entity
class Notice(
    var title: String,
    var content: String
): BaseEntity()


data class CreateNoticeRequestDto(
    var title: String,
    var content: String
) : CrudDto.CreateRequestDto<Notice> {
    override fun toEntity(): Notice {
        return Notice(
            title = title,
            content = content
        )
    }
}

data class UpdateNoticeRequestDto(
    var title: String,
    var content: String
) : CrudDto.UpdateRequestDto<Notice> {
    override fun applyTo(entity: Notice): Notice {
        return entity.apply {
            this.title = title
            this.content = content
        }
    }
}



object NoticeResponseDto : CrudDto.CrudResponseDto<Notice> {
    data class NoticeIndexResponse(
        var id: Long,
        var title: String,
        var createdAt: String,
        var updatedAt: String
    )

    override fun listFromEntity(entities: List<Notice>): Any {
        return entities.map {
            NoticeIndexResponse(
                id = it.id!!,
                title = it.title,
                createdAt = it.createdAt.toString(),
                updatedAt = it.updatedAt.toString()
            )
        }
    }

    data class NoticeShowResponse(
        var id: Long,
        var title: String,
        var content: String,
        var createdAt: String,
    )

    override fun fromEntity(entity: Notice): Any {
        return NoticeShowResponse(
            id = entity.id!!,
            title = entity.title,
            content = entity.content,
            createdAt = entity.createdAt.toString(),
        )
    }
}

