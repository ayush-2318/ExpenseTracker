package org.example.repository;

import org.example.entities.UserInfo;
import org.example.entities.UserInfoDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfo,String> {
   Optional<UserInfo>  findByUserId(String userId);
}
