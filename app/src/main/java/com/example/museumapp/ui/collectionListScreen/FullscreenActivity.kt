package com.example.museumapp.ui.collectionListScreen

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.museumapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

class FullscreenActivity : AppCompatActivity() {
    private lateinit var mainCustomAdapter: MainCustomAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var sheetBehavior: BottomSheetBehavior<*>
    private lateinit var fullscreenContent: NestedScrollView
    private lateinit var bottomSheetLayout: ConstraintLayout
    private lateinit var mainrecycler: RecyclerView
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

    private var maker:  String? = null
    private var type:   String? = null
    private var period: String? = null
    private var place:  String? = null
    private var material: String? = null
    private var technique: String? = null
    private var color:  String? = null
    private var viewManager = LinearLayoutManager(this)
    private var spinnerInitializedList = arrayListOf<Boolean>()
    private var searchMore = false
    private var searchPage = 1
    private var currentPage = 1
    private lateinit var API_KEY: String

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

        API_KEY = getString(R.string.API_KEY)
        viewModel.getData(API_KEY)



        for ( i in 0..6){
            spinnerInitializedList.add(i, false)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        buttonNext.setOnClickListener{ buttonNextList() }
        sheetButton.setOnClickListener{ sheetListener() }
        searchButton.setOnClickListener{ searchListener() }
        clearButton.setOnClickListener{ clearListener() }
        spinnerListeners()
    }

    private fun initUI(){
        fullscreenContent = findViewById(R.id.fullscreen_content)
        bottomSheetLayout = findViewById(R.id.searchBottomSheet)
        mainrecycler = findViewById(R.id.mainRecyclerView)
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

    private fun initSpinner(){
        for ( i in 0..6){
            spinnerInitializedList[i] = false
        }
    }

    private fun searchListener() {
        viewModel.searchData(API_KEY, maker = maker, type = type,
                material = material, technique = technique, period = period)
        searchMore = true
        initSpinner()
        fullscreenContent.smoothScrollTo(0, mainrecycler.top, 500)
    }

    private fun clearListener(){
        viewModel.restore()
        initSpinner()
        searchMore = false
        searchPage = 1
        maker = null
        type  = null
        material = null
        technique = null
        period = null
        color = null
    }

    private fun buttonNextList(){
        if(!searchMore) {
            currentPage += 1
            initSpinner()
            viewModel.getData(API_KEY, currentPage.toString(), maker, type,
                    material, technique, period)
        } else{
            searchPage += 1
            initSpinner()
            viewModel.searchData(API_KEY, searchPage.toString(), maker, type,
                    material, technique, period)
        }
    }

    private fun initializeAdaptor(){
        mainrecycler.layoutManager = viewManager
        observeData()
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

    private fun spinnerListeners(){
        makerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!spinnerInitializedList[0]) {
                    spinnerInitializedList[0] = true
                    return
                }
                if (parent != null) {
                    maker = parent.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!spinnerInitializedList[1]) {
                    spinnerInitializedList[1] = true
                    return
                }
                if (parent != null) {
                    type = parent.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        periodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!spinnerInitializedList[2]) {
                    spinnerInitializedList[2] = true
                    return
                }
                if (parent != null) {
                    period = parent.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        placeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!spinnerInitializedList[3]) {
                    spinnerInitializedList[3] = true
                    return
                }
                if (parent != null) {
                    place = parent.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        materialSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!spinnerInitializedList[4]) {
                    spinnerInitializedList[4] = true
                    return
                }
                if (parent != null) {
                    material = parent.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        techniqueSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!spinnerInitializedList[5]) {
                    spinnerInitializedList[5] = true
                    return
                }
                if (parent != null) {
                    technique = parent.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!spinnerInitializedList[6]) {
                    spinnerInitializedList[6] = true
                    return
                }
                if (parent != null) {
                    color = parent.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun observeData(){
        viewModel.makerLiveList.observe(this, { it ->
            val temp = arrayListOf<String>()
            it.forEach {
                it.key?.let { it1 -> temp.add(it1) }
            }
            temp.add(0, "")
            makerSpinner.adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, temp)
        })
        viewModel.typeLiveList.observe(this, { it ->
            val temp2 = arrayListOf<String>()
            it.forEach {
                it.key?.let { it1 -> temp2.add(it1) }
            }
            temp2.add(0, "")
            typeSpinner.adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, temp2)
        })
        viewModel.periodLiveList.observe(this, {
            val temp3 = arrayListOf<String>()
            it.forEach {
                it.key?.let { it1 -> temp3.add(it1) }
            }
            temp3.add(0, "")
            periodSpinner.adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, temp3)
        })
        viewModel.placeLiveList.observe(this, {
            val temp4 = arrayListOf<String>()
            it.forEach {
                it.key?.let { it1 -> temp4.add(it1) }
            }
            temp4.add(0, "")
            placeSpinner.adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, temp4)
        })
        viewModel.materialLiveList.observe(this, {
            val temp5 = arrayListOf<String>()
            it.forEach {
                it.key?.let { it1 -> temp5.add(it1) }
            }
            temp5.add(0, "")
            materialSpinner.adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, temp5)
        })
        viewModel.techniqueLiveList.observe(this, {
            val temp6 = arrayListOf<String>()
            it.forEach {
                it.key?.let { it1 -> temp6.add(it1) }
            }
            temp6.add(0, "")
            techniqueSpinner.adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, temp6)
        })
        viewModel.colorLiveList.observe(this, {
            val temp7 = arrayListOf<String>()
            it.forEach {
                it.key?.let { it1 -> temp7.add(it1) }
            }
            temp7.add(0, "")
            colorSpinner.adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, temp7)
        })
        viewModel.artObjList.observe(this, Observer {
            mainCustomAdapter = MainCustomAdapter(this.applicationContext, it)
            mainrecycler.adapter = mainCustomAdapter
        })
    }
}