package com.huawei.springboot.mapper;

import com.huawei.springboot.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByUsername(String username);
}