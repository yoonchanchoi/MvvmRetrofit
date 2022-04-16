package com.example.mvvmretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.CovidApi.Companion.TOKEN
import com.example.mvvmretrofit.databinding.ActivityMainBinding
import com.example.mvvmretrofit.viewmodel.CovidViewModel

//메인 액티비티
class MainActivity : AppCompatActivity() {

    private lateinit var covidViewAdapter: CovidViewAdapter
//    private lateinit var covidList: MutableList<CovidVO>

    lateinit var binding: ActivityMainBinding
    lateinit var covidViewModel: CovidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        covidViewModel = ViewModelProvider(this).get(CovidViewModel::class.java)
        covidViewModel.liveStateVo.observe(this, Observer {
            covidViewModel.getAll(TOKEN)
        })


    }



//        covidService
//            .getDocument(CovidApi.TOKEN)
//            .enqueue(object : Callback<StateVO>{
//                override fun onFailure(call: Call<StateVO>, t: Throwable) {
//                    Log.d("실패실패..", "${t.message.toString()}")
//                }
//
//                override fun onResponse(call: Call<StateVO>, response: Response<StateVO>) {
//                    if(response.isSuccessful){
//                        Log.d("성공성공!", response!!.body().toString())
//
//                        covidList = mutableListOf<CovidVO>(
//                            response.body()!!.korea,
//                            response.body()!!.seoul,
//                            response.body()!!.busan,
//                            response.body()!!.incheon,
//                            response.body()!!.gwangju,
//                            response.body()!!.jeonbuk,
//                            response.body()!!.chungbuk,
//                            response.body()!!.jeonnam,
//                            response.body()!!.gyeongbuk,
//                            response.body()!!.daegu,
//                            response.body()!!.ulsan,
//                            response.body()!!.daejeon,
//                            response.body()!!.sejong,
//                            response.body()!!.chungnam,
//                            response.body()!!.gyeonggi,
//                            response.body()!!.gyeongnam,
//                            response.body()!!.gangwon,
//                            response.body()!!.jeju,
//                            response.body()!!.quarantine
//                        ) ?: mutableListOf()
//
//                        covidViewAdapter = CovidViewAdapter(covidList)
//
//                        binding.re.adapter = covidViewAdapter
//
//                        val layoutManager = LinearLayoutManager(baseContext)
//                        binding.re.layoutManager = layoutManager
//
//                    }
//                }
//            })
}