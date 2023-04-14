package com.example.unilocal.db

import com.example.unilocal.model.Place
import com.example.unilocal.model.PlaceStatus
import com.example.unilocal.model.Schedule

object Places {

    private val places:java.util.ArrayList<Place> = ArrayList()

    init {
        val schedule1 = Schedule(1, Schedules.getAll(),12,20)
        val schedule2 = Schedule(2, Schedules.getWeekDay(),9,20)
        val schedule3 = Schedule(3, Schedules.getWeekend(),14,13)

        val place1 = Place(1, "Maria Licky", "Comidas Rápidas", 1, PlaceStatus.ACCEPTED, 1, "Cll 12B 15-02",45.545454f,-23.87867f,1,1,1)
        place1.schedules.add(schedule1)
        val place2 = Place(2, "Don Pepo", "Granero don Pepo", 2, PlaceStatus.ACCEPTED, 2, "Quintas de la Castellana",45.545454f,-23.87867f,1,1,1)
        place2.schedules.add(schedule2)
        val place3 = Place(3, "Unicentro", "Centro Comercial unicentro", 3, PlaceStatus.REJECTED, 3, "San Agustín 22-30",45.545454f,-23.87867f,1,1,1)
        place3.schedules.add(schedule2)
        val place4 = Place(4, "CentralPark", "Parque Recreacional", 1, PlaceStatus.PENDING, 4, "Cra 28A 12-01",45.545454f,-23.87867f,1,1,1)
        place4.schedules.add(schedule1)
        val place5 = Place(5, "Bar HUB", "Barcito", 2, PlaceStatus.ACCEPTED, 5, "Freislo 88879",45.545454f,-23.87867f,1,1,1)
        place5.schedules.add(schedule2)
        val place6 = Place(6, "Licky Liquors", "Licorera", 3, PlaceStatus.ACCEPTED, 6, "Av. Centenario B13-9",45.545454f,-23.87867f,1,1,1)
        place6.schedules.add(schedule3)

        places.add(place1)
        places.add(place2)
        places.add(place3)
        places.add(place4)
        places.add(place5)
        places.add(place6)

    }

    fun ListByState(status:PlaceStatus):ArrayList<Place>{
        return places.filter { p -> p.status == status }.toCollection(ArrayList())
    }

    fun ListByOwner(idOwner:Int):ArrayList<Place>{
        return places.filter { p -> p.idOwner == idOwner }.toCollection(ArrayList())
    }

    fun get(id:Int): Place?{
        return places.firstOrNull { p -> p.id == id }
    }

    fun findByName(name:String): ArrayList<Place> {
        return places.filter { p -> p.name.lowercase().contains(name.lowercase()) && p.status == PlaceStatus.ACCEPTED }.toCollection(ArrayList())
    }

    fun create(place:Place){
        places.add( place )
    }

    fun findByCity(idCity:Int): ArrayList<Place> {
        return places.filter { p -> p.idCity == idCity && p.status == PlaceStatus.ACCEPTED }.toCollection(ArrayList())
    }

    fun findByCategory(idCategory:Int): ArrayList<Place> {
        return places.filter { p -> p.idCategory == idCategory && p.status == PlaceStatus.ACCEPTED }.toCollection(ArrayList())
    }


    fun changeStatus(id:Int, newStatus:PlaceStatus){

        val place = places.firstOrNull{ p -> p.id == id}

        if(place!=null){
            place.status = newStatus
        }

    }

}