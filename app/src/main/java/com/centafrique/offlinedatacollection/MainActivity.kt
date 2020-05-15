package com.centafrique.offlinedatacollection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var RecyclerView : RecyclerView

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var databaseHelper: DatabaseHelper

    private lateinit var arributeList: ArrayList<AttributesClass>
    private lateinit var attributesAdapter: AttributesAdapter

    private lateinit var linear: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        linear = findViewById(R.id.linear)

        linearLayoutManager = LinearLayoutManager(this)

        RecyclerView = findViewById(R.id.RecyclerView)
        RecyclerView.layoutManager = linearLayoutManager

        arributeList = databaseHelper.getAttributes()
        attributesAdapter = AttributesAdapter(applicationContext, arributeList)

        if (attributesAdapter.itemCount == 0){

            linear.visibility = View.VISIBLE
            RecyclerView.visibility = View.GONE

        }else{

            linear.visibility = View.GONE
            RecyclerView.visibility = View.VISIBLE
        }

        RecyclerView.adapter = attributesAdapter



    }

    fun AddNew(view: View) {

        val intent : Intent = Intent(this, PostAttribute::class.java)
        startActivity(intent)
    }
}
