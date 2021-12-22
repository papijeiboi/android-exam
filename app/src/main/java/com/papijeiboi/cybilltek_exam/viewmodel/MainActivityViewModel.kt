package com.papijeiboi.cybilltek_exam.viewmodel

import androidx.lifecycle.*
import com.papijeiboi.cybilltek_exam.api.PersonApi
import com.papijeiboi.cybilltek_exam.data.Person
import com.papijeiboi.cybilltek_exam.data.PersonRepository
import com.papijeiboi.cybilltek_exam.data.PersonResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(repository: PersonRepository) : ViewModel() {
    val person = repository.getPerson().asLiveData()
//    private val personLiveData = MutableLiveData<PersonResults>()
//    val persons: LiveData<PersonResults> = personLiveData
//
//    init {
//        viewModelScope.launch {
//            val persons = api.getPersons()
//            personLiveData.value = persons
//        }
//    }

}