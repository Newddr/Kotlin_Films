package com.example.lab3_vk_control

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class userinfoFragment: Fragment(R.layout.info_user_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var text = requireActivity().findViewById<TextView>(R.id.name)
        text.text = putArgs()
        var image = requireActivity().findViewById<ImageView>(R.id.imageView2)
        var bitmap= arguments?.getParcelable<Bitmap>("bitmap")
//        bitmap= bitmap?.let { resizeBitmap(it,1600,1600) }
        image.setImageBitmap(bitmap)


    }
    fun resizeBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)
        bitmap.recycle()
        return resizedBitmap
    }
    fun putArgs():String    {
        val bundle=arguments

        if(bundle!=null) {
            val name: String = bundle?.getString("name").toString()
            return name
        }
        return ""
    }




}