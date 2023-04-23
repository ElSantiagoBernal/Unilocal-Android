package com.example.unilocal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unilocal.R
import com.example.unilocal.model.Comment

class CommentsAdapter(var list:ArrayList<Comment>): RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.comment_place, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(var commentsPlace: View):RecyclerView.ViewHolder(commentsPlace), OnClickListener{
        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

        fun bind(comment: Comment) {
            TODO("Not yet implemented")
        }

    }
}