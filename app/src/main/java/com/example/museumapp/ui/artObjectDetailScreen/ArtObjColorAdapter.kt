package com.example.museumapp.ui.artObjectDetailScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.museumapp.R
import com.example.museumapp.models.detailModels.ColorsWithNormalization

class ArtObjColorAdapter (private val activity: Context?,
                          private val arrData: List<ColorsWithNormalization> )
    : RecyclerView.Adapter<ArtObjColorAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val colorImage = view.findViewById<ImageView>(R.id.colorImageView)
        var colorObj: ColorsWithNormalization? = null

        fun bindCard(color: ColorsWithNormalization){
            this.colorObj = color
            colorImage.setBackgroundColor(android.graphics.Color.parseColor(color.normalizedHex))
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText( v.context, "Color is ${colorObj?.normalizedHex}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView: View? = null
        itemView = LayoutInflater.from(activity).inflate(R.layout.color_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = arrData[position]
        holder.bindCard(model)
    }

    override fun getItemCount(): Int {
        return arrData.size
    }
}