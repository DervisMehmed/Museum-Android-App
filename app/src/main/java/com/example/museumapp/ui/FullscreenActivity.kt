package com.example.museumapp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.alpha
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.museumapp.R
import com.example.museumapp.models.FacetFacet
import com.google.android.material.bottomsheet.BottomSheetBehavior

class FullscreenActivity : AppCompatActivity() {
    private lateinit var mainCustomAdapter: MainCustomAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var sheetBehavior: BottomSheetBehavior<*>
    private lateinit var fullscreenContent: NestedScrollView
    private lateinit var bottomSheetLayout: ConstraintLayout
    private lateinit var mainrecycler: RecyclerView
    private lateinit var buttonBack: Button
    private lateinit var buttonNext: Button
    private lateinit var sheetButton: ImageButton
    private lateinit var searchButton: Button
    private lateinit var clearButton: Button
    private lateinit var makerSpinner: Spinner
    private lateinit var typeSpinner: Spinner
    private lateinit var periodSpinner: Spinner
    private lateinit var placeSpinner: Spinner
    private lateinit var materialSpinner: Spinner
    private lateinit var techniqueSpinner: Spinner
    private lateinit var colorSpinner: Spinner


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

        initUI()
        initializeAdaptor()
        mainrecycler.setHasFixedSize(true)

        viewModel.getData(API_KEY)
        buttonNext.setOnClickListener{ buttonNextList() }
        sheetButton.setOnClickListener{ sheetListener() }
        searchButton.setOnClickListener{ searchListener() }
        clearButton.setOnClickListener{ clearListener() }
        // TODO : SPINNERLARI DOLDURUP SEARCH YAP
        // TODO : VIEWMODEL'DA FAZLADAN BIR ARRAY ACIP ILK ARAMA SONUCLARINI TUTCAK.
        // TODO : ORJINAL ARRAY DE SU ANA KADAR ACILAN ITEMLERI TUTACAK
    }

    private fun initUI(){
        fullscreenContent = findViewById(R.id.fullscreen_content)
        bottomSheetLayout = findViewById(R.id.searchBottomSheet)
        mainrecycler = findViewById(R.id.mainRecyclerView)
        buttonBack = findViewById(R.id.buttonBack)
        buttonNext = findViewById(R.id.buttonNext)
        makerSpinner = findViewById(R.id.makerSpinner)
        typeSpinner = findViewById(R.id.typeSpinner)
        periodSpinner = findViewById(R.id.periodSpinner)
        placeSpinner = findViewById(R.id.placeSpinner)
        materialSpinner = findViewById(R.id.materialSpinner)
        techniqueSpinner = findViewById(R.id.techniqueSpinner)
        colorSpinner = findViewById(R.id.colorHexSpinner)
        sheetButton = findViewById(R.id.sheetHideShowButton)
        searchButton = findViewById(R.id.searchButton)
        clearButton = findViewById(R.id.clearButton)
        sheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
    }

    private fun searchListener() {
        viewModel.searchData( API_KEY, maker = makerSpinner.selectedItem as String?)
    }

    private fun clearListener(){
        viewModel.restore()
    }

    private fun sheetListener(){
        if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            findViewById<CoordinatorLayout>(R.id.sheetCoorLayout)
                .setBackgroundColor(Color.argb(70, 0, 0, 0))
        } else {
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            findViewById<CoordinatorLayout>(R.id.sheetCoorLayout)
                .setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun buttonNextList(){
        currentPage += 1
        viewModel.getData(API_KEY, page = currentPage.toString())
    }

    private fun initializeAdaptor(){
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData(){
        viewModel.makerLiveList.observe(this, { it ->
            val temp = arrayListOf<String>()
            it.forEach{
                it.key?.let { it1 -> temp.add(it1) }
            }
            makerSpinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, temp)
        })
        viewModel.typeLiveList.observe(this, { it ->
            val temp2 = arrayListOf<String>()
            it.forEach{
                it.key?.let { it1 -> temp2.add(it1) }
            }
            typeSpinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, temp2)
        })
        viewModel.periodLiveList.observe(this, {
            val temp3 = arrayListOf<String>()
            it.forEach{
                it.key?.let { it1 -> temp3.add(it1) }
            }
            periodSpinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, temp3)
        })
        viewModel.placeLiveList.observe(this, {
            val temp4 = arrayListOf<String>()
            it.forEach{
                it.key?.let { it1 -> temp4.add(it1) }
            }
            placeSpinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, temp4)
        })
        viewModel.materialLiveList.observe(this, {
            val temp4 = arrayListOf<String>()
            it.forEach{
                it.key?.let { it1 -> temp4.add(it1) }
            }
            materialSpinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, temp4)
        })
        viewModel.techniqueLiveList.observe(this, {
            val temp5 = arrayListOf<String>()
            it.forEach{
                it.key?.let { it1 -> temp5.add(it1) }
            }
            techniqueSpinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, temp5)
        })
        viewModel.colorLiveList.observe(this, {
            val temp6 = arrayListOf<String>()
            it.forEach{
                it.key?.let { it1 -> temp6.add(it1) }
            }
            colorSpinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, temp6)
        })
        viewModel.artObjList.observe(this, Observer {
            mainCustomAdapter = MainCustomAdapter(this.applicationContext, it)
            mainrecycler.adapter = mainCustomAdapter
        })
    }
}