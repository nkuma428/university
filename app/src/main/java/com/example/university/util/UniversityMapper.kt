package com.example.university.util

import com.example.university.data.remote.University
import com.example.university.data.local.UniversityEntity

object UniversityMapper {

    fun toEntity(dto: University): UniversityEntity {
        return UniversityEntity(
            alphaTwoCode = dto.alphaTwoCode,
            webPages = dto.webPages,
            country = dto.country,
            domains = dto.domains,
            name = dto.name,
            stateProvince = dto.stateProvince
        )
    }

    fun toUniversity(entity: UniversityEntity): University {
        return University(
            alphaTwoCode = entity.alphaTwoCode,
            webPages = entity.webPages,
            country = entity.country,
            domains = entity.domains,
            name = entity.name,
            stateProvince = entity.stateProvince
        )
    }
}
