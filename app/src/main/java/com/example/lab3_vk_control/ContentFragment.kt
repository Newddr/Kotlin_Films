package com.example.lab3_vk_control


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*


public var isAutorize = false
class ContentFragment : Fragment(R.layout.fragment_content) {

    public var listOfFilms = mutableListOf<VkInfo>(VkInfo("фильм 1", 2023,"Описание к фильму 1","https://sun4-22.userapi.com/impg/-tORYHaDV4FVxTcHCBY3FX3UncIqmYqFfuCQ8g/8p3VUzEBGEc.jpg?size=1391x2160&quality=95&sign=78db0f74a592abc89586f519f416cd5a&type=album"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    val access_token= "3468c9bf3468c9bf3468c9bf31377bb49d334683468c9bf505a7cf7354ade38097d0157"
    private lateinit var databaseHelper: DataBaseHelper




    @SuppressLint("Range")
    private suspend fun fillist(adapter: RecycleAdapter)  {
        if(this.context!=null) databaseHelper = DataBaseHelper(this.context!!)
        val db = databaseHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM films", null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val year = cursor.getString(cursor.getColumnIndex("year")).toInt()
            val description = cursor.getString(cursor.getColumnIndex("description"))
            val image = cursor.getString(cursor.getColumnIndex("poster"))
            val data = VkInfo(name,year,description,image)
            if (adapter != null) {

              listOfFilms.add(data)
                //adapter.addItem(data)
                adapter.notyy()
            }
            delay(4000);


        }
        cursor.close()
        databaseHelper.close()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val recycle = view.findViewById<RecyclerView>(R.id.recycler)
       recycle.layoutManager = LinearLayoutManager(context)

        val container= requireActivity().findViewById<View>(R.id.fragment)
        //val text= requireActivity().findViewById<TextView>(R.id.LogText)
//        text.text=fillist().size.toString()


//        recycle.adapter = activity?.let { RecycleAdapter(fillist() as MutableList<VkInfo>, it,container) }
//        recycle.adapter.addItem(VkInfo("fgg",5,"fddg"," "))
        val adapter = activity?.let {
            RecycleAdapter(listOfFilms ,
                it, container)
        }
        recycle.adapter = adapter

        GlobalScope.launch(Dispatchers.IO) {
            if (adapter != null) {
                fillist(adapter)
            };
        }
//        text.text=recycle.adapter?.itemCount.toString()


    }


     public fun changeFragment()
    {}

}





