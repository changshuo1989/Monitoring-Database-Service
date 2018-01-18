package com.db.service.utility;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptTool {
	
	// generate salt seed  
    private static final int SALT_SEED = 12;
    
    
    public static String encrypt(String plainText) throws Exception{
    	return BCrypt.hashpw(plainText, BCrypt.gensalt(SALT_SEED));
    }
    
    public static boolean check(String candidate, String hashed) throws Exception{
    	if(BCrypt.checkpw(candidate, hashed)){
    		return true;
    	}
    	return false;
    }

}
