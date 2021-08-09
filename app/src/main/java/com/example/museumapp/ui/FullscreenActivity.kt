package com.example.museumapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.museumapp.R

class FullscreenActivity : AppCompatActivity() {
    private lateinit var fullscreenContent: NestedScrollView
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var buttonBack: Button
    private lateinit var buttonNext: Button
    private var viewManager = GridLayoutManager(this, 2)
    private val API_KEY = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up the user interaction to manually show or hide the system UI.
        fullscreenContent = findViewById(R.id.fullscreen_content)

        val application = requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
        mainrecycler = findViewById(R.id.mainRecyclerView)
        buttonBack = findViewById(R.id.buttonBack)
        buttonNext = findViewById(R.id.buttonNext)

        initializeAdaptor()
        viewModel.getData()
    }

    private fun initializeAdaptor(){
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData(){
        viewModel.artObjList.observe(this, Observer{
            Log.i("data",it.toString())
            mainrecycler.adapter= MainCustomAdapter(this.applicationContext, it)
        })
    }
}