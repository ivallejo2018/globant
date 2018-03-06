package globant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaArrayListExercise {

    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numOfLists = Integer.valueOf(sc.nextLine());
		int indexes[] = new int[numOfLists + 1];
		List<Integer> values = new ArrayList<>();
		
		int countElements = 0;
		indexes[0] = countElements;
		for(int i = 0; i < numOfLists; i++) {
			String[] items = sc.nextLine().split(" ");
			countElements += Integer.valueOf(items[0]);
			for(int j = 1; j < items.length; j++) {
				values.add(Integer.valueOf(items[j]));	
			}
			indexes[i + 1] = countElements;
		}

		int numOfQueries = sc.nextInt();
		StringBuilder output = new StringBuilder();
        
		for(int i = 0; i < numOfQueries; i++) {
			int line = sc.nextInt();
			int index = sc.nextInt();
			int numElements = indexes[line] - indexes[line - 1];
			
			if(index > numElements) {
				output.append("ERROR!");
				output.append("\n");
			} else {
				int indexOfValue = (indexes[line - 1] + index) - 1;
				output.append(
						values.get(indexOfValue));
				output.append("\n");
			}
		}
		
		System.out.print(output.toString());
    }
    
}
