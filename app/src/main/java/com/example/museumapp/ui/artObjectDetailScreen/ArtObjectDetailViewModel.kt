package com.example.museumapp.ui.artObjectDetailScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.museumapp.models.collectionModels.RetrofitClient
import com.example.museumapp.models.detailModels.DetailWelcome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtObjectDetailViewModel: ViewModel() {
    private lateinit var call : Call<DetailWelcome>
    var detailObject = DetailWelcome()

    var liveDetailObj = MutableLiveData<DetailWelcome>()

    fun setDetailObj(key: String, objNum : String){
        this.call = getServiceCall(objNum, key)
        loadDetailData(call)
    }

    private fun loadDetailData(call: Call<DetailWelcome>) {
        call.enqueue(object: Callback<DetailWelcome> {
            override fun onResponse(call: Call<DetailWelcome>, response: Response<DetailWelcome>) {
                val body = response.body()
                if (body != null) {
                    detailObject = body
                }
                liveDetailObj.postValue(detailObject)
            }

            override fun onFailure(call: Call<DetailWelcome>, t: Throwable) {
                Log.d("DetailViewModel", "Failure" + t.message)
            }
        })
    }

    private fun getServiceCall(key: String, objNum: String): Call<DetailWelcome> {
        return RetrofitClient.retroInterface.getDetailedData(key, objNum)
    }
}