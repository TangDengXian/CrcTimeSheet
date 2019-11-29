package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.User;
import org.sang.bean.Role;

import java.util.List;

/**
 * Created by sang on 2017/12/28.
 */
public interface UserMapper {
    User loadUserByUsername(String username);

    List<Role> getRolesByHrId(Long id);

    int hrReg(@Param("username") String username, @Param("password") String password);

    List<User> getHrsByKeywords(@Param("keywords") String keywords);

    int updateHr(User user);

    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    User getHrById(Long hrId);

    int deleteHr(Long hrId);

    List<User> getAllHr(@Param("currentId") Long currentId);
}
