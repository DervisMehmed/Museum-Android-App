package com.example.museumapp.ui.collectionListScreen

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.museumapp.R
import com.example.museumapp.models.collectionModels.ArtObject
import com.example.museumapp.ui.artObjectDetailScreen.ArtObjectDetailScreen
import com.squareup.picasso.Picasso

class MainCustomAdapter (private val activity: Context?,
                         private val arrData: List<ArtObject>)
: RecyclerView.Adapter<MainCustomAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {
        private val itemTitle : TextView = view.findViewById(R.id.ItemTitleTextView)
        private val itemPic: ImageView? = view.findViewById(R.id.ItemImageView)
        var artObj: ArtObject? = null

        fun bindCard(wel: ArtObject){
            this.artObj = wel
            itemTitle.text = wel.longTitle
            Picasso.get()
                .load(wel.webImage?.url)
                .fit()
                .into(itemPic)
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = v?.context
            val intent = Intent(context, ArtObjectDetailScreen::class.java)
            intent.putExtra("OBJECT", artObj)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView: View? = null
        itemView = LayoutInflater.from(activity).inflate(R.layout.cardview_listitems, parent, false)
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