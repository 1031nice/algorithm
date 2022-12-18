package hw3;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainTest {

    @DisplayName("Numbers - Bubble sort - Ascending")
    @Test
    void t1() {
        int[] nums = {5, 3, 1, 2, 6, 9, 8, 7, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[] actual = IntegerArraySort.sortNumbers(nums, 1, 1);

        Assertions.assertArrayEquals(expected, actual);
    }

    @DisplayName("Numbers - Bubble sort - Descending")
    @Test
    void t2() {
        int[] nums = {5, 3, 1, 2, 6, 9, 8, 7, 4};
        int[] expected = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        int[] actual = IntegerArraySort.sortNumbers(nums, 1, 2);

        Assertions.assertArrayEquals(expected, actual);
    }

    @DisplayName("Numbers - Merge sort - Ascending")
    @Test
    void t3() {
        int[] nums = {5, 3, 1, 2, 6, 9, 8, 7, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Arrays.sort(expected);

        int[] actual = IntegerArraySort.sortNumbers(nums, 2, 1);

        Assertions.assertArrayEquals(expected, actual);
    }

    @DisplayName("Numbers - Merge sort - Descending")
    @Test
    void t4() {
        int[] nums = {5, 3, 1, 2, 6, 9, 8, 7, 4};
        int[] expected = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        int[] actual = IntegerArraySort.sortNumbers(nums, 2, 2);

        Assertions.assertArrayEquals(expected, actual);
    }

    @DisplayName("Words - Bubble sort - Ascending")
    @Test
    void t5() {
        String[] strings = {"bird", "apple", "chair", "descending", "air", "zoo", "egg"};
        String[] expected = {"air", "apple", "bird", "chair", "descending", "egg", "zoo"};

        String[] actual = StringArraySort.sortStrings(strings, 1, 1);

        Assertions.assertArrayEquals(expected, actual);
    }

    @DisplayName("Words - Bubble sort - Descending")
    @Test
    void t6() {
        String[] strings = {"bird", "apple", "chair", "descending", "air", "zoo", "egg"};
        String[] expected = {"zoo", "egg", "descending", "chair", "bird", "apple", "air"};

        String[] actual = StringArraySort.sortStrings(strings, 1, 2);

        Assertions.assertArrayEquals(expected, actual);
    }

    @DisplayName("Words - Merge sort - Ascending")
    @Test
    void t7() {
        String[] strings = {"bird", "apple", "chair", "descending", "air", "zoo", "egg"};
        String[] expected = {"air", "apple", "bird", "chair", "descending", "egg", "zoo"};

        String[] actual = StringArraySort.sortStrings(strings, 2, 1);

        Assertions.assertArrayEquals(expected, actual);
    }

    @DisplayName("Words - Merge sort - Descending")
    @Test
    void t8() {
        String[] strings = {"bird", "apple", "chair", "descending", "air", "zoo", "egg"};
        String[] expected = {"zoo", "egg", "descending", "chair", "bird", "apple", "air"};

        String[] actual = StringArraySort.sortStrings(strings, 2, 2);

        Assertions.assertArrayEquals(expected, actual);
    }
}