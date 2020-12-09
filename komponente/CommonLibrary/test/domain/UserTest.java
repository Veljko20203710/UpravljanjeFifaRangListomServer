/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import exceptions.IllegalUserState;
import junit.framework.TestCase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Veljko
 */
public class UserTest {

    User user;

    @BeforeTest
    public void set_up() throws IllegalUserState {
        user = new User(1, "Veljko");
        user.setActive(true);
        user.setAdministator(true);
    }

    @Test(expectedExceptions = IllegalUserState.class)
    public void createUserWithEmptyUsername() throws IllegalUserState {
        user = new User(1, "");
    }

    @Test(expectedExceptions = IllegalUserState.class)
    public void emptyUsernameSetter() throws IllegalUserState {
        user.setUsername("");
    }

    @Test
    public void testUsernameGetterAndSetters() throws IllegalUserState {
        String username = "username";
        user.setUsername(username);

        TestCase.assertEquals(user.getUsername(), username);
    }

    @Test(expectedExceptions = IllegalUserState.class)
    public void emptyPasswordSetter() throws IllegalUserState {
        user.setPassword("");
    }

    @Test
    public void testPasswordGetterAndSetters() throws IllegalUserState {
        String username = "password";
        user.setUsername(username);

        TestCase.assertEquals(user.getUsername(), username);
    }

    @Test
    public void equalUsers() throws IllegalUserState {
        User user2 = new User(1, "Veljko");
        user2.setActive(true);
        user2.setAdministator(true);

        TestCase.assertEquals(user, user2);
    }

    @Test
    public void notEqualUsers() throws IllegalUserState {
        User user2 = new User(1, "Veljko2");
        TestCase.assertNotSame(user, user2);
    }

}
