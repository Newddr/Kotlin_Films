package com.example.lab3_vk_control


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vk.api.sdk.auth.VKAccessToken


public var isAutorize = false
class ContentFragment : Fragment(R.layout.fragment_content) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    val access_token= "3468c9bf3468c9bf3468c9bf31377bb49d334683468c9bf505a7cf7354ade38097d0157"



    private fun fillist(): List<VkInfo> {


        val data = mutableListOf<VkInfo>()
        (0..15).forEach { i -> data.add(VkInfo("$i element","time","https://sun9-76.userapi.com/impg/znH_e6UVDTypTyN3BKdbZJy16in08u3MsNGzVw/O3PkE6Rq2vM.jpg?size=1620x2160&quality=95&sign=61c61a5b4dbd834a5f6381fbb135d623&type=album"))}
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





