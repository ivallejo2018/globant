package globant;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JavaDequeExercise {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        Deque<Integer> deque = new ArrayDeque<>(m);

        int i = 0;
        for (; i < m; i++) {
            deque.offer(in.nextInt());
        }

        int maxUniqueIntegers = 0;
        Set<Integer> elements = new HashSet<>(deque);
        maxUniqueIntegers = Math.max(maxUniqueIntegers, elements.size());
        for(;i < n; i++) {
            deque.pollFirst();
            deque.offer(in.nextInt());
            elements.clear();
            elements.addAll(deque);
            maxUniqueIntegers = Math.max(maxUniqueIntegers, elements.size());
        }

        System.out.println(maxUniqueIntegers);
    }
    
}
