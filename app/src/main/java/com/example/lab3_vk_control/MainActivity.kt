package com.example.lab3_vk_control

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.viewpager.widget.ViewPager
import com.example.lab3_vk_control.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    lateinit var viewPager:ViewPager
    val fragment = ContentFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.autorizebtn)
        btn.setOnClickListener {
            val IntentLog = Intent(this, LoginActivity::class.java)
           startActivity(IntentLog)
        }
        



    }


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        return super.onCreateView(name, context, attrs)

    }
    public fun setPage(page: Int) {

        viewPager.setCurrentItem(page, true)
    }




}