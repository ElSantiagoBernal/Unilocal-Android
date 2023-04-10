package com.example.unilocal.db

import com.example.unilocal.model.User

object Users {

    private var id:Int = 1
    private val users:java.util.ArrayList<User> = ArrayList()

    init {
        users.add(User(id, "Andres", "Ocampo", "loca@gmail.com", "Z4ND3R", "helado444", 1, 1, 1, 20,""))
        id++
        users.add(User(id, "Santiago", "Bernal", "loca2@gmail.com", "ElSantiago", "1234", 1, 1, 1, 20,""))
        id++
        users.add(User(id, "Luisa", "Pulido", "loca3@gmail.com", "Lu", "4321", 1, 2, 2, 22,""))
    }

    fun list():ArrayList<User>{
        return users
    }

    fun findByEmail(email:String): User? {
        return users.first { u -> u.email == email }
    }

    fun add (user: User){
        users.add(user)
    }

    fun size (): Int {
        return users.size
    }

    /*fun login(email:String, pass:String):User{
        val answer = users.first { u -> u.password == pass && u.email == email }
        return answer
    }*/
}