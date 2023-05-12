package dev.mrtroy.schools

import kotlinx.coroutines.flow.Flow

interface SchoolRepo {
    suspend fun schools(limit: Int): Flow<List<School>>
}

data class School(
    val schoolId: Int,
    val schoolName: String,
    val phoneNumber: String?,
    val emailAddress: String?,
)
