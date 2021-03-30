package eecs2030.lab7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Requirement:
 * You are required to implement all methods recursively.
 * You receive a zero if there is any occurrence of a loop (e.g., for, while).
 */
public class RecursiveMethods {

	private static final double ASCII_ZERO = 48.0;
	private static final String LETTERS = "ABCDEFGHIJ";

	public static void permute(int n) {
		if (n == 1)
			System.out.println(LETTERS.substring(0, n));
		else
			System.out.println(permuteHelper1(LETTERS.substring(0, n), n));
	}

	private static String permuteHelper1(String s, int n) {
		if (n == 0)
			return "";
		String output = permuteHelper2(s.substring(1), 0, s.charAt(0));
		return output + permuteHelper1(s.substring(1).concat(s.substring(0, 1)), --n);

	}

	private static String permuteHelper2(String s, int n, char c) {
		if (n == s.length())
			return "";
		return c + s + "\n" + permuteHelper2(s.substring(1).concat(s.substring(0, 1)), ++n, c);
	}

	public static double parseDouble(String s) {
		double total = 1;
		if (s.charAt(0) == '-') {
			total = -1;
			s = s.substring(1, s.length());
		}
		int decimal = decimalIndex(s);
		double left = 0.0;
		double right = 0.0;
		if (decimal < 0)
			left = parsePart1(s, 1);
		else {
			left = parsePart1(s.substring(0, decimal), 1);
			right = parsePart2(s.substring(decimal + 1, s.length()), 10);
		}

		total *= (left + right);

		return total;
	}

	private static int decimalIndex(String s) {
		if (s.length() == 0)
			return Integer.MIN_VALUE;
		else if (s.charAt(0) == '.')
			return 0;
		else
			return 1 + decimalIndex(s.substring(1, s.length()));
	}

	private static double parsePart1(String s, int n) {
		if (s.length() == 0)
			return 0;
		else
			return ((s.charAt(s.length() - 1) - ASCII_ZERO) * n) + parsePart1(s.substring(0, s.length() - 1), n * 10);
	}

	private static double parsePart2(String s, int n) {
		if (s.length() == 0)
			return 0;
		else
			return ((s.charAt(0) - ASCII_ZERO) / n) + parsePart2(s.substring(1, s.length()), n * 10);
	}

	public static int lenLongSubList(List<Integer> list) {
		return lenLongSubListHelper(list, list.remove(0));
	}

	private static int lenLongSubListHelper(List<Integer> list, int i) {
		if (list.size() == 0)
			return 1;
		int sub = 0;
		if (list.get(0) > i)
			sub = 1;
		return sub + lenLongSubListHelper(list, list.remove(0));
	}

	public static int lenLongSubArr(int[] arr) {
		return lenLongSubArrHelper(arr, 0, 1);
	}

	private static int lenLongSubArrHelper(int[] arr, int start, int finish) {
		if (finish == arr.length)
			return 1;
		int sub = 0;
		if (arr[start] < arr[finish])
			sub = 1;
		return sub + lenLongSubArrHelper(arr, ++start, ++finish);

	}

	public static int[] mergeSortedArrays(int[] left, int[] right) {
		int[] merged = new int[left.length + right.length];
		recursiveFill(merged, Integer.MAX_VALUE, 0);
		recursiveCopy(merged, left, 0);
		recursiveCopy(merged, right, 0);

		return merged;
	}

	private static void recursiveFill(int[] dest, int value, int n) {
		if (n == dest.length)
			return;
		dest[n] = value;
		recursiveFill(dest, value, ++n);
	}

	private static void recursiveCopy(int[] dest, int[] src, int n) {
		if (n == src.length)
			return;
		mergeArrayHelper(dest, src[n], n);
		recursiveCopy(dest, src, ++n);
	}

	private static void mergeArrayHelper(int[] dest, int value, int n) {
		if (n == dest.length)
			return;
		if (dest[n] > value) {
			int temp = dest[n];
			dest[n] = value;
			mergeArrayHelper(dest, temp, ++n);
		} else
			mergeArrayHelper(dest, value, ++n);

	}

	public static List<Integer> mergeSortedLists(List<Integer> left, List<Integer> right) {
		List<Integer> merged = new ArrayList<Integer>();

		if (left.size() == 0)
			return right;
		if (right.size() == 0)
			return left;

		if (left.get(0) < right.get(0))
			merged.add(left.remove(0));
		else
			merged.add(right.remove(0));

		merged.addAll(mergeSortedLists(left, right));

		return merged;
	}

	public static void main(String[] args) {

		int arr[] = { 2, 6, 4, 5, 7 };
		List<Integer> list = new ArrayList<Integer>();
		for (int i : arr)
			list.add(i);
		int a = lenLongSubArr(arr);
		int b = lenLongSubList(list);
		System.out.println("longest subarray: " + a);
		System.out.println("longest sublist: " + b);

		String s = "-0201.03210";
		System.out.println("\n" + s + " ==> " + parseDouble(s) + "\n");

		permute(4);

		int arr1[] = { 1, 3, 5, 7 };
		int arr2[] = { 2, 4, 6, 8, 9 };

		List<Integer> l1 = new ArrayList<Integer>();
		List<Integer> l2 = new ArrayList<Integer>();

		for (int i : arr1)
			l1.add(i);
		for (int i : arr2)
			l2.add(i);

		List<Integer> sorted = mergeSortedLists(l1, l2);

		for (int i : sorted)
			System.out.println(i);

		mergeSortedArrays(arr1, arr2);

	}
}
