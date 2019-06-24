package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AdminUserType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 张占伟
 * @date 2019/3/21 14:40
 *  后台添加三方用户 类型加以区别权限
 */
@Mapper
@Repository
public interface AdminUserTypeMapper {

    int deleteByPrimaryKey(Integer adminUserTypeId);

    int insert(AdminUserType record);

    AdminUserType selectByPrimaryKey(Integer adminUserTypeId);

    List<AdminUserType> selectAll();

    int updateByPrimaryKey(AdminUserType record);

    /**
     * 获取当前角色绑定的用户个数
     * @param adminUserTypeId
     * @return
     */
    int getCountUserByTypeId(@Param("adminUserTypeId")int adminUserTypeId);

    /**
     * 获取用户角色的个数
     * @return
     */
    int getCount();

    /**
     * 查询用户类型根据名字
     * @param name
     * @return
     */
    AdminUserType selectByName(@Param("name")String name);
}