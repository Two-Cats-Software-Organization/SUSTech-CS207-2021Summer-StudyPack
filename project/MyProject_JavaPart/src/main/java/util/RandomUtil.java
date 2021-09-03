package util;

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();
    public static int getRandomIntBetween(int start, int end){//左右都可以取到
        return start + random.nextInt(1+end-start);
    }

}
