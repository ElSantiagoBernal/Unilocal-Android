package com.example.unilocal.model

import java.time.LocalDateTime

class Comment(var id:Int, var text:String, var idUser:Int, var rating:Int, var date:LocalDateTime) {
}