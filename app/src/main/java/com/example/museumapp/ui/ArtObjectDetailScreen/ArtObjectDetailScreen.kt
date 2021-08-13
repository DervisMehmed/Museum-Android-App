package com.example.museumapp.ui.ArtObjectDetailScreen

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.museumapp.R
import com.example.museumapp.models.detailModels.collectionModels.ArtObject
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso

class ArtObjectDetailScreen : AppCompatActivity() {

    private lateinit var artObj: ArtObject
    private lateinit var sheetBehavior: BottomSheetBehavior<*>
    private lateinit var sheetLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_art_object_detail_screen)

        artObj = intent.extras?.get("OBJECT") as ArtObject
        intent.extras?.remove("OBJECT")
        initUI()
    }

    private fun initUI(){
        Picasso.get()
            .load(artObj.webImage?.url)
            .into(findViewById<ImageView>(R.id.artObjimageView))

        sheetLayout = findViewById(R.id.detailBottomSheet)
        sheetBehavior = BottomSheetBehavior.from(sheetLayout)
        findViewById<TextView>(R.id.makerDetailTextView).text = artObj.principalOrFirstMaker
        findViewById<TextView>(R.id.typeDetailTextView).text = artObj.longTitle
        findViewById<Button>(R.id.detailSheetButton).setOnClickListener{ buttonListener()}
    }

    private fun buttonListener() {
        if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            sheetLayout.setBackgroundColor(Color.argb(70, 0, 0, 0))
        } else {
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            sheetLayout.setBackgroundColor(Color.TRANSPARENT)
        }
    }
}