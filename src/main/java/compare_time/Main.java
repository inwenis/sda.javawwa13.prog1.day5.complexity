package compare_time;

import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random(new Date().getTime());

        int size = 100;

        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }

        int toBeFound = 12;

        SingleTestResult result = measureTime(array, toBeFound);
        System.out.println(String.format("%f", result.timeForArraySeconds));
        System.out.println(String.format("%f", result.timeForBstSeconds));
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
