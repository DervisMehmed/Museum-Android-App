package com.example.museumapp.ui

import android.graphics.drawable.Icon
import android.os.Bundle
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
    private lateinit var mainCustomAdapter: MainCustomAdapter
    private lateinit var buttonBack: Button
    private lateinit var buttonNext: Button
    private var viewManager = GridLayoutManager(this, 2)
    private var currenPage: Int = 1
    private val API_KEY = "orGRvpiL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        // Set up the user interaction to manually show or hide the system UI.
        requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
        fullscreenContent = findViewById(R.id.fullscreen_content)
        mainrecycler = findViewById(R.id.mainRecyclerView)
        buttonBack = findViewById(R.id.buttonBack)
        buttonNext = findViewById(R.id.buttonNext)

        initializeAdaptor()
        viewModel.getData(API_KEY)

        buttonNext.setOnClickListener{
            currenPage += 1
            println("Current page num: $currenPage")
            viewModel.getData(API_KEY, p = currenPage.toString())
            mainCustomAdapter.notifyDataSetChanged()
        }
        buttonBack.isEnabled = false
        buttonBack.setOnClickListener {
            /*if (currenPage != 1){
                currenPage -= 1
                viewModel.getData(API_KEY, p = currenPage.toString())
            }
            println("Current page num: $currenPage")
            mainCustomAdapter.notifyDataSetChanged()*/
        }
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