package com.example.lab3_vk_control



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3_vk_control.ui.login.CircleTransform
import com.example.lab3_vk_control.ui.login.userinfoFragment
import com.squareup.picasso.Picasso


class RecycleAdapter(private val names: List<VkInfo>,private val linkActivity: FragmentActivity, private val container:View): RecyclerView.Adapter<RecycleAdapter.MyViewHolder>(){



     class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

         val largeTextView: TextView = itemView.findViewById(R.id.textViewLarge)
         val smallTextView: TextView = itemView.findViewById(R.id.textViewSmall)
         val imageview:ImageView=itemView.findViewById(R.id.imageView)

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

         val itemView = LayoutInflater.from(parent.context).inflate(com.example.lab3_vk_control.R.layout.recyclerview_item, parent, false)
         return MyViewHolder(itemView)
     }

     override fun getItemCount() = names.size

     override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

         holder.largeTextView.text = names[position].name
         holder.smallTextView.text = names[position].timeOnline


        names[position].image.into(holder.imageview)
         val fragment= userinfoFragment()

         holder.largeTextView.setOnClickListener(View.OnClickListener{

             val bundle = Bundle()
             bundle.putString("name",names[position].name)

             fragment.arguments = bundle

             linkActivity.supportFragmentManager.beginTransaction().replace(container.id,fragment).commit()
         })



     }

 }