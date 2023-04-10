package com.example.unilocal.db

import com.example.unilocal.activities.model.City


object Cities {

    private var id:Int = 1
    private val cities:java.util.ArrayList<City> = ArrayList()

    init {
        cities.add(City(id, "Armenia"))
        id++
        cities.add(City(id,"Medellin"))
    }

    fun list():ArrayList<City>{
        return cities
    }

    fun add (city: City){
        cities.add(city)
    }

}