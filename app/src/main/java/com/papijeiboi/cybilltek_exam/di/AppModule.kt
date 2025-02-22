package com.papijeiboi.cybilltek_exam.di

import android.app.Application
import androidx.room.Room
import com.papijeiboi.cybilltek_exam.api.PersonApi
import com.papijeiboi.cybilltek_exam.data.PersonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(PersonApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providePersonApi(retrofit: Retrofit): PersonApi = retrofit.create(PersonApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PersonDatabase =
        Room.databaseBuilder(app, PersonDatabase::class.java, "person_database")
            .build()
}