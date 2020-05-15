package com.centafrique.offlinedatacollection

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AttributesAdapter(private val context: Context, private val attributesList: ArrayList<AttributesClass>)
    :RecyclerView.Adapter<AttributesAdapter.AttributesViewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributesViewholder {

        val inflater = LayoutInflater.from(parent.context)
        return AttributesViewholder(inflater, parent)
    }

    override fun getItemCount() = attributesList.size

    override fun onBindViewHolder(holder: AttributesViewholder, position: Int) {

        val attributesClass :AttributesClass = attributesList[position]
        holder.Assign(attributesClass, context)
    }

    class AttributesViewholder(inflater: LayoutInflater, parent: ViewGroup):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.attribute_details, parent, false)){

        private var tvName :TextView? = null
        private var tvDescription :TextView? = null
        private var imageView :ImageView? = null

        init {

            tvName = itemView.findViewById(R.id.tvName)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            imageView = itemView.findViewById(R.id.image)
        }

        fun Assign(attributesClass: AttributesClass, context: Context){

            tvName?.text = attributesClass.name
            tvDescription?.text = attributesClass.description

            val imageByte = attributesClass.image

            val bitmap :Bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)
            imageView?.setImageBitmap(bitmap)



        }

    }



}