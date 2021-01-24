package com.huawei.springboot.service.impl;
import com.huawei.springboot.domain.SysUser;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.domain.pojo.BussinessPerson;
import com.huawei.springboot.domain.vo.LoginReqVo;
import com.huawei.springboot.domain.vo.LoginRespVo;
import com.huawei.springboot.domain.vo.RegisterReqVO;
import com.huawei.springboot.domain.vo.UpdateUserReqVO;
import com.huawei.springboot.exception.BusinessException;
import com.huawei.springboot.mapper.SysUserMapper;
import com.huawei.springboot.service.RedisService;
import com.huawei.springboot.service.UserService;
import com.huawei.utils.PasswordUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
/**
 * Author：胡灯
 * Date：2020-06-02 22:51
 * Description：<描述>
 */
@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisService redisService;
    @Override
    public void printUser(User user)
    {
        if (user == null)
        {
            throw new RuntimeException("检查用户参数为空");
        }
        System.out.println(user);
    }
    @Override
    public SysUser getUserInfo(String id)
    {
        return sysUserMapper.selectByPrimaryKey(id);
    }
    @Override
    public String register(RegisterReqVO vo)
    {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(vo, sysUser);
        sysUser.setCreateTime(new Date());
        sysUser.setId(UUID.randomUUID().toString());
        String salt = PasswordUtils.getSalt();
        String password = PasswordUtils.encode(vo.getPassword(), salt);
        sysUser.setPassword(password);
        sysUser.setSalt(salt);
        int i = sysUserMapper.insertSelective(sysUser);
        if (i != 1)
        {
            return "注册失败";
        }
        return "注册成功";
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateUserInfo(UpdateUserReqVO vo)
    {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(vo, sysUser);
        if (StringUtils.isEmpty(sysUser.getPassword()))
        {
            sysUser.setPassword(null);
        } else
        {
            String salt = PasswordUtils.getSalt();
            String encode = PasswordUtils.encode(sysUser.getPassword(), salt);
            sysUser.setPassword(encode);
            sysUser.setSalt(salt);
        }
        sysUser.setUpdateTime(new Date());
        int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (i != 1)
        {
            return "更新失败";
        }
        return "更新成功";
    }
    @Override
    public String deletedUserInfo(String id)
    {
        SysUser sysUser = new SysUser();
        sysUser.setUpdateTime(new Date());
        sysUser.setDeleted(1);
        sysUser.setId(id);
        int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (i != 1)
        {
            return "操作失败";
        }
        return "操作成功";
    }
    @Override
    public LoginRespVo login(LoginReqVo vo)
    {
        SysUser sysUser = sysUserMapper.selectByUsername(vo.getUsername());
        if (sysUser == null)
        {
            throw new BusinessException(4001005, "不存在该用户，请先注册");
        }
        if (sysUser.getStatus() == 2)
        {
            throw new BusinessException(4001006, "该用户已经被禁用，联系管理员");
        }
        if (!PasswordUtils.matches(sysUser.getSalt(), vo.getPassword(), sysUser.getPassword()))
        {
            throw new BusinessException(4001007, "用户密码不匹配");
        }
        String token = UUID.randomUUID().toString();
        LoginRespVo respVo = new LoginRespVo();
        respVo.setToken(token);
        respVo.setUserId(sysUser.getId());
        redisService.set(token, sysUser.getId(), 300, TimeUnit.SECONDS);
        redisService.set(sysUser.getId(), token, 300, TimeUnit.SECONDS);
        return respVo;
    }
}
