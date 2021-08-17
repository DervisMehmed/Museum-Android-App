package com.example.museumapp.ui.artObjectDetailScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.museumapp.R
import com.example.museumapp.models.collectionModels.ArtObject
import com.example.museumapp.models.detailModels.DetailWelcome
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso

/*
*   TODO: progressbar implementation
*/

class ArtObjectDetailScreen : AppCompatActivity() {
    private lateinit var artOjbViewModel: ArtObjectDetailViewModel
    private lateinit var sheetBehavior: BottomSheetBehavior<*>
    private lateinit var sheetLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_art_object_detail_screen)
        val factory = ArtObjectDetailViewModelFactory()
        val objNum = intent.extras?.get("OBJECT") as ArtObject
        artOjbViewModel = ViewModelProviders.of(this, factory).get(ArtObjectDetailViewModel::class.java)
        intent.extras?.remove("OBJECT")

        objNum.objectNumber?.let {
            artOjbViewModel.setDetailObj(getString(R.string.API_KEY), it) }

        initAdapter()
    }

    private fun initAdapter() {
        findViewById<RecyclerView>(R.id.colorRecyclerView).layoutManager =
            GridLayoutManager(this, 4)
        observeData()
    }

    private fun observeData() {
        artOjbViewModel.liveDetailObj.observe(this, {
            findViewById<RecyclerView>(R.id.colorRecyclerView).adapter =
                it.artObject?.colorsWithNormalization?.let { it1 ->
                    ArtObjColorAdapter(this.applicationContext, it1) }
            initUI(it)
            loadImage(it)
        })
    }

    private fun loadImage(obj: DetailWelcome) {
        Picasso.get()
            .load(obj.artObject?.webImage?.url)
            .into(findViewById<ImageView>(R.id.artObjimageView))
    }

    @SuppressLint("SetTextI18n")
    private fun initUI(obj: DetailWelcome){
        sheetLayout = findViewById(R.id.detailBottomSheet)
        sheetBehavior = BottomSheetBehavior.from(sheetLayout)
        findViewById<TextView>(R.id.makerDetailTextView).text = obj.artObject?.principalMaker
        findViewById<TextView>(R.id.descDetailTextView).text = obj.artObject?.plaqueDescriptionEnglish + "\n\n${obj.artObject?.subTitle}"

        var temp = ""
        //  Production Places
        obj.artObject?.productionPlaces?.forEach {
            temp += " $it"
        }
        findViewById<TextView>(R.id.placeDetailTextView).text = temp.removeSuffix(", ")
        //  Materials used in art object
        temp = ""
        obj.artObject?.materials?.forEach {
            temp += "$it, "
        }
        findViewById<TextView>(R.id.materialDetailTextView).text = temp.removeSuffix(", ")
        //  Makers
        temp = ""
        obj.artObject?.principalMakers?.forEach {
            temp += "${it.name}, "
        }
        findViewById<TextView>(R.id.makersDetailTextView).text = temp.removeSuffix(", ")
        //  Historical People
        temp = ""
        obj.artObject?.historicalPersons?.forEach {
            temp += "${it}, "
        }
        findViewById<TextView>(R.id.histPeopleDetailTextView).text = temp.removeSuffix(", ")

        findViewById<TextView>(R.id.periodDetailTextView).text = obj.artObject?.dating?.presentingDate
        findViewById<Button>(R.id.detailSheetButton).text = obj.artObject?.title
        findViewById<Button>(R.id.detailSheetButton).setOnClickListener{ buttonListener()}
    }

    private fun buttonListener() {
        if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}