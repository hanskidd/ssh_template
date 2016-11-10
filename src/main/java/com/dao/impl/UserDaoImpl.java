package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}
