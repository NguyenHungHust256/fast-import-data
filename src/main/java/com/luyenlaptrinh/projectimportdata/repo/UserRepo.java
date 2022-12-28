package com.luyenlaptrinh.projectimportdata.repo;

import com.luyenlaptrinh.projectimportdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
