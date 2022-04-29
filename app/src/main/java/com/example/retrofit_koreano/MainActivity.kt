package com.example.retrofit_koreano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_koreano.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val myRecyclerViewAdapter by lazy { EmgMedAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            adapter = myRecyclerViewAdapter
            addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }
        binding.btnGet.setOnClickListener{
            retrofitWork()
        }

    }
    private fun retrofitWork(){
        val service = RetrofitApi.emgMedService

       /* service.getEmgMedData(getString(R.string.api_key), "json")
            .enqueue(object : Callback<EmgMedResponse> {
                override fun onResponse(
                    call: Call<EmgMedResponse>,
                    response: Response<EmgMedResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("TAG", response.body().toString())
                        // head를 스킵하기 위해 index 1번을 가져옴
                        val result = response.body()?.emgMedInfo?.get(1)?.row
                        myRecyclerViewAdapter.submitList(result!!)
                    }
                }

                override fun onFailure(call: Call<EmgMedResponse>, t:  Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })*/

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getDataCoroutine(getString(R.string.api_key), "json")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val result = response.body()?.emgMedInfo?.get(1)?.row
                    result?.let {
                        myRecyclerViewAdapter.submitList(it)
                    }
                } else {
                    Log.e("TAG", response.code().toString())
                }
            }
        }


    }

}