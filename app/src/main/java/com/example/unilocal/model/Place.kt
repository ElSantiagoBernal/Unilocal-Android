package com.example.unilocal.model

import java.util.*
import kotlin.collections.ArrayList

class Place(var id:Int,
            var name:String,
            var description:String,
            var idOwner:Int,
            var status:PlaceStatus,
            var idCategory:Int,
            var direction:String,
            var latitude:Float,
            var longitude:Float,
            var idCountry:Int,
            var idDepartment:Int,
            var idCity:Int) {

    var images:ArrayList<String> = ArrayList()
    var phoneNumbers:ArrayList<String> = ArrayList()
    var date: Date = Date()
    var schedules:ArrayList<Schedule> = ArrayList()

    fun isOpen():String{
        val date = Calendar.getInstance()
        val day = date.get(Calendar.DAY_OF_WEEK)
        val hour = date.get(Calendar.HOUR_OF_DAY)

        var message = "isClosed"

        for(schedule in schedules){
            if (schedule.weekDay.contains( WeekDay.values()[day - 1]) && hour < schedule.closingTime && hour > schedule.startTime){
                message = "Open"
                break
            }
        }
        return message
    }

    fun getClosingTime():String{
        val date = Calendar.getInstance()
        val day = date.get(Calendar.DAY_OF_WEEK)

        var message = ""

        for(schedule in schedules){
            if (schedule.weekDay.contains(WeekDay.values()[day - 1])){
                message = schedule.closingTime.toString()
                break
            }
        }
        return message
    }

    override fun toString(): String {
        return "Place(id=$id, name='$name', description='$description', idOwner=$idOwner, status=$status, idCategory=$idCategory, direction=${direction}, latitude=$latitude, longitude=$longitude, idCountry=$idCountry, idDepartment=$idDepartment, idCity=$idCity, images=$images, phoneNumbers=$phoneNumbers, date=$date, schedules=$schedules)"
    }

}