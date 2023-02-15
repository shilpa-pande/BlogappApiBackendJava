package com.blog.blogappapi.repository;

import com.blog.blogappapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepo extends JpaRepository<Role,Integer> {


}
