package globant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaListExercise {
	
    public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
		int numelements = sc.nextInt();
		List<Integer> result = new ArrayList<>();
		for(int i = 0; i < numelements; i++)
			result.add(Integer.valueOf(sc.next()));
		int queries = sc.nextInt();
		for(int i = 0; i < queries; i++) {
			String cmd = sc.next();
			if(cmd.equals("Insert")) {
				result.add(sc.nextInt(), sc.nextInt());
			} else {
				result.remove(sc.nextInt());
			}	
		}
		
		result.forEach(i -> System.out.print(i + " "));
    }
}
