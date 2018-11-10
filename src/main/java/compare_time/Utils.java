package compare_time;

import java.util.Random;

public class Utils {
    public static double nanoToMilliSeconds(long nanoSeconds) {
        return (double) nanoSeconds / 1000000;
    }

    public static Integer[] randomArray(int size) {
        Random random = new Random(System.nanoTime());
        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }
}
