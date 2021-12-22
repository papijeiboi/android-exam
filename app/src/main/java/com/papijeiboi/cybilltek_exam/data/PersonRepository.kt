package com.papijeiboi.cybilltek_exam.data

import androidx.room.withTransaction
import com.papijeiboi.cybilltek_exam.api.PersonApi
import com.papijeiboi.cybilltek_exam.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val api: PersonApi,
    private val db: PersonDatabase
) {

    private val personDao = db.personDao()

    fun getPerson() = networkBoundResource(
        query = {
            personDao.getAllPersons()
        },
        fetch = {
            delay(2000)
            api.getPersons()
        },
        savedFetchResult = { person ->
            db.withTransaction {
                personDao.deleteAllPerson()
                personDao.insertPerson(person.results)
            }

        }
    )
}