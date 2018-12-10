package com.dxc.user.repository;

import com.dxc.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsernameAndDeleted(@Param("username") String username,@Param("deleted") boolean deleted);

    UserEntity findByUsername(@Param("username") String username);

    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.deleted = true, u.modifiedDate = now() " +
            "where u.username =:username and u.deleted = false")
    int deleteByUsername(@Param("username") String username);

    UserEntity findByUsernameAndPasswordAndDeleted(@Param("username") String username, @Param("password") String password, @Param("deleted") boolean deleted);
}
