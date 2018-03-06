package globant;

import java.util.Scanner;
import java.util.Stack;

public class BracketsExercise {

    static String isBalanced(String s) {
    	char brackets[] = s.toCharArray();
    	Stack<String> bracketsStack = new Stack<>();
    	
    	for(int i = 0; i < brackets.length; i++) {
    		char bracket = brackets[i];
    		if(bracket == '{' || bracket == '[' || bracket == '(') {
    			bracketsStack.push(String.valueOf(bracket)); 
    		} else {
    			if(bracket == '}') {
    				if(bracketsStack.isEmpty() || !bracketsStack.pop().equals("{")) return "NO";
    			} else if(bracket == ']') {
    				if(bracketsStack.isEmpty() || !bracketsStack.pop().equals("[")) return "NO";
    			} else {
    				if(bracketsStack.isEmpty() || !bracketsStack.pop().equals("(")) return "NO";
    			}
    		}
    	}
    	
        return bracketsStack.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            String s = in.next();
            String result = isBalanced(s);
            System.out.println(result);
        }
        in.close();
    }
    
}
