package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User DAO
 *
 * @author yellow013
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    @Query("SELECT '*' FROM #{#entityName} e WHERE "
            + " e.username = :username "
            + " OR e.email = :email "
            + " OR e.phone = :phone "
            + " AND e.password = :password")
    List<UserEntity> queryBy(@Param("username") String username,
                             @Param("email") String email,
                             @Param("phone") String phone,
                             @Param("password") String password);

}
