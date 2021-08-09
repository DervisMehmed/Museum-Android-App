package com.example.museumapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.museumapp.R
import com.example.museumapp.models.ArtObject
import com.example.museumapp.models.Welcome
import com.squareup.picasso.Picasso
import java.io.Serializable

class MainCustomAdapter (private val activity: Context?, private val arrData: List<ArtObject>)
: RecyclerView.Adapter<MainCustomAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {
        private val itemTitle : TextView = view.findViewById(R.id.ItemTitleTextView)
        private val itemPic: ImageView? = view.findViewById(R.id.ItemImageView)
        var artObj: ArtObject? = null

        fun bindCard(wel: ArtObject){
            this.artObj = wel
            itemTitle.text = wel.longTitle
            Picasso.get().load(wel.webImage?.url)
                .into(itemPic)
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            /*val context = v?.context
            val intent = Intent(context, CharDetailScreenActivity::class.java)
            intent.putExtra("OBJECT", character as Serializable)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(intent)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCustomAdapter.ViewHolder {
        var itemView: View? = null
        itemView = LayoutInflater.from(activity).inflate(R.layout.cardview_listitems, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainCustomAdapter.ViewHolder, position: Int) {
        val model = arrData[position]
        holder.bindCard(model)
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

}