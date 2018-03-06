package globant;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JavaHashSetExercise {

	public static void main(String []args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String [] pair_left = new String[t];
        String [] pair_right = new String[t];
        
        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }
        //Write your code here
        Set<String> names = new HashSet<>();
        int count = 0;
        for(int j = 0; j < pair_left.length; j++) {
        	String name = pair_left[j] + " " + pair_right[j];
        	count += names.add(name) ? 1 : 0;
        	System.out.println(count);
        }        
	}
}
