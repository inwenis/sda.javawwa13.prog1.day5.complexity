package compare_time;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 4, 100, 11, 21, 23};

        BstTree bst = new BstTree();

        for (Integer i : array) {
            bst.insert(i);
        }

        long before = System.nanoTime();
        boolean foundInArray = findInArray(array ,12);
        long after = System.nanoTime();
        long elapsedNanoSeconds = after - before;
        System.out.println("elapsed nanoseconds: " + elapsedNanoSeconds);

        before = System.nanoTime();
        boolean foundInBst = findInBst(bst, 12);
        after = System.nanoTime();
        elapsedNanoSeconds = after - before;
        System.out.println("elapsed nanoseconds: " + elapsedNanoSeconds);
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
