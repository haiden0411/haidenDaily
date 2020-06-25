package com.huawei.springboot.service;
import com.huawei.springboot.domain.SysUser;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.domain.vo.RegisterReqVO;
import com.huawei.springboot.domain.vo.UpdateUserReqVO;
/**
 * Author：胡灯
 * Date：2020-06-02 22:50
 * Description：<描述>
 */
public interface UserService
{
    public void printUser(User user);

    SysUser getUserInfo(String id);

    String register(RegisterReqVO vo);

    String updateUserInfo(UpdateUserReqVO vo);

    String deletedUserInfo(String id);
}
