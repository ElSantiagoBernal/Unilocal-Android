package com.example.unilocal.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unilocal.R
import com.example.unilocal.activities.DetailPlaceActivity
import com.example.unilocal.model.Place
import com.example.unilocal.model.Schedule

class PlaceAdapter(var list:ArrayList<Place>): RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(var itemView:View):RecyclerView.ViewHolder(itemView), OnClickListener{

        val name:TextView = itemView.findViewById(R.id.place_name)
        val direction:TextView = itemView.findViewById(R.id.place_direction)
        val startTime:TextView = itemView.findViewById(R.id.start_time)
        val closingTime:TextView = itemView.findViewById(R.id.closing_time)
        val status:TextView = itemView.findViewById(R.id.status_place)
        val image:ImageView = itemView.findViewById(R.id.place_image)
        var codePlace:Int = 0

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(place: Place){
            name.text = place.name
            direction.text = place.direction
            startTime.text = place.schedules[0].startTime.toString() + ":00"
            closingTime.text = place.schedules[0].closingTime.toString() + ":00"
            status.text = "Abierto"
            codePlace= place.id
        }

        override fun onClick(p0: View?) {
            val intent = Intent(name.context, DetailPlaceActivity::class.java)
            intent.putExtra("code", codePlace)
            name.context.startActivity(intent)
        }

    }
}