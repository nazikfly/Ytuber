package com.geektech.ytube.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.ytube.BuildConfig
import com.geektech.ytube.core.network.RetrofitClient
import com.geektech.ytube.data.remote.model.PlayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService=RetrofitClient.create()

    fun getPlayList():LiveData<PlayList>{


        val data= MutableLiveData<PlayList>()

        apiService.getPlayList(
            "snippet,contentDetails",
            "UCluBATXIP9doWvougjUVUGQ", BuildConfig.API_KEY
        ).enqueue(object : Callback<PlayList> {
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful) {
                    data.value=response.body()
                    Log.e("ololo","success")
                }
            }
            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                print(t.stackTrace)
                Log.e("ololo","fail")

            }
        })
        return data
    }
}

