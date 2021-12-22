package com.papijeiboi.cybilltek_exam.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.papijeiboi.cybilltek_exam.data.Person
import com.papijeiboi.cybilltek_exam.databinding.ActivityDetailBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val EXTRA_PERSON_INFORMATION = "EXTRA_PERSON_INFORMATION"

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listType = object : TypeToken<Person>() {}.type
        val personInfo: Person =
            Gson().fromJson<Person>(
                intent.getStringExtra(EXTRA_PERSON_INFORMATION),
                listType
            )

        binding.apply {
            setSupportActionBar(toolbarLayout)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            "${personInfo.name.first} ${personInfo.name.last}".let {
                collapsingToolbarLayout.title = it
            }

            Glide.with(ivPersonPicture)
                .load(personInfo.picture.large)
                .into(ivPersonPicture)

            tvBirthday.text = changeBirthdayFormat(personInfo.dob.date)
            tvAge.text = personInfo.dob.age.toString()
            tvEmail.text = personInfo.email
            tvMobileNumber.text = personInfo.cell
            tvAddress.text =
                "${personInfo.location.street.number} ${personInfo.location.street.name}, ${personInfo.location.city}"

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun changeBirthdayFormat(birthdate: String): String {
        val outputFormat: DateFormat = SimpleDateFormat("MMM d y", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)


        val date: Date = inputFormat.parse(birthdate)
        val outputText: String = outputFormat.format(date)

        return outputText

    }
}