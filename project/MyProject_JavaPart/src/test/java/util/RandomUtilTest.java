package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomUtilTest {

    @Test
    void getRandomIntBetween() {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtil.getRandomIntBetween(0,3));
        }
    }
}