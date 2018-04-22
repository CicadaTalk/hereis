package edu.scu.hereis.service;

import edu.scu.hereis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserService userService;

    /**
     * 测试通过
     */
    @Test
    public void deleteUser() {

        userService.deleteUser("hi");

    }

    /**
     * 测试通过
     */
    @Test
    public void addUser() {

        User user = new User();
        user.setHereisId("hi");
        user.setRole(UserService.UNAUTHORIZED);
        user.setImgPath("./hi.jpg");

        userService.addUser(user);
    }

    /**
     * 测试通过
     */
    @Test
    public void getUserHereisId() {

        User user = userService.getUserHereisId("haha");
        System.out.println(user.getHereisId() + user.getImgPath() + user.getRole());
    }

    /**
     * 测试通过
     */
    @Test
    public void setUserAuthority() {

        userService.setUserAuthority("haha");

        User user = userService.getUserHereisId("haha");
        System.out.println(user.getHereisId() + user.getImgPath() + user.getRole());
    }

    /**
     * 测试通过
     */
    @Test
    public void cancelUserAuthority() {

        userService.cancelUserAuthority("haha");

        User user = userService.getUserHereisId("haha");
        System.out.println(user.getHereisId() + user.getImgPath() + user.getRole());
    }

    /**
     * 测试通过
     */
    @Test
    public void updateUser() {
        User user = new User();
        user.setHereisId("haha");
        user.setImgPath("./haha2.jpg");

        userService.updateUser(user);

        user = userService.getUserHereisId("haha");
        System.out.println(user.getHereisId() + user.getImgPath() + user.getRole());
    }
}