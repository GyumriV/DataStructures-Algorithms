// Vardan Vardanyan
// CSC 130
// Assignment 3

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static final int SIZE = 5000;
	
	public static void main(String[] args) {
		String input = "";
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		minHeap MinHeap = new minHeap(SIZE);
		minHeap MinHeap2 = new minHeap(SIZE);
		int arr[] = new int[SIZE];
		for(int i = 0;i<arr.length;i++) {
			arr[i] = rand.nextInt(SIZE);
		}
		for(int i = 0;i<arr.length;i++) {
			MinHeap.insert(arr[i]);
		}
		
		do {
			long startTime = System.nanoTime();
			while(MinHeap.size != 0) {
				int removedNum = MinHeap.removeMin();
				System.out.printf("\nThe process with a priority of %d is now scheduled to run!", removedNum);
				MinHeap2.insert(removedNum);
				System.out.printf("\nThe process with a priority %d has run out of its timeslice!", removedNum);
			}
			long endTime = System.nanoTime();
			long elapsedTime = endTime - startTime;
			double seconds = (double)elapsedTime/1_000_000_000.0;
			System.out.printf("\nTime passed: %.2f seconds", seconds);
			System.out.println("\nPlease press \"Enter\" to start the next round!\n");
			for(int i = 0; i < SIZE;i++) {
				MinHeap.insert(MinHeap2.removeMin());
			}
			input = scan.nextLine();
		}while(input.equals(""));
	}
}
