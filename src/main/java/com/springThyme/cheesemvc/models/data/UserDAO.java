package com.springThyme.cheesemvc.models.data;

import com.springThyme.cheesemvc.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDAO extends CrudRepository<User, Long> {
}
