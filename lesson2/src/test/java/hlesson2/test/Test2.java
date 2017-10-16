package hlesson2.test;

import org.junit.Assert;
import org.junit.Test;

import static hlesson2.services.UserService.getUser;
import static hlesson2.services.UserService.loadUser;

public class Test2 {
    @Test
    public void getUserTest(){
        getUser(9999999);
    }

    @Test
    public void loadUserTest(){
        loadUser(99999999);
    }
}
