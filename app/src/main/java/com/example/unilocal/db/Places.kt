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

        val place1 = Place(1, "Maria Juana", "Comidas Rápidas", 1, PlaceStatus.ACCEPTED, 1, 45.545454f,-23.87867f,1,1,1)
        val place2 = Place(2, "Don Pepo", "Granero don Pepo", 2, PlaceStatus.ACCEPTED, 2, 45.545454f,-23.87867f,1,2,2)
        val place3 = Place(3, "Unicentro", "Centro Comercial unicentro", 3, PlaceStatus.REJECTED, 3, 45.545454f,-23.87867f,1,1,1)
        val place4 = Place(4, "CentralPark", "Parque Recreacional", 1, PlaceStatus.PENDING, 4, 45.545454f,-23.87867f,1,2,2)
        val place5 = Place(5, "Bar HUB", "Barcito", 2, PlaceStatus.ACCEPTED, 5, 45.545454f,-23.87867f,1,1,1)
        val place6 = Place(6, "Licky Liquors", "Licorera", 3, PlaceStatus.PENDING, 6, 45.545454f,-23.87867f,1,2,2)

        places.add(place1)
        places.add(place2)
        places.add(place3)
        places.add(place4)
        places.add(place5)
        places.add(place6)

        fun ListByState(status:PlaceStatus):ArrayList<Place>{
            return places.filter { p -> p.status == status }.toCollection(ArrayList())
        }

        fun get(id:Int): Place?{
            return places.firstOrNull { p -> p.id == id }
        }

        fun searchByName(name:String): ArrayList<Place> {
            return places.filter { p -> p.name.lowercase().contains(name.lowercase()) && p.status == PlaceStatus.ACCEPTED }.toCollection(ArrayList())
        }

        fun create(place:Place){
            places.add( place )
        }

        fun searchByCity(idCity:Int): ArrayList<Place> {
            return places.filter { p -> p.idCity == idCity && p.status == PlaceStatus.ACCEPTED }.toCollection(ArrayList())
        }

        fun searchByCategory(idCategory:Int): ArrayList<Place> {
            return places.filter { p -> p.idCategory == idCategory && p.status == PlaceStatus.ACCEPTED }.toCollection(ArrayList())
        }


        fun changeStatus(id:Int, newStatus:PlaceStatus){

            val place = places.firstOrNull{ p -> p.id == id}

            if(place!=null){
                place.status = newStatus
            }

        }

    }

}