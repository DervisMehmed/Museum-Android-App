package com.example.museumapp.ui

import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.museumapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FullscreenActivity : AppCompatActivity() {
    private lateinit var fullscreenContent: NestedScrollView
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var mainCustomAdapter: MainCustomAdapter
    private lateinit var bottomSheetLayout: ConstraintLayout
    private lateinit var sheetBehavior: BottomSheetBehavior<*>
    private lateinit var buttonBack: Button
    private lateinit var buttonNext: Button
    private lateinit var sheetButton: ImageButton
    private var viewManager = GridLayoutManager(this, 2)
    private var currentPage: Int = 1
    private val API_KEY = "orGRvpiL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        // Set up the user interaction to manually show or hide the system UI.
        requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)

        initUI()
        initializeAdaptor()

        viewModel.getData(API_KEY)
        buttonNext.setOnClickListener{ buttonNextList() }
        sheetButton.setOnClickListener{ sheetListener() }

        // TODO : VIEWMODEL'DA FAZLADAN BIR ARRAY ACIP ILK ARAMA SONUCLARINI TUTCAK.
        // TODO : ORJINAL ARRAY DE SU ANA KADAR ACILAN ITEMLERI TUTACAK
    }

    private fun initUI(){
        bottomSheetLayout = findViewById(R.id.searchBottomSheet)
        sheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
        fullscreenContent = findViewById(R.id.fullscreen_content)
        mainrecycler = findViewById(R.id.mainRecyclerView)
        sheetButton = findViewById(R.id.sheetHideShowButton)
        buttonBack = findViewById(R.id.buttonBack)
        buttonNext = findViewById(R.id.buttonNext)
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
        mainCustomAdapter.notifyDataSetChanged()
    }

    private fun initializeAdaptor(){
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData(){
        viewModel.artObjList.observe(this, Observer{
            mainCustomAdapter = MainCustomAdapter(this.applicationContext, it)
            mainrecycler.adapter = mainCustomAdapter
        })
    }
}