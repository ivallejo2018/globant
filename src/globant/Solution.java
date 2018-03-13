package globant;

import java.util.Scanner;

interface PerformOperation {
	boolean check(int a);
}

class MyMath {
	public static boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}
}

public class Solution {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int testCases = Integer.parseInt(s.nextLine());
	    while(testCases > 0){
	        int numEx = s.nextInt();
	        int input = s.nextInt();
	         
	 		if(numEx == 1) {
	 			if(MyMath.checker(num -> num % 2 != 0, input)) {
	 				System.out.println("ODD");
	 			} else {
	 				System.out.println("EVEN");
	 			}
	 		} else if(numEx == 2) {
				if(MyMath.checker(num -> {
					for(int i = 2; i <= num / 2; i++) {
						if(num % i == 0) return false;
					}
					return true;
				}, input)) {
					System.out.println("PRIME");
				} else {
					System.out.println("COMPOSITE");
				}
	 		} else {
				if(MyMath.checker(num -> { 
					char chars[] = String.valueOf(num).toCharArray();
					int i = 0;
					int j = chars.length - 1;
					while(i < j) {
						if(chars[i] != chars[j]) return false;
						i++; j--;
					}
					return true;
				}, input)) {
					System.out.println("PALINDROME");
				} else {
					System.out.println("NOT PALINDROME");
				}
	 		}
			
	         testCases--;
		}		
	}

}
