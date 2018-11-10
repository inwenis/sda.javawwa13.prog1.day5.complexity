package compare_time;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

import java.util.Arrays;

public class Statistics {
    public double average;
    public double median;
    public double stdDev;

    public static Statistics compute(double[] array) {
        double[] sorted = Arrays.stream(array)
                .sorted()
                .toArray();

        double average = Arrays.stream(sorted)
                .average()
                .getAsDouble();

        double median = computeMedian(sorted);

        double stdDev = new StandardDeviation().evaluate(sorted);

        Statistics stats = new Statistics();
        stats.average = average;
        stats.median = median;
        stats.stdDev = stdDev;
        return stats;
    }

    public static double computeMedian(double[] array) {
        Arrays.sort(array);
        double median;
        if (array.length % 2 == 0)
            median = (array[array.length/2] + array[array.length/2 - 1])/2;
        else
            median = array[array.length/2];
        return median;
    }

    private static void computeStatistics(TestResult[] results) {
        // std dev.
        // average without outlines
        // media without outlines
        // sort results

//        computeStatistics();
//
//        // remove outsiders
//        double[] timesForArrayWithOutOutsiders = Arrays.stream(timesForArraySorted)
//                .filter(x -> x > averageTimeForArraySeconds - 2 * stdDevArray)
//                .filter(x -> x < averageTimeForArraySeconds + 2 * stdDevArray)
//                .toArray();
//
//        double[] timesForBstWithOutOutsiders = Arrays.stream(timesForBstSorted)
//                .filter(x -> x > averageTimeForBstSeconds - 2 * stdDevBst)
//                .filter(x -> x < averageTimeForBstSeconds + 2 * stdDevBst)
//                .toArray();
    }
}
