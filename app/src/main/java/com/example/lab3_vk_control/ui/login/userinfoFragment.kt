package com.example.lab3_vk_control.ui.login

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab3_vk_control.R

class userinfoFragment: Fragment(R.layout.info_user_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var text = requireActivity().findViewById<TextView>(R.id.name)
        text.text = putArgs()
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