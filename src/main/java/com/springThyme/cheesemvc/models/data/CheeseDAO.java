package com.springThyme.cheesemvc.models.data;

import com.springThyme.cheesemvc.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CheeseDAO extends CrudRepository<Cheese, Integer> {
//    custom query methods go here


//    @Query("SELECT * FROM cheeses WHERE cheese.cheeseType=:cheeseType")
//    List<Cheese> findByCheeseType(CheeseType cheeseType);



//    default void deleteMany(int [] cheeseIds) {
//        for(int id: cheeseIds) {
//            delete(id);
//        }
//    }
    void deleteByIdIn(int[] cheeseIds);
}
