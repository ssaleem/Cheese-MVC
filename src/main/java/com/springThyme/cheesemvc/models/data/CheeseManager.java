package com.springThyme.cheesemvc.models.data;

import com.springThyme.cheesemvc.models.Cheese;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class CheeseManager {

    private static HashMap<Integer, Cheese> cheeses = new HashMap<>();

//    CRUD methods
//    R, individual, all
//    C, individual
//    U, individual
//    D, individual, all

    public static Collection<Cheese> getCheeses(){
        return cheeses.values();
    }

    public static Cheese getCheese(int ID) {
        return cheeses.get(ID);
    }

//    public static void create(String name, String description ){
//        Cheese obj = new Cheese(name, description);
//        add(obj);
//    }

    public static void add(Cheese cheese){
        cheeses.put(cheese.getID(), cheese);
    }

    public static void removeCheese(int ID) {
        cheeses.remove(ID);
    }

    public static void removeCheeses(ArrayList<Integer> IDs) {
        for(int id: IDs){
            removeCheese(id);
        }
    }

}
