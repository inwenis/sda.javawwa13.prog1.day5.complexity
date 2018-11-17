# Computional Complexity

This repo demonstrates the concept of computional complexity by comparing running times of different algorithms.
Test 1 finds a value in an `unsorted array` and `unbalanced binary search tree`.
Test 2 sorts a `int[]` array using custom `bubble sort` and `double pivot quicksort` from `Arrays` class.

## Test 1 - searching

Note that this is a `unbalanced binary search tree` thus the results are not so beautifully simillar to a `log2(n)` function. Searching for a values in a `*balanced* binary search tree` should be closer to a `log2(n)` function.

![Chart - array vs bst](https://github.com/inwenis/sda.javawwa13.prog1.day5.complexity/blob/master/array_vs_bst.png)

![Chart - bst only](https://github.com/inwenis/sda.javawwa13.prog1.day5.complexity/blob/master/bst_only.png)

## Test 2 - sorting

Note that the expected complexity of `bubble sort` is `n^2` and the expected complexity of `quicksort` is `n log2(n)`

![Chart - bubble vs quicksort](https://github.com/inwenis/sda.javawwa13.prog1.day5.complexity/blob/master/bubble_vs_quicksort.png)

![Chart - quicksort only](https://github.com/inwenis/sda.javawwa13.prog1.day5.complexity/blob/master/quicksort_only.png)

