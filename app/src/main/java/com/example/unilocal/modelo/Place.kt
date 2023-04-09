package com.example.unilocal.modelo

import java.time.LocalDateTime

class Place(var id:Int,
            var name:String,
            var description:String,
            var images:List<String>,
            var idOwner:Int, var status:Boolean,
            var idCategory:Int,
            var latitude:Float,
            var longitude:Float,
            var idCountry:Int,
            var idDepartment:Int,
            var idCity:Int,
            var date:LocalDateTime) {
    var tel:List<String> = ArrayList();
}