package com.training.model.dao.interfaces;

import com.training.model.dao.GenericDao;
import com.training.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findByNickname(String nickname);
}
