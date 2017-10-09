package hlesson2.test;

import org.junit.Assert;
import org.junit.Test;

import static hlesson2.services.UserDao.CreateUser;
import static hlesson2.services.UserService.getUser;
import static hlesson2.services.UserService.loadUser;

public class Test2 {
    @Test
    public void getUserTest(){
        Assert.assertNotNull(getUser(2));
    }

    @Test
    public void loadUserTest(){
        Assert.assertNotNull(loadUser(2));
    }
}
