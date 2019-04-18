package com.training.model.dao.impl;

import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.UserDao;
import com.training.model.entity.User;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

// TODO: add mockito
public class JDBCUserDaoTest {

    private static final Logger log = Logger.getLogger(JDBCUserDaoTest.class);

    @Test
    public void create() {
        try(UserDao dao = DaoFactory.getInstance().createUserDao()){
            User user = new User();
            user.setNickname("admin");
            user.setPassword("1111");
            user.setRole(1);
            if (dao.create(user)){
                log.info("user was registered");
            } else {
                log.info("user registration failed");
            }
        } catch (Exception e){
            fail("there was an unexpected exception");
            log.info("credit was" + e.getMessage());
        }
    }

    @Test
    public void findById() {
        try(UserDao dao = DaoFactory.getInstance().createUserDao()){
            Optional<User> user = dao.findById(1);
            if (user.isPresent()){
                log.info("user[1] = {" + user.get().getId() + ", " + user.get().getNickname() + ", " +
                        user.get().getPassword() + ", " +
                        user.get().getRole() + ", " + user.get().getSpendMoney() + "}");
            } else {
                log.info("user search failed");
            }
        } catch (Exception e){
            fail("there was an unexpected exception");
            log.info("credit was" + e.getMessage());
        }
    }

    @Test
    public void findByNickname() {
        try(UserDao dao = DaoFactory.getInstance().createUserDao()){
            Optional<User> user = dao.findByNickname("admin");
            if (user.isPresent()){
                log.info("user[1] = {" + user.get().getId() + ", " + user.get().getNickname() + ", " +
                        user.get().getPassword() + ", " +
                        user.get().getRole() + ", " + user.get().getSpendMoney() + "}");
            } else {
                log.info("user search failed");
            }
        } catch (Exception e){
            fail("there was an unexpected exception");
            log.info("credit was" + e.getMessage());
        }
    }

    @Ignore
    @Test
    public void findAll() {
    }

    @Ignore
    @Test
    public void update() {
    }

    @Ignore
    @Test
    public void delete() {
    }
}