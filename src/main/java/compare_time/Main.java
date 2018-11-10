package compare_time;

import java.util.Arrays;
import java.util.Random;

import static compare_time.Utils.randomArray;

public class Main {
    public static void main(String[] args) {
        System.out.printf("\tarray\tbst\n");
        for (int i = 10; i < 100000000; i+=1000) {
            TestResult result = runTestForInputOfSize(i, 1, 1);
            System.out.printf("%d\t%.10f\t%.10f\n", i, result.timeForArraySeconds, result.timeForBstSeconds);
        }
    }

    private static TestResult runTestForInputOfSize(int size, int repeatTestCount, int repeatSizeCount) {
        Random random = new Random(System.nanoTime());

        TestResult[] results = new TestResult[repeatSizeCount];
        for (int i = 0; i < repeatSizeCount; i++) {
            Integer[] array = randomArray(size);
            Integer toBeFound = random.nextInt();
            TestResult result = runTest(array, toBeFound, repeatTestCount);
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

        TestResult averageResult = new TestResult();
        averageResult.timeForBstSeconds = averageTimeForBstInSeconds;
        averageResult.timeForArraySeconds = averageTimeForArrayInSeconds;
        return averageResult;
    }

    private static TestResult runTest(Integer[] array, Integer toBeFound, int repeatTestCount) {
        // multi tier jitting
//        runTest(array, toBeFound);
//        runTest(array, toBeFound);
//        runTest(array, toBeFound);

        TestResult[] results = new TestResult[repeatTestCount];
        for (int i = 0; i < repeatTestCount; i++) {
            TestResult result = runTest(array, toBeFound);
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

        TestResult averageResult = new TestResult();
        averageResult.timeForBstSeconds = averageTimeForBstInSeconds;
        averageResult.timeForArraySeconds = averageTimeForArrayInSeconds;
        return averageResult;
    }

    private static TestResult runTest(Integer[] array, int toBeFound) {
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

        TestResult result = new TestResult();
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
