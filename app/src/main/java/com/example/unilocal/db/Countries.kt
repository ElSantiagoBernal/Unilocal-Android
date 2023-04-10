package com.example.unilocal.db

import com.example.unilocal.model.Country

object Countries {

    private var id:Int = 1
    private val countries:java.util.ArrayList<Country> = ArrayList()

    init {
        countries.add(Country(id, "Colombia"))
    }

    fun list():ArrayList<Country>{
        return countries
    }

    fun add (country: Country){
        countries.add(country)
    }

}