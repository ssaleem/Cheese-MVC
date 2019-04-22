package com.springThyme.cheesemvc.models.data;

import com.springThyme.cheesemvc.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryDAO extends CrudRepository<Category, Integer> {
}
