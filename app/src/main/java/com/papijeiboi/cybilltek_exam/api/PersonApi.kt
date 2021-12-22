package com.papijeiboi.cybilltek_exam.api

import com.papijeiboi.cybilltek_exam.data.Person
import com.papijeiboi.cybilltek_exam.data.PersonResults
import retrofit2.http.GET

interface PersonApi {

    companion object{
        const val BASE_URL = "https://randomuser.me/api/"
    }

    @GET("?results=50")
    suspend fun getPersons(): PersonResults
}