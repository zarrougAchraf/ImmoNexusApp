package tn.devteam.immonexus.Utility;

import java.util.Random;

public class AnnouncementCode {
    public static String getSmsCode(){
        Random r = new Random();
        int numbers = 100000 + (int)(r.nextFloat() * 899900);
        return String.valueOf(numbers);
    }
}
