package com.example.museumapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.museumapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

class FullscreenActivity : AppCompatActivity() {
    private lateinit var mainCustomAdapter: MainCustomAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var sheetBehavior: BottomSheetBehavior<*>

    private var fullscreenContent: NestedScrollView = findViewById(R.id.fullscreen_content)
    private var bottomSheetLayout: ConstraintLayout = findViewById(R.id.searchBottomSheet)
    private var mainrecycler: RecyclerView = findViewById(R.id.mainRecyclerView)
    private var buttonBack: Button = findViewById(R.id.buttonBack)
    private var buttonNext: Button = findViewById(R.id.buttonNext)
    private var makerSpinner: Spinner = findViewById(R.id.makerSpinner)
    private var typeSpinner: Spinner = findViewById(R.id.typeSpinner)
    private var periodSpinner: Spinner = findViewById(R.id.periodSpinner)
    private var placeSpinner: Spinner = findViewById(R.id.placeSpinner)
    private var materialSpinner: Spinner = findViewById(R.id.materialSpinner)
    private var techniqueSpinner: Spinner = findViewById(R.id.techniqueSpinner)
    private var colorSpinner: Spinner = findViewById(R.id.colorHexSpinner)
    private var sheetButton: ImageButton = findViewById(R.id.sheetHideShowButton)
    private var viewManager = LinearLayoutManager(this)
    private var currentPage: Int = 1
    private val API_KEY = "orGRvpiL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        // Set up the user interaction to manually show or hide the system UI.
        requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        findViewById<Spinner>(R.id.makerSpinner).adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, viewModel.newlist)

        initializeAdaptor()
        sheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
        mainrecycler.setHasFixedSize(true)

        viewModel.getData(API_KEY)
        buttonNext.setOnClickListener{ buttonNextList() }
        sheetButton.setOnClickListener{ sheetListener() }

        // TODO : SPINNERLARI DOLDURUP SEARCH YAP
        // TODO : VIEWMODEL'DA FAZLADAN BIR ARRAY ACIP ILK ARAMA SONUCLARINI TUTCAK.
        // TODO : ORJINAL ARRAY DE SU ANA KADAR ACILAN ITEMLERI TUTACAK
    }

    private fun sheetListener(){
        if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
    }

    private fun buttonNextList(){
        currentPage += 1
        viewModel.getData(API_KEY, p = currentPage.toString())
        //mainCustomAdapter.notifyDataSetChanged()
    }

    private fun initializeAdaptor(){
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData(){
        viewModel.artObjList.observe(this, Observer {
            mainCustomAdapter = MainCustomAdapter(this.applicationContext, it)
            mainrecycler.adapter = mainCustomAdapter
        })
    }
}