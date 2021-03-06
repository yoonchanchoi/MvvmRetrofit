package com.example.mvvmretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.CovidApi.Companion.TOKEN
import com.example.mvvmretrofit.databinding.ActivityMainBinding
import com.example.mvvmretrofit.repository.CovidRepository
import com.example.mvvmretrofit.viewmodel.CovidViewModel
import com.example.mvvmretrofit.viewmodel.CovidViewModelFactory

//메인 액티비티
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var covidViewModel: CovidViewModel
    private val covidAdapter: CovidViewAdapter by lazy {
        CovidViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = CovidRepository()
        val viewModelFactory = CovidViewModelFactory(repository)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        covidViewModel = ViewModelProvider(this, viewModelFactory).get(CovidViewModel::class.java)
        covidViewModel.getAll(TOKEN)
        initAdapter()
        observeCovidList()
    }

    private fun initAdapter() {
        binding.re.adapter = covidAdapter
    }

    private fun observeCovidList() {
        covidViewModel.liveCovidVo.observe(this, Observer { covidList ->
            covidAdapter.setList(covidList)
        })
    }
}