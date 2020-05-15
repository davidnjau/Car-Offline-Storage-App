package com.centafrique.offlinedatacollection

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.io.ByteArrayOutputStream

class PostAttribute : AppCompatActivity() {

    private lateinit var etName : EditText
    private lateinit var etDescription : EditText
    private lateinit var imageView: ImageView
    private lateinit var imageUri : Uri

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_attribute)

        etName = findViewById(R.id.etName)
        etDescription = findViewById(R.id.etDescription)

        imageView = findViewById(R.id.imageView)

        databaseHelper = DatabaseHelper(this)


    }

    fun Save(view: View) {

        val txtName: String = etName.text.toString()
        val txtDesc: String = etDescription.text.toString()

        if(!TextUtils.isEmpty(txtName) && !TextUtils.isEmpty(txtDesc) && imageUri != null ){

            val bitmap = (imageView.drawable as BitmapDrawable).bitmap

            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val imageByte = byteArrayOutputStream.toByteArray()
            databaseHelper.addAttribute(txtName, txtDesc, imageByte)

            Toast.makeText(applicationContext, "Added successfully ", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }

    fun Gallery(view: View) {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1){

            imageUri = data?.data!!

            imageView.setImageURI(imageUri) // handle chosen image
        }
    }
}
