package com.example.unilocal.db

import com.example.unilocal.model.Department

object Departments {

    private var id:Int = 1
    private val departments:java.util.ArrayList<Department> = ArrayList()

    init {
        departments.add(Department(id, "Quindío"))
        id++
        departments.add(Department(id,"Antioquía"))
    }

    fun list():ArrayList<Department>{
        return departments
    }

    fun add (department: Department){
        departments.add(department)
    }

}