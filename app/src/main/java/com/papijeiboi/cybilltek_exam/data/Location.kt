package com.papijeiboi.cybilltek_exam.data

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Location(
    @Embedded
    @SerializedName("street")
    val street: Street,
    @SerializedName("city")
    val city: String
)