package com.example.lab3_vk_control

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3_vk_control.ui.login.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

public var isAutorize = false
class ContentFragment : Fragment(R.layout.fragment_content) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private fun fillist(): List<VkInfo> {
        val data = mutableListOf<VkInfo>()
        (0..15).forEach { i -> data.add(VkInfo("$i element","time", Picasso.get().
        load("https://sun9-76.userapi.com/impg/znH_e6UVDTypTyN3BKdbZJy16in08u3MsNGzVw/O3PkE6Rq2vM.jpg?size=1620x2160&quality=95&sign=61c61a5b4dbd834a5f6381fbb135d623&type=album").transform(
            CircleTransform()
        ))) }
        return data

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val recycle = view.findViewById<RecyclerView>(R.id.recycler)
       recycle.layoutManager = LinearLayoutManager(context)

        val container= requireActivity().findViewById<View>(R.id.fragment)
        //val text= requireActivity().findViewById<TextView>(R.id.LogText)
//        text.text=fillist().size.toString()


        recycle.adapter = activity?.let { RecycleAdapter(fillist(), it,container) }
//        text.text=recycle.adapter?.itemCount.toString()


    }
     public fun changeFragment()
    {}

}

