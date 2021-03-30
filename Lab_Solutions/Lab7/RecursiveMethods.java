package eecs2030.lab7;

import java.util.ArrayList;
import java.util.List;

/*
 * Requirement:
 * You are required to implement all methods recursively.
 * You receive a zero if there is any occurrence of a loop (e.g., for, while).
 * see this link for methods description:
 * https://www.eecs.yorku.ca/course_archive/2018-19/S/2030/labs/lab7
 */
public class RecursiveMethods {

	public static void permute(int n) {
		/* Your Task */
		permute_h1("", "ABCDEFGHIJ".substring(0, n));
	}

	private static void permute_h2(String left, String right, int i) {
		if (i < right.length()) {
			String l = left + right.charAt(i);
			String r = right.substring(0, i) + right.substring(i + 1, right.length());
			permute_h1(l, r);
			permute_h2(left, right, i + 1);
		}
	}

	private static void permute_h1(String left, String right) {
		if (right.length() > 1) {
			permute_h2(left, right, 0);
		} else {
			System.out.println(left);
		}
	}

	public static double parseDouble(String s) {
		/* Your Task */
		if (s.startsWith("-"))
			return -parsePart1(s.substring(1), 0);
		else
			return parsePart1(s, 0);
	}

	private static double parsePart1(String s, int a) {
		if (s.length() == 0)
			return a;
		if (s.charAt(0) == '.') {
			return a + parsePart2(s.substring(1), 0);
		}
		a = a * 10 + (s.charAt(0) - '0');
		return parsePart1(s.substring(1), a);
	}

	private static double parsePart2(String s, int b) {
		if (s.length() == 0)
			return b;
		int a = (s.charAt(0) - '0');
		return b + parsePart2(s.substring(1), a) / 10;
	}

	public static int lenLongSubList(List<Integer> list) {
		/* Your Task */
		return lenLongSubListHelper(list, -1);
	}

	private static int lenLongSubListHelper(List<Integer> list, int max) {
		int n = list.size();
		if (n == 0) {
			return 0;
		}
		int a = 0;
		if (max < list.get(0)) {
			a = 1 + lenLongSubListHelper(list.subList(1, n), list.get(0));
		}
		int b = lenLongSubListHelper(list.subList(1, n), max);
		return Math.max(a, b);
	}

	public static int lenLongSubArr(int[] arr) {
		/* Your Task */
		return lenLongSubArrHelper(arr, -1, 0);
	}

	private static int lenLongSubArrHelper(int[] arr, int v, int k) {
		int n = arr.length;
		if (n <= k) {
			return 0;
		}
		int a = 0;
		if (v < arr[k]) {
			a = 1 + lenLongSubArrHelper(arr, arr[k], k + 1);
		}
		int b = lenLongSubArrHelper(arr, v, k + 1);
		return Math.max(a, b);
	}

	public static int[] mergeSortedArrays(int[] left, int[] right) {
		/* Your Task */
		int[] merge = new int[left.length + right.length];
		mergeSortedArraysHelper(left, right, 0, 0, merge, 0);
		return merge;
	}

	private static void mergeSortedArraysHelper(int[] left, int[] right, int l, int r, int[] merge, int current) {
		if (l < left.length || r < right.length) {
			int x;
			if (l < left.length && r < right.length) {
				if (left[l] <= right[r]) {
					x = left[l++];
				} else {
					x = right[r++];
				}
			} else if (l < left.length) {
				x = left[l++];
			} else {
				x = right[r++];
			}
			merge[current] = x;
			mergeSortedArraysHelper(left, right, l, r, merge, current + 1);
		}
	}

	public static List<Integer> mergeSortedLists(List<Integer> left, List<Integer> right) {
		/* Your Task */
		List<Integer> merge = new ArrayList<>();
		mergeSortedListsHelper(left, right, merge);
		return merge;
	}

	private static void mergeSortedListsHelper(List<Integer> left, List<Integer> right,	List<Integer> merge) {
		if (0 < left.size() || 0 < right.size()) {
			if (0 < left.size() && 0 < right.size()) {
				if (left.get(0) <= right.get(0)) {
					merge.add(left.get(0));
					mergeSortedListsHelper(left.subList(1,left.size()), right, merge);
				} else {
					merge.add(right.get(0));
					mergeSortedListsHelper(left, right.subList(1, right.size()), merge);
				}
			} else { /* L >= left.length || R >= right.length */
				if (0 < left.size()) {
					merge.add(left.get(0));
					mergeSortedListsHelper(left.subList(1, left.size()), right, merge);
				} else { /* R >= right.length */
					merge.add(right.get(0));
					mergeSortedListsHelper(left, right.subList(1, right.size()), merge);
				}
			}
		}
	}

	public static void main(String[] args) {
		permute(6);
		int arr[] = { 2, 4, 5, 7 };
		List<Integer> list = new ArrayList<Integer>();
		for (int i : arr)
			list.add(i);
		int a = lenLongSubArr(arr);
		int b = lenLongSubList(list);
		System.out.println(a);
		System.out.println(b);

		String s = "-0201.03210";
		System.out.println(s + " ==> " + parseDouble(s));
	}
}
