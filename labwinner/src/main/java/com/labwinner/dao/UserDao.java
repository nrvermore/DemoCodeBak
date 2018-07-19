package com.labwinner.dao;

import java.util.List;

import com.labwinner.security.SysUser;

public interface UserDao {
    public List<SysUser> findByUserName(String username);
}
