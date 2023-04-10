package com.example.unilocal.model

import java.util.*
import kotlin.collections.ArrayList

class Place(var id:Int,
            var name:String,
            var description:String,
            var idOwner:Int,
            var status:PlaceStatus,
            var idCategory:Int,
            var latitude:Float,
            var longitude:Float,
            var idCountry:Int,
            var idDepartment:Int,
            var idCity:Int) {

    var images:ArrayList<String> = ArrayList()
    var phoneNumbers:ArrayList<String> = ArrayList()
    var date: Date = Date()
    var schedules:ArrayList<Schedule> = ArrayList()

    override fun toString(): String {
        return "Lugar(id=$id, name='$name', description='$description', idOwner=$idOwner, status=$status, idCategory=$idCategory, latitude=$latitude, longitude=$longitude, idCountry=$idCountry, idDepartment=$idDepartment, idCity=$idCity, images=$images, phoneNumbers=$phoneNumbers, date=$date, schedules=$schedules)"
    }

}