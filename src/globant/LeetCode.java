package globant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
		
		System.out.println("Contains Duplicate III -> " + 
				lc.containsNearbyAlmostDuplicate(new int[]{-2147483647,2147483647}, 1, 2147483647));
		System.out.println("Contains Duplicate I -> " +
				lc.containsDuplicate(new int[]{1,3,2,1}));
		System.out.println("Contains Duplicate II -> " +
				lc.containsNearbyDuplicate(new int[]{-1,-1}, 1));		
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
    	//5,10,21,13,6 
        Map<Integer, Integer> counterMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
        	if(counterMap.getOrDefault(nums[i], -1) != -1 &&
        			Math.abs((long)counterMap.get(nums[i]) - (long)i) <= k) return true;
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
