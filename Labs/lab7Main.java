
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

		permute(2);

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
