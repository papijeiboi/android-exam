package com.papijeiboi.cybilltek_exam.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.papijeiboi.cybilltek_exam.adapter.PersonAdapter
import com.papijeiboi.cybilltek_exam.databinding.ActivityMainBinding
import com.papijeiboi.cybilltek_exam.util.Resource
import com.papijeiboi.cybilltek_exam.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personAdapter = PersonAdapter(PersonAdapter.OnClickListener { person ->
            val mGson = GsonBuilder()
                .setLenient()
                .create()
            mGson.toJson(person)

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_PERSON_INFORMATION, mGson.toJson(person))
            startActivity(intent)
        })

        binding.apply {
            rvPerson.apply {
                adapter = personAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)

            }

            viewModel.person.observe(this@MainActivity) { result ->
                personAdapter.submitList(result.data)

                pbPerson.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                tvError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                tvError.text = result.error?.localizedMessage
            }
        }
    }
}