package com.siruiman.crosslogistics.mapper;


import com.siruiman.crosslogistics.model.AdminPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminPermissionMapper {

    /**
     * 根据用户id查出用户所需的权限
     * @param uid
     * @return
     */
    List<AdminPermission> selectByUId(int uid);
}
