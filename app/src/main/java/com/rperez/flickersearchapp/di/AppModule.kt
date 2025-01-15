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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.flickr.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideFlickrApiService(retrofit: Retrofit): FlickrApiService = retrofit.create(FlickrApiService::class.java)

    @Provides
    @Singleton
    fun provideFlickrRepository(api: FlickrApiService): FlickrRepository = FlickrRepository(api)
}
