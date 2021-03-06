package globant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class LeetCode {

	public static void main(String[] args) {
		LeetCode lc = new LeetCode();
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(2);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(3);
		root.left.right.right = new TreeNode(5);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);
		
		System.out.println("Lowest common ancestor -> " + 
				lc.lowestCommonAncestor(root, root.left, root.left.right).value);
		
		System.out.println("Contains Duplicate I -> " +
				lc.containsDuplicate(new int[]{1,3,2,1}));
		System.out.println("Contains Duplicate II -> " +
				lc.containsNearbyDuplicate(new int[]{-1,-1}, 1));		
		System.out.println("Contains Duplicate III -> " + 
				lc.containsNearbyAlmostDuplicate(new int[]{10,100,11,9}, 1, 2));
		
		System.out.println(lc.numMatchingSubseq("abcdebbe", 
				new String[]{"a", "w", "acb", "bebbe"}));
		System.out.println(lc.numSubarrayBoundedMax(new int[]{2,9,2,5,6},2,8));
		System.out.println(lc.solve(new int[]{1,1,3,4,5,10}));
		
		System.out.println(lc.isBalanced("{"));
//		System.out.println(lc.powerSum(100, 2));
		
//		lc.useOfSet();
		System.out.println(lc.longestCommonPrefix(
				new String[]{"","b"}));
	}

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        
        String longest = new String();
        SortedSet<String> words = new TreeSet<>();
        for(String s : strs) {
            words.add(s);
        }
        
        Iterator<String> it = words.iterator();
        String prev = it.next();
        String next = null;
        while(it.hasNext()) {
            next = it.next();
            
            int i = 0;
            String testStr = prev.substring(0, i);
            if(longest.length() < next.length()) {
                while(testStr.length() < next.length() && i < prev.length()) {
                	if(next.indexOf(prev.substring(0, i + 1)) == -1) break;
                	testStr = prev.substring(0, i + 1);
                	i++;
                }	
            }
           
            if(testStr.length() >= longest.length()) {
                longest = testStr;
            }
            
            prev = next;
        }
        
        return longest;
    }
    
	public void useOfSet() {
		String array[] = new String[]{"hola","alla","mar","alicia","mama","hierro","aliciente","maread","milicia","arma","mareado"};
		SortedSet<String> set = new TreeSet<>();
		for(String word : array) {
			set.add(word);
		}
		
		set.forEach(w -> System.out.print(w + " "));
	}
	
    public static String isBalanced(String s) {
    	Stack<String> stack = new Stack<>();
    	char chars[] = s.toCharArray();
    	
    	int k = 0;
    	while(k < chars.length) {
    		switch(chars[k]) {
    			case '(':
    				stack.push("("); break; 
    			case '{':
    				stack.push("{"); break;
    			case '[':
    				stack.push("["); break;
    			case ')':
    				if(stack.isEmpty() || !stack.pop().equals("(")) return "NO"; break;
    			case '}':
    				if(stack.isEmpty() || !stack.pop().equals("{")) return "NO"; break;
    			case ']':
    				if(stack.isEmpty() || !stack.pop().equals("[")) return "NO"; break;   				
    		}
    		
    		k++;
    	}
    	
    	return stack.isEmpty() ? "YES" : "NO";
    }
    
    public static int powerSum(int X, int N) {
    	
    	int n = (int)Math.pow(X, (double)1/N);

    	int count = 0;
    	for(int i = n; i > 0; i--) {
    		int next = X - (int)Math.pow(i, N);
    		if(next == 0) count++; 
    		else count += powerSum(next, N);
    	}
    	
        return count;
    }
    
    public static String solve(int[] a){
        if(a.length == 1) return "YES";
 	   
 	   int right = 0;
 	   int left = 0;
 	   
 	   for(int i = 1; i < a.length; i++) {
 			left += a[i];
 	   }
 	   
 	   if(right == left) return "YES";
 	   for(int j = 1; j < a.length; j++) {
 			right += a[j-1];
 			left -= a[j];
 			if(right == left) return "YES";
 	   }
 	   
 	   return "NO";
     }
	
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
    	//2,1,4,3	2 3
    	Queue<Integer> firstQueue = new LinkedList<>();
    	for(int i = 0; i < A.length; i++) {
    		firstQueue.add(A[i]);
    	}
    	
        return numSubarrayBoundedMax((LinkedList<Integer>)firstQueue, firstQueue.size(), L, R);
    }
    
    private int numSubarrayBoundedMax(LinkedList<Integer> elements, int n, int L, int R) {
    	
    	if(n == 0) {
    		return 0;
    	}
    	int numSubarrays = 0;
    	int max = Integer.MIN_VALUE;
    	numSubarrays += numSubarrayBoundedMax(elements, n-1, L, R);
		for(int i = 0, j = n; j <= elements.size(); i++, j++) {
			max = elements.subList(i, j).stream().reduce(Math::max).orElse(Integer.MIN_VALUE);
			if(max >= L && max <= R) numSubarrays++;
		}
		
    	return numSubarrays;
    }
    
	public int numMatchingSubseq(String S, String[] words) {
        //abcdebbe	acb bbe	
    	Map<String, List<Integer>> letterPositions = new HashMap<>();
    	char chars[] = S.toCharArray();
    	for(int i = 0; i < chars.length; i++) {
    		List<Integer> positions = letterPositions.getOrDefault(
    				String.valueOf(chars[i]), new ArrayList<>());
    		positions.add(i);
    		letterPositions.put(String.valueOf(chars[i]), positions);
    	}
    	
    	int matches = 0;
    	for(int i = 0; i < words.length; i++) {
    		String word = words[i];
    		char charsOfWord[] = word.toCharArray();
    		int previousPosition = -1;
    		int j = 0;
    		for(; j < charsOfWord.length; j++) {
    			String letter = String.valueOf(charsOfWord[j]);
    			if(!letterPositions.containsKey(letter)) break;
    			List<Integer> positions = letterPositions.get(letter);
    			int index = 0;
    			while(index < positions.size() &&
    					positions.get(index) <= previousPosition) {
    				index++;
    			}
    			if(index > positions.size() - 1) break;
    			previousPosition = positions.get(index);	
    		}
    		if(j == charsOfWord.length)
    			matches++;
    	}
    	return matches;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        this.searchNode(root, p, s1);
        this.searchNode(root, q, s2);
        int diff = s1.size() - s2.size();
        if(diff > 0) {
        	while(diff > 0) {
        		s1.pop();
        		diff--;
        	}
        } else if(diff < 0) {
        	diff = Math.abs(diff);
        	while(diff > 0) {
        		s2.pop();
        		diff--;
        	}       	
        }
        
        while(s1.peek().value != s2.peek().value) {
        	s1.pop(); 
        	s2.pop();
        }
        
        return s1.peek();
    }
    
    public boolean searchNode(TreeNode root, TreeNode node, Stack<TreeNode> stack) {
    	if(root == null)
    		return false;
    	stack.push(root);
    	if(root.value == node.value)
    		return true;    	
    	if(searchNode(root.left, node, stack))
    		return true;
    	if(searchNode(root.right, node, stack)) 
    		return true;
    	stack.pop();
    	return false;
    }
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    	//5,10,21,13,4 
    	NavigableMap<Integer, Integer> counterMap = new TreeMap<>();
        
        for(int i = 0; i < nums.length; i++) {
        
        	if(counterMap.floorKey(nums[i] + t) != null &&
        			Math.abs((long)counterMap.floorKey(nums[i] + t) - (long)nums[i]) <= t &&
        			Math.abs((long)counterMap.floorEntry(nums[i] + t).getValue() - (long)i) <= k) 
        				return true;
        	if(counterMap.ceilingKey(nums[i] - t) != null &&
        			Math.abs((long)counterMap.ceilingKey(nums[i] - t) - (long)nums[i]) <= t &&
        			Math.abs((long)counterMap.ceilingEntry(nums[i] - t).getValue() - (long)i) <= k) 
        				return true;
        	counterMap.put(nums[i], i);
        }
        return false;
    }
    
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> counterMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
        	if(counterMap.getOrDefault(nums[i], 0) == 1) return true;
        	counterMap.put(nums[i], 1);
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> counterMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
        	if(counterMap.getOrDefault(nums[i], -1) != -1 &&
        			Math.abs((long)counterMap.get(nums[i]) - (long)i) <= k) return true;
        	counterMap.put(nums[i], i);
        }
        return false;
    }
}
