package com.example.museumapp.ui.collectionListScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.museumapp.models.collectionModels.ArtObject
import com.example.museumapp.models.collectionModels.FacetFacet
import com.example.museumapp.models.collectionModels.RetrofitClient
import com.example.museumapp.models.collectionModels.Welcome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private lateinit var call : Call<Welcome>
    private lateinit var body : Welcome
    private var firstTime : Boolean = false
    var artObjList = MutableLiveData<List<ArtObject>>()
    var newlist = arrayListOf<ArtObject>()
    var searchList = arrayListOf<ArtObject>()
    var orgList = arrayListOf<ArtObject>()

    var makerLiveList = MutableLiveData<List<FacetFacet>>()
    var makerList = arrayListOf<FacetFacet>()
    var typeLiveList = MutableLiveData<List<FacetFacet>>()
    var typeList = arrayListOf<FacetFacet>()
    var periodLiveList = MutableLiveData<List<FacetFacet>>()
    var periodList = arrayListOf<FacetFacet>()
    var placeLiveList = MutableLiveData<List<FacetFacet>>()
    var placeList = arrayListOf<FacetFacet>()
    var materialLiveList = MutableLiveData<List<FacetFacet>>()
    var materialList = arrayListOf<FacetFacet>()
    var techniqueLiveList = MutableLiveData<List<FacetFacet>>()
    var techniqueList = arrayListOf<FacetFacet>()
    var colorLiveList = MutableLiveData<List<FacetFacet>>()
    var colorList = arrayListOf<FacetFacet>()

    fun searchData(key: String?, page: String? = null, maker: String? = null,
                   type: String? = null, material: String? = null, technique: String? = null,
                   period: String? = null) {
        this.call = getServiceCall(key, page, maker, type, material, technique, period)
        firstTime = false
        loadData(call)
    }

    fun getData(key: String?, page: String? = null, maker: String? = null, type: String? = null,
                material: String? = null, technique: String? = null, period: String? = null) {
        this.call = getServiceCall(key, page, maker, type, material, technique, period)
        firstTime = true
        loadData(call)
    }

    fun restore(){
        newlist = orgList
        searchList.clear()
        artObjList.postValue(newlist)
    }

    private fun getServiceCall(key: String?, page: String? = null, maker: String? = null,
                               type: String? = null, material: String? = null,
                               technique: String? = null, period: String? = null): Call<Welcome> {
        return RetrofitClient.retroInterface.getCollectionData(key, page, maker, type, material, technique, period)
    }

    private fun loadData( call : Call<Welcome>){
        call.enqueue( object : Callback<Welcome> {
            override fun onResponse(call: Call<Welcome>, response: Response<Welcome>) {
                body = response.body()!!
                if(firstTime){
                    newlist.addAll(body.artObjects as Collection<ArtObject>)
                    orgList = newlist
                }
                else{
                    searchList.addAll(body.artObjects as ArrayList<ArtObject>)
                    newlist = searchList
                }
                makerList = body.facets?.get(0)?.facets as ArrayList<FacetFacet>
                typeList = body.facets?.get(1)?.facets as ArrayList<FacetFacet>
                periodList = body.facets?.get(2)?.facets as ArrayList<FacetFacet>
                placeList = body.facets?.get(3)?.facets as ArrayList<FacetFacet>
                materialList = body.facets?.get(4)?.facets as ArrayList<FacetFacet>
                techniqueList = body.facets?.get(5)?.facets as ArrayList<FacetFacet>
                colorList = body.facets?.get(6)?.facets as ArrayList<FacetFacet>

                makerLiveList.postValue(makerList)
                typeLiveList.postValue(typeList)
                periodLiveList.postValue(periodList)
                placeLiveList.postValue(placeList)
                materialLiveList.postValue(materialList)
                techniqueLiveList.postValue(techniqueList)
                colorLiveList.postValue(colorList)
                artObjList.postValue(newlist)
            }

            override fun onFailure(call: Call<Welcome>, t: Throwable) {
                Log.d("ViewModel", "Failure" + t.message)
            }
        })
    }
}