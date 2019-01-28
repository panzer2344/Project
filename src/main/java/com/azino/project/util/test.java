package com.azino.project.util;
import java.util.Base64;

public class test {
    public static void main(String[] args) {

        try {
            byte[] bytes = "Hello, World!".getBytes("UTF-8");
            String encoded = Base64.getEncoder().encodeToString(bytes);
            byte[] decoded = Base64.getDecoder().decode(encoded);

            System.out.println(bytes);
            System.out.println(encoded);
            System.out.println(decoded);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
