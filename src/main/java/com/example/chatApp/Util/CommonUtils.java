package com.example.chatApp.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    public static boolean isvalidPassword(String password){

        boolean isValid = true;
        if (password.length() > 15 || password.length() < 8)
        {
//            System.out.println("Password must be less than 20 and more than 8 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
//            System.out.println("Password must have atleast one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
//            System.out.println("Password must have atleast one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
//            System.out.println("Password must have atleast one number");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars ))
        {
//            System.out.println("Password must have atleast one special character among @#$%");
            isValid = false;
        }
        return isValid;
//
//        String regex = "^(?=.*[0-9])"
//                + "(?=.*[a-z])(?=.*[A-Z])"
//                + "(?=.*[@#$%^&+=])"
//                + "(?=\\S+$).{8,20}$";
//
//        // Compile the ReGex
//        Pattern p = Pattern.compile(regex);
//
//        // If the password is empty
//        // return false
//        if (password == null) {
//            return false;
//        }
//
//        // Pattern class contains matcher() method
//        // to find matching between given password
//        // and regular expression.
//        Matcher m = p.matcher(password);
//
//        // Return if the password
//        // matched the ReGex
//        return m.matches();
    }
    public static boolean isvalidemail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isvalidPhonenum(String phoneNum) {
        Pattern p = Pattern.compile("^\\d{10}$");

        Matcher m = p.matcher(phoneNum);

        return (m.matches());
    }
}
