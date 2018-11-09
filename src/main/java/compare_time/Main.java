package compare_time;

import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


        int size = 100;
        Random random = new Random(new Date().getTime());

        Integer[] array = randomArray(size);

        Integer toBeFound = random.nextInt();

        SingleTestResult result = measureTime(array, toBeFound);
        System.out.println(String.format("%f", result.timeForArraySeconds));
        System.out.println(String.format("%f", result.timeForBstSeconds));
    }

    private static Integer[] randomArray(int size) {
        Random random = new Random(new Date().getTime());
        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static SingleTestResult measureTime(Integer[] array, int toBeFound) {
        BstTree bst = new BstTree();
        for (Integer i : array) {
            bst.insert(i);
        }

        long before = System.nanoTime();
        boolean foundInArray = findInArray(array , toBeFound);
        long after = System.nanoTime();
        long elapsedNanoSeconds = after - before;
        double timeForArray = Utils.nanoToSeconds(elapsedNanoSeconds);

        before = System.nanoTime();
        boolean foundInBst = findInBst(bst, toBeFound);
        after = System.nanoTime();
        elapsedNanoSeconds = after - before;
        double timeForBst = Utils.nanoToSeconds(elapsedNanoSeconds);

        SingleTestResult result = new SingleTestResult();
        result.timeForArraySeconds = timeForArray;
        result.timeForBstSeconds = timeForBst;
        return result;
    }

    private static boolean findInBst(BstTree bst, int toBeFound) {
        return bst.contains(toBeFound);
    }

    private static boolean findInArray(Integer[] array, Integer toBeFound) {
        for (int j = 0; j < array.length; j++) {
            if(array[j].equals(toBeFound)) {
                return true;
            }
        }
        return false;
    }
}
