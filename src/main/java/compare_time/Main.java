package compare_time;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        Integer[] array = new Integer[]{1, 2, 4, 100, 11, 21, 23};
        boolean foundInArray = findInArray(array, 12);

        BstTree bst = new BstTree();

        for (Integer i : array) {
            bst.insert(i);
        }

        boolean foundInBst = findInBst(bst ,12);
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
