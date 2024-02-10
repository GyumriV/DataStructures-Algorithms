import java.util.Scanner;

public class Fib {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a number for n");
		long n = in.nextLong();
		System.out.println("would you like to use Recursive or Looping?\n1)Recursive\n2)Loop");
		int choice = in.nextInt();
		switch(choice) {
		case 1:
			long startTime = System.currentTimeMillis();
			System.out.println(fibRecursive(n));
			long endTime = System.currentTimeMillis();
			System.out.println("Runtime: " + (endTime - startTime) + " milliseconds.");
			break;
		case 2:
			startTime = System.currentTimeMillis();
			System.out.println(fibLoop(n));
			endTime = System.currentTimeMillis();
			System.out.println("Runtime: " + (endTime - startTime) + " milliseconds.");
			break;
		default:
			System.out.println("you did not enter a right selection, enter 1 for Recursion, 2 for Looping");
		}
	}
	
	static long fibRecursive(long n) {
		if(n <= 1)
			return n;
		return fibRecursive(n-1) + fibRecursive(n-2);
	}
	
	static long fibLoop(long n) {
		long count  = 0, sum = 0, num = 1;
		if(n == 0)
			return count;
		for(int i = 2; i <=n; i++) {
			sum = count + num;
			count = num;
			num = sum;
		}
		return num;
	}
}
