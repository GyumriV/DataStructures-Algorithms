//Vardan Vardanyan
//CSC 130 Assignment 4

import java.util.Random;
import java.util.Scanner;


public class Main {
	public static final int SIZE = 5000;
	public static void main(String[] args) {
		String input = "";
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		int arr[] = new int[SIZE];
		int heapArr[] = new int[SIZE];
		int mergeArr[] = new int[SIZE];
		int quickArr[] = new int[SIZE];
		for(int i=0; i < arr.length;i++) {
			arr[i] = rand.nextInt(SIZE);
		}
		copy(arr, heapArr);
		copy(arr, mergeArr);
		copy(arr, quickArr);
		
		sorting startSort = new sorting();
		do {
			//heapsort
			long start = System.nanoTime();
			startSort.heapSort(heapArr);
			long finish = System.nanoTime();
			long totalTimeHeap = finish - start;
			double mSecondHeap = (double)totalTimeHeap / 1000000.0;	
			System.out.printf("It took %.2f msecs to heapsort the array\n", mSecondHeap); 
			//mergesort
			start = System.nanoTime();
			startSort.mergeSort(mergeArr, mergeArr.length);
			finish = System.nanoTime();
			long totalTimeMerge = finish - start;
            double mSecondMerge = (double) totalTimeMerge / 1000000.0;
			System.out.printf("It took %.2f msecs to mergesort the array\n", mSecondMerge);
			//quicksort
			start = System.nanoTime();
			startSort.quickSort(quickArr, 0, quickArr.length-1);
			finish = System.nanoTime();
            long totalTimeQuick = finish - start;
			double mSecondQuick = (double) totalTimeQuick / totalTimeMerge / 1000000.0;
			System.out.printf("It took %.2f msecs to quicksort the array\n", mSecondQuick);
			
			input = scan.nextLine();
			if(input.equals("")) {
				copy(arr, heapArr);
				copy(arr, mergeArr);
				copy(arr, quickArr);
			}
		}while(input.equals(""));
		
	}
	
	public static void copy(int arr1[], int arr2[]) {
		for(int i= 0; i < SIZE; i++) {
			arr2[i] = arr1[i];
		}
	}
}
