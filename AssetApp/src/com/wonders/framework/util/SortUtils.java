package com.wonders.framework.util;

public class SortUtils {

	public static int[] quickSort(int[] data, int i, int j) {
		int pivotIndex = (i + j) / 2;
		swap(data, pivotIndex, j);
		int k = partition(data, i - 1, j, data[j]);
		swap(data, k, j);
		if ((k - i) > 1)
			quickSort(data, i, k - 1);
		if ((j - k) > 1)
			quickSort(data, k + 1, j);
		return data;

	}
	
	private static int partition(int[] data, int l, int r, int pivot) {
		do {
			while (data[++l] < pivot)
				;
			while ((r != 0) && data[--r] > pivot)
				;
			swap(data, l, r);
		} while (l < r);
		swap(data, l, r);
		return l;
	}
	
	public static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
}
