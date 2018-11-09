package compare_time;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static compare_time.Utils.randomArray;

public class Main {
    public static void main(String[] args) {
        System.out.printf("\tarray\tbst\n");
        for (int i = 10; i < 100000000; i+=1000) {
            SingleTestResult result = repeatForSize(1, 1, i);

            System.out.printf("%d\t%.10f\t%.10f\n", i, result.timeForArraySeconds, result.timeForBstSeconds);
        }
    }

    private static SingleTestResult repeatForSize(int generateThisManyDataSets, int repeatRunForSingleDataSet, int size) {
        Random random = new Random(System.nanoTime());

        SingleTestResult[] results = new SingleTestResult[generateThisManyDataSets];
        for (int i = 0; i < generateThisManyDataSets; i++) {
            Integer[] array = randomArray(size);
            Integer toBeFound = random.nextInt();
            SingleTestResult result = runTestsMultipleTimes(array, toBeFound, repeatRunForSingleDataSet);
            results[i] = result;
        }

        double averageTimeForArrayInSeconds = Arrays.stream(results)
                .mapToDouble(x -> x.timeForArraySeconds)
                .average()
                .getAsDouble();

        double averageTimeForBstInSeconds = Arrays.stream(results)
                .mapToDouble(x -> x.timeForBstSeconds)
                .average()
                .getAsDouble();

        SingleTestResult averageResult = new SingleTestResult();
        averageResult.timeForBstSeconds = averageTimeForBstInSeconds;
        averageResult.timeForArraySeconds = averageTimeForArrayInSeconds;
        return averageResult;
    }

    private static SingleTestResult runTestsMultipleTimes(Integer[] array, Integer toBeFound, int repeatRunForSingleDataSet) {
        // multi tier jitting
//        measureTime(array, toBeFound);
//        measureTime(array, toBeFound);
//        measureTime(array, toBeFound);

        SingleTestResult[] results = new SingleTestResult[repeatRunForSingleDataSet];
        for (int i = 0; i < repeatRunForSingleDataSet; i++) {
            SingleTestResult result = measureTime(array, toBeFound);
//            System.out.printf("\t%f %f\n", result.timeForArraySeconds, result.timeForBstSeconds);
            results[i] = result;
        }

        double averageTimeForArrayInSeconds = Arrays.stream(results)
                .mapToDouble(x -> x.timeForArraySeconds)
                .average()
                .getAsDouble();

        double averageTimeForBstInSeconds = Arrays.stream(results)
                .mapToDouble(x -> x.timeForBstSeconds)
                .average()
                .getAsDouble();

        SingleTestResult averageResult = new SingleTestResult();
        averageResult.timeForBstSeconds = averageTimeForBstInSeconds;
        averageResult.timeForArraySeconds = averageTimeForArrayInSeconds;
        return averageResult;
    }

    private static SingleTestResult measureTime(Integer[] array, int toBeFound) {
        BstTree bst = new BstTree();
        for (Integer i : array) {
            bst.insert(i);
        }

        long before = System.nanoTime();
        boolean foundInArray = findInArray(array, toBeFound);
        long after = System.nanoTime();
        long elapsedNanoSeconds = after - before;
        double timeForArray = Utils.nanoToSeconds(elapsedNanoSeconds);

        before = System.nanoTime();
        boolean foundInBst = findInBst(bst, toBeFound);
        after = System.nanoTime();
        elapsedNanoSeconds = after - before;
        double timeForBst = Utils.nanoToSeconds(elapsedNanoSeconds);
        if (foundInArray != foundInBst) {
            throw new RuntimeException("something is clearly wrong");
        }

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
