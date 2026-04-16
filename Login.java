
package com.mycompany.registration2;
//private String 1Username;

public class Login {
     //Check username
    public boolean checkUserName(String userName){
        return userName.contains("_") &&userName.length() <=5;
    }
    
    //Check Password
   public boolean checkPasswordComplexity(String password){
    return password != null 
            && password.matches(".*[0-9].*") 
            && password.length() >=8 
            && password.matches(".*[!@#$%].*") 
            && password.matches(".*[A-Z].*");   
   }
   
   //Register User
   public String registerUser (String userName, String password){
       if(userName == null || !(userName.contains("_") && userName.length() <=5 )){
           return "The username is incorrectly formatted.";
       }else if (!checkPasswordComplexity(password)){
           return "The password does not meet the complexity requirements.";
       }else{
           return "The two above coonditions have been met, and the user has beeen registered successfully.";
       }
   }
   
   //Check Cellphone Number
   public boolean checkCellPhoneNumber(String phoneNumber){
       return phoneNumber.matches("^\\+\\d{1,3}\\d{1,10}$");
   }
   
   //Login Status
   public boolean loginUser(String userName, String password) {
       return password != null && password.matches(".*[0-9].*") 
               && password.length() >=8 
               && password.matches(".*[!@#$%].*") 
               && password.matches(".*[A-Z].*") 
               && userName.contains("_") 
               &&userName.length() <=5;
   }
   //Login results
   public String returnLoginStatus(boolean loginUser){
       if(loginUser){
           return "A successful login";
       }else {
           return "A failed login";
       }
   }
}