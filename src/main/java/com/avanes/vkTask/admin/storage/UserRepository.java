package com.avanes.vkTask.admin.storage;

import com.avanes.vkTask.admin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(Long id);

    @Query(value = "SELECT * " +
            "FROM users", nativeQuery = true)
    List<UserEntity> getAllUsers();

    UserEntity findFirstByEmail(String email);

}
