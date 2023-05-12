package dev.mrtroy.schools

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun builder(baseUrl: HttpUrl): Retrofit.Builder = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .callFactory(
            OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .callTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build()
        )
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
}

@Module
@InstallIn(SingletonComponent::class)
object SquareInterviewModule {
    @Provides
    fun baseUrl(): HttpUrl = "https://catalogue.data.govt.nz/api/3/".toHttpUrl()
}
