package com.myself.learningkotlin

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_userinfo.view.*

class MyAdapter(private val context:Context,private val data:List<User>?) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = View.inflate(context,R.layout.item_userinfo,null)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(data) {
            holder.tvName.text = this?.get(position)?.getName()
            holder.tvAge.text = this?.get(position)?.getAge().toString()
            holder.tvJob.text = this?.get(position)?.getJob()
            holder.tvSkin.text = this?.get(position)?.getSkin()
        }

    }



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var tvName = itemView.tv_name!!
         var tvAge = itemView.tv_age!!
         var tvJob = itemView.tv_job!!
         var tvSkin = itemView.tv_skin!!
    }
}