package dev.mrtroy.schools

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface SchoolApi {
    @GET("action/datastore_search")
    suspend fun schools(
        @Query("resource_id") resourceId: String,
        @Query("limit") limit: Int
    ): SchoolResponse
}

@JsonClass(generateAdapter = true)
data class SchoolResponse(
    @Json(name = "result") val result: Result,
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "records") val schools: List<School>,
    ) {
        @JsonClass(generateAdapter = true)
        data class School(
            @Json(name = "School_Id") val schoolId: Int,
            @Json(name = "Org_Name") val schoolName: String,
            @Json(name = "Telephone") val phoneNumber: String?,
            @Json(name = "Email") val emailAddress: String?,
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object SchoolApiModule {
    @Provides
    @Singleton
    fun api(builder: Retrofit.Builder): SchoolApi = builder.build().create()
}
