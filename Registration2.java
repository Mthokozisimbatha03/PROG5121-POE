/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registration2 {

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        
        //Ask the user to enter their credintials
        System.out.println("First name: ");
        String name = myInput.nextLine();
        
        System.out.println("Last name: ");
        String lastName = myInput.nextLine();
        
        //Ask the user to enter username with ertain resrictions
        System.out.println("Enter a username");
        String userName = myInput.nextLine();
        
        if(userName.contains("_")&& userName.length() <=5){
            System.out.println("Username has been succefully captured");
        }else{
            System.out.println("Username is not correctly formatted; please ensure that your username cantains "
                    + "an underscore and is n more than five characters in length.");
        }
        
        //May you please enter password
        System.out.println("Enter password: ");
        String password = myInput.nextLine();
        
        if(password != null 
                && password.matches(".*[0-9].*") 
                && password.length() >= 8 
                && password.matches(".*[!@#$%].*") 
                && password.matches(".*[A-Z].*")) 
        {
            System.out.println("Password succesfully captured");
        }else{
            System.out.println("Password is not correctly formatted; please ensure that your password cantains "
                    + "at lest eight characters, a capital letter, a number, and a special character ");
        }
        
        //Ask the user to enter their ceelphone numbers
        System.out.println("Enter cellphone number");
        String number = myInput.nextLine();
        
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        
        if(number != null && matcher.matches() && number.length() ==12){
            System.out.println("Cellphone number succesfully added");
        }else{
            System.out.println("Cellphone number incorrectly formatted or does not contain international code");
        }
        
        if(userName.contains("_") && userName.length() <=5 && password != null 
                && password.length() >=8
                && password.matches(".*[0-9].*") 
                && password.matches(".*[!@#$%].*")){
            System.out.println(" Welcome " + name + " " + lastName + " " + "it is great to see you.");
        }else{
            System.out.println("Username or password incorrect,please try again.");
        }
        //Create a login class next class
}
    }


    

