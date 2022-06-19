package com.ownproject.Sorganizer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT roles.role_id, roles.name FROM serviceorganizerjpa.roles INNER JOIN  users_roles ON roles.role_id=users_roles.role_id where users_roles.user_id=:userId",
            nativeQuery = true)
    Set<Role> findByUserId(@Param("userId") Integer userId);

  /*  @Select("SELECT roles.role_id, roles.name FROM serviceorganizer.roles INNER JOIN  users_roles ON roles.role_id=users_roles.role_id where users_roles.user_id=#{userId}")
    //Set<Role> getRoles(Integer userId);

//   @Select("SELECT * from roles where role_id=#{roleId}")
   // Role getRole(int roleId);

    @Select("SELECT role_id,name  FROM roles")
  //  Set<Role> allRoles();

    @Insert("INSERT into users_roles (user_id,role_id) VALUES (#{userId}, #{roleId}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
  //  int addRole(int userId, int roleId);

    @Delete("DELETE FROM users_roles WHERE users_roles.user_id=#{userId} and users_roles.role_id=#{roleId} ")
   int removeRole(Integer userId,Integer roleId);
*/
}
