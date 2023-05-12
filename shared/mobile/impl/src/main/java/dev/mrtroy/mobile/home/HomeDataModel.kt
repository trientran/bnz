package dev.mrtroy.mobile.home

import dev.mrtroy.schools.School
import kotlinx.collections.immutable.ImmutableList
import javax.annotation.concurrent.Immutable

data class HomeState(
    val schools: ImmutableList<School>? = null,
    val isLoading: Boolean = true,
    val error: Error? = null
) {
    @Immutable
    data class School(
        val schoolId: Int,
        val schoolName: String,
        val phoneNumber: String?,
        val emailAddress: String?,
    )

    @Immutable
    data class Error(val exception: Exception)
}

internal fun School.toSchool() = HomeState.School(
    schoolId = schoolId,
    schoolName = schoolName,
    phoneNumber = phoneNumber,
    emailAddress = emailAddress,
)
