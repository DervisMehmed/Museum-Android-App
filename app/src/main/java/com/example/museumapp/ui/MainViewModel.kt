package com.example.museumapp.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.museumapp.models.ArtObject
import com.example.museumapp.models.RetrofitClient
import com.example.museumapp.models.Welcome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private lateinit var call : Call<Welcome>
    private lateinit var welcome : Welcome
    var artObjList = MutableLiveData<List<ArtObject>>()
    var newlist = arrayListOf<Welcome>()

    fun getData(key: String?) {
        this.call = getServiceCall(key)
        loadData(call)
    }

    private fun getServiceCall(key: String?) : Call<Welcome> {
        return RetrofitClient.retroInterface.getCollectionData(key)
    }

    private fun loadData( call : Call<Welcome>){
        call.enqueue( object : Callback<Welcome> {
            override fun onResponse(call: Call<Welcome>, response: Response<Welcome>) {
                val body = response.body()
                if (body != null) {
                    with(body){
                        welcome = Welcome(elapsedMilliseconds,count,countFacets,artObjects,facets)
                    }
                    artObjList.postValue(body.artObjects)
                }
            }

            override fun onFailure(call: Call<Welcome>, t: Throwable) {
                Log.d("ViewModel", "Failure" + t.message)
            }
        })
    }
}