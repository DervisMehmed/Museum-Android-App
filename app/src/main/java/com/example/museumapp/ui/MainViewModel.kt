package com.example.museumapp.ui

import android.util.Log
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
    private var firstTime : Boolean = false
    var artObjList = MutableLiveData<List<ArtObject>>()
    var newlist = arrayListOf<ArtObject>()
    var orgList = arrayListOf<ArtObject>()

    fun search(key: String?, p: String? = null, ps: String? = null, imgonly: String? = null) {
        this.call = getServiceCall(key, p, ps, imgonly)
        firstTime = false
        loadData(call)
    }

    fun getData(key: String?, p: String? = null, ps: String? = null, imgonly: String? = null) {
        this.call = getServiceCall(key, p, ps, imgonly)
        firstTime = true
        loadData(call)
    }

    private fun getServiceCall(key: String?, p: String? = null, ps: String? = null, imgonly: String? = null): Call<Welcome> {
        return RetrofitClient.retroInterface.getCollectionData(key, p, ps, imgonly)
    }

    private fun loadData( call : Call<Welcome>){
        call.enqueue( object : Callback<Welcome> {
            override fun onResponse(call: Call<Welcome>, response: Response<Welcome>) {
                val body = response.body()
                if (body != null) {
                    newlist.addAll(body.artObjects as Collection<ArtObject>)
                    if(firstTime)
                        orgList = newlist
                    artObjList.postValue(newlist)
                }
            }

            override fun onFailure(call: Call<Welcome>, t: Throwable) {
                Log.d("ViewModel", "Failure" + t.message)
            }
        })
    }
}