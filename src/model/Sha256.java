package model;

import java.security.MessageDigest;

public class Sha256 {

    public String sha256(String data_input){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(data_input.getBytes("UTF-8"));
            StringBuffer hex_tring = new StringBuffer();
            for(int i = 0; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hash.length == 1)
                    hex_tring.append("0");
                hex_tring.append(hex);
            }
            return hex_tring.toString();
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
