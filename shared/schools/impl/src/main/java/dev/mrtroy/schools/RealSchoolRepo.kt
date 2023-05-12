package dev.mrtroy.schools

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealSchoolRepo @Inject constructor(private val schoolApi: SchoolApi) : SchoolRepo {

    override suspend fun schools(limit: Int): Flow<List<School>> = flow {
        emit(
            schoolApi
                .schools(
                    resourceId = "20b7c271-fd5a-4c9e-869b-481a0e2453cd",
                    limit = limit
                )
                .result
                .schools
                .map { it.toSchool() }
        )
    }
}

private fun SchoolResponse.Result.School.toSchool() = School(
    schoolId = schoolId,
    schoolName = schoolName,
    phoneNumber = phoneNumber,
    emailAddress = emailAddress,
)

@Module
@InstallIn(SingletonComponent::class)
interface SchoolRepoModule {
    @Binds
    fun repo(repo: RealSchoolRepo): SchoolRepo
}
