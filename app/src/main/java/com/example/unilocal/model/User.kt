package com.example.unilocal.model

class User(var idUser:Int,
           var name:String,
           var last_name:String,
           var email:String,
           var nickname:String,
           var password:String,
           var idCountry:Int,
           var idDepartment:Int,
           var idCity:Int,
           var age:Int,
           var imgUrl:String) {

    var tel:List<String> = ArrayList();
}