package com.springThyme.cheesemvc.models.data;

import com.springThyme.cheesemvc.models.Cheese;

import java.util.Collection;
import java.util.HashMap;

public class CheeseManager {

    private static HashMap<Integer, Cheese> cheeses = new HashMap<>();

//    CRUD methods
//    R, individual, all
//    C, individual
//    U, individual
//    D, individual, all

    public static Collection<Cheese> getAll(){
        return cheeses.values();
    }

    public static Cheese getById(int ID) {
        return cheeses.get(ID);
    }

    public static void add(Cheese cheese){
        cheeses.put(cheese.getID(), cheese);
    }

    public static void update(int ID, Cheese theCheese){
        Cheese cheese = getById(ID);
        cheese.setName(theCheese.getName());
        cheese.setDescription(theCheese.getDescription());
        cheese.setType(theCheese.getType());
        cheese.setRating(theCheese.getRating());
    }

    public static void removeCheese(int ID) {
        cheeses.remove(ID);
    }

    public static void removeCheeses(int[] IDs) {
        for(int id: IDs){
            removeCheese(id);
        }
    }

}
