
package com.mycompany.registration2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {
    
    Login login = new Login();
    
    public LoginTest() {
    }

    //Testing Username if correctly formatted or not(valid or invalid) 
    @Test
    public void testCheckUserName() {
        assertTrue(login.checkUserName("kyl_1"));
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    //Testing if Password is correctly formatted and contain the required information 
    @Test
    public  void testCheckPasswordComplexity(){
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(login.checkPasswordComplexity("password"));
       
    }
    //Testing the cellphone number if it is in the correct format
    @Test
    public void testCheckCellPhoneNumber(){
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    @Test
    public void testReturnLoginStatus(){
        String expected = "A successful login";
        String actual = login.returnLoginStatus(true);
        assertEquals(expected,actual);
    }
    @Test
    public void testReturnLoginStatusFailed(){
        String expected = "A failed login";
        String actual = login.returnLoginStatus(false);
        assertEquals(expected,actual);
    }

  }
