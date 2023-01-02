package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        boolean isNewPasswordValid=true;
        if(oldPassword.equals(this.password)){
            if(newPassword.length()<8){
                isNewPasswordValid=false;
            }
            String upperCaseChars="(.*[A-Z].*)";
            if(!newPassword.matches(upperCaseChars)){
                isNewPasswordValid=false;
            }
            String lowerCaseChars="(.*[a-z].*)";
            if(!newPassword.matches(lowerCaseChars)){
                isNewPasswordValid=false;
            }
            String numbers = "(.*[0-9].*)";
            if(!newPassword.matches(numbers)){
                isNewPasswordValid=false;
            }
//            String specialChars = "(.*[!, \",#,$,%,&,',(,),*,+,-,.,/,:, ;, <, =, >, ?, @, [, \\, ], ^, _, `, {, |, }, ~, ].*)";
//            if(!newPassword.matches(specialChars)){
//                isNewPasswordValid = false;
//            }
            if(!isNewPasswordValid){
                isNewPasswordValid=true;
            }
        }
        if(isNewPasswordValid){
            this.password=newPassword;
        }

    }
}
