package kg.djedai.store;

import java.util.Random;

/**
 * @author Zhoodar
 * @since 25.06.2016.
 */
public class RandomIdGenerator {
    private static char[] _base62chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static Random _random = new Random();

    public static String getBase36(int length){
        StringBuilder sb = new StringBuilder(length);
        for(int i=0; i<length;i++){
            sb.append(_base62chars[_random.nextInt(36)]);
        }
        return sb.toString();
    }
}
