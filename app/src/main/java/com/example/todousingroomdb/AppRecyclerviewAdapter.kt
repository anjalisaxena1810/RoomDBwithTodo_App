package com.example.todousingroomdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todousingroomdb.databinding.LayoutCustomViewBinding


class AppRecyclerviewAdapter(private val userList: List<User>,private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<AppRecyclerviewAdapter.CustomViewHolder>() {
        inner class CustomViewHolder(var layoutCustomViewBinding: LayoutCustomViewBinding): RecyclerView.ViewHolder(layoutCustomViewBinding.root){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            var layoutCustomViewBinding:LayoutCustomViewBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.layout_custom_view,parent,false)
            return CustomViewHolder(layoutCustomViewBinding)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            val user = userList.get(position)
            holder.layoutCustomViewBinding.TitleView.text= user.Title
            holder.layoutCustomViewBinding.descriptionView.text= user.Description
//            holder.layoutCustomViewBinding.time1 .text= user.CreateAt


            holder.itemView.setOnClickListener {
                onItemClickListener.onItemClick(position,user)
            }



        }
        override fun getItemCount(): Int {
            return userList.size
        }
    }
