package compare_time;

import java.util.Arrays;
import java.util.Random;

import static compare_time.Utils.randomArray;

public class Main {
    public static void main(String[] args) {
        System.out.printf("\tarray\tbst\n");
        for (int i = 10; i < 100000000; i+=1000) {
            TestResultWithStats[] result = runTestForInputOfSize(i, 10, 10);
            System.out.printf("%d\t%.10f\t%.10f\n", i, result[0].statsForArray.median, result[0].statsForBst.median);
        }
    }

    private static TestResultWithStats[] runTestForInputOfSize(int size, int repeatTestCount, int repeatSizeCount) {
        Random random = new Random(System.nanoTime());

        TestResultWithStats[] results = new TestResultWithStats[repeatSizeCount];
        for (int i = 0; i < repeatSizeCount; i++) {
            Integer[] array = randomArray(size);
            Integer toBeFound = random.nextInt();
            results[i] = runTest(array, toBeFound, repeatTestCount);
        }

        return results;
    }

    private static TestResultWithStats runTest(Integer[] array, Integer toBeFound, int repeatTestCount) {
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

        double[] timesForArray = Arrays.stream(results)
                .mapToDouble(x -> x.timeForArraySeconds)
                .sorted()
                .toArray();

        double[] timesForBst = Arrays.stream(results)
                .mapToDouble(x -> x.timeForBstSeconds)
                .sorted()
                .toArray();

        Statistics statsForArray = Statistics.compute(timesForArray);
        Statistics statsForBst = Statistics.compute(timesForBst);

        TestResultWithStats result = new TestResultWithStats();
        result.timesForArray = timesForArray;
        result.timesForBst = timesForBst;
        result.statsForArray = statsForArray;
        result.statsForBst = statsForBst;
        return result;
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
