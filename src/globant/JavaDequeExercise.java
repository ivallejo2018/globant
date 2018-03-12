package globant;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaDequeExercise {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        Deque<Integer> deque = new ArrayDeque<>(m);

        Map<Integer, Integer> elements = new HashMap<>();
	    int i = 0;
	    for (; i < m; i++) {
	    	int next = in.nextInt();
	    	deque.offer(next);
	    	elements.put(next, elements.getOrDefault(next, 0) + 1);
	    }  
	    int max = elements.size();
	    for(;i < n; i++) {
	    	int first = deque.pollFirst();
	    	if(!elements.remove(first, 1))elements.put(first, elements.get(first) - 1);
	    	int next = in.nextInt();
	    	deque.offer(next);
	    	elements.put(next, elements.getOrDefault(next, 0) + 1);
	    	max = Math.max(max, elements.size());
	    }
	    System.out.println(max);
    }
    
}
