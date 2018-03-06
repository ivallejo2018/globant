package globant;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaMapExercise {

	   public static void main(String []args){
			Scanner in = new Scanner(System.in);
		    int n = in.nextInt();
		    in.nextLine();
		    Map<String, String> phoneBook = new HashMap<>();
		    for(int i = 0; i < n; i++) {
		        String name = in.nextLine();
		        String phone = in.nextLine();
		        phoneBook.put(name, phone);
		    }
		    StringBuilder output = new StringBuilder();
		    while(in.hasNext()) {
		    	String name = in.nextLine();
		    	String value = phoneBook.getOrDefault(name, "Not found");
		    	output.append(!value.equals("Not found") ? 
		    			name + "=" + value : 
		    				value); 
		    	output.append("\n");
		    }	
		    
		    System.out.println(output.toString());
	   }
	   
}
