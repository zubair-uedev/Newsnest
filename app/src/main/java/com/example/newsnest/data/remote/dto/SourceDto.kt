package com.example.newsnest.data.remote.dto

import com.example.newsnest.domain.model.Source

data class SourceDto(
    val id: String?,
    val name: String
)

fun SourceDto.toDomain(): Source {
    return Source(
        id = this.id,
        name = this.name
    )
}

fun Source.toData(): SourceDto {
    return SourceDto(
        id = this.id,
        name = this.name
    )
}

