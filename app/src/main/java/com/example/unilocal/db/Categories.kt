package com.example.unilocal.db

import com.example.unilocal.model.Category


object Categories {

    private var id:Int = 1
    private val categories:java.util.ArrayList<Category> = ArrayList()

    init {
        categories.add(Category(id, "Categoria 1"))
        id++
        categories.add(Category(id,"Categoria 2"))
        id++
        categories.add(Category(id,"Categoria 3"))
    }

    fun list():ArrayList<Category>{
        return categories
    }

    fun add (category: Category){
        categories.add(category)
    }
}