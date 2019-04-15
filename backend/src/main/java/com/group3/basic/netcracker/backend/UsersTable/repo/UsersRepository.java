package com.group3.basic.netcracker.backend.UsersTable.repo;

import com.group3.basic.netcracker.backend.UsersTable.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface UsersRepository extends CrudRepository<Users, Long> {
    List<Users> findById(int id);
}