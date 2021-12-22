package com.papijeiboi.cybilltek_exam.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "person")
data class Person(
    @Embedded
    @SerializedName("name")
    val name: Name,
    @Embedded
    @SerializedName("location")
    val location: Location,
    @PrimaryKey
    @SerializedName("email")
    val email: String,
    @Embedded
    @SerializedName("dob")
    val dob: Dob,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("cell")
    val cell: String,
    @Embedded
    @SerializedName("picture")
    val picture: Picture
)