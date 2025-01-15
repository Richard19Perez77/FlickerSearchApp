package com.rperez.flickersearchapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.rperez.flickersearchapp.data.api.FlickrApiService
import com.rperez.flickersearchapp.data.repository.FlickrRepository

/**
 * Hilt module for providing application-level dependencies.
 *
 * This module is installed in the [SingletonComponent], meaning the provided dependencies
 * will have a singleton scope and live throughout the lifecycle of the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of [Retrofit].
     *
     * The [Retrofit] instance is configured with the base URL for Flickr's public API
     * and a Gson converter for JSON serialization and deserialization.
     *
     * @return A configured [Retrofit] instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.flickr.com/") // Base URL for Flickr's API
        .addConverterFactory(GsonConverterFactory.create()) // Gson converter for JSON parsing
        .build()

    /**
     * Provides a singleton instance of [FlickrApiService].
     *
     * @param retrofit The [Retrofit] instance used to create the service.
     * @return A [FlickrApiService] instance for making API calls.
     */
    @Provides
    @Singleton
    fun provideFlickrApiService(retrofit: Retrofit): FlickrApiService =
        retrofit.create(FlickrApiService::class.java)

    /**
     * Provides a singleton instance of [FlickrRepository].
     *
     * @param api The [FlickrApiService] instance used by the repository to fetch data.
     * @return A [FlickrRepository] instance for accessing Flickr's public API data.
     */
    @Provides
    @Singleton
    fun provideFlickrRepository(api: FlickrApiService): FlickrRepository =
        FlickrRepository(api)
}
