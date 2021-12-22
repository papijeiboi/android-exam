package com.papijeiboi.cybilltek_exam.data

import com.google.gson.annotations.SerializedName

class PersonResults(
    @SerializedName("results")
    val results: List<Person>
    )