package compare_time;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


        int size = 100000;
        Random random = new Random(new Date().getTime());

        Integer[] array = randomArray(size);
        Integer toBeFound = random.nextInt();

        int repeatRunForSingleDataSet = 10;

        // multi tier jitting
        measureTime(array, toBeFound);
        measureTime(array, toBeFound);
        measureTime(array, toBeFound);

        SingleTestResult[] results = new SingleTestResult[repeatRunForSingleDataSet];
        for (int i = 0; i < repeatRunForSingleDataSet; i++) {
            SingleTestResult result = measureTime(array, toBeFound);
            results[i] = result;
            String formated = String.format("%f %f", result.timeForArraySeconds, result.timeForBstSeconds);
            System.out.println(formated);
        }

        double averageTimeForArrayInSeconds = Arrays.stream(results)
                .mapToDouble(x -> x.timeForArraySeconds)
                .average()
                .getAsDouble();

        double averageTimeForBstInSeconds = Arrays.stream(results)
                .mapToDouble(x -> x.timeForBstSeconds)
                .average()
                .getAsDouble();

        String formated = String.format("%f %f", averageTimeForArrayInSeconds, averageTimeForBstInSeconds);
        System.out.println(formated);
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
