package globant;

import java.util.Stack;

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int value){
		this.value = value;
	}
}

public class TreeSample {
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		
		TreeSample ts = new TreeSample();
		System.out.println("isBST? -> " + ts.isBST(root));
		
		root = new TreeNode(6);
		root.left = new TreeNode(2);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(3);
		root.left.right.right = new TreeNode(5);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);		
		System.out.println("Lowest common ancestor -> " + 
				ts.lowestCommonAncestor(root, root.left, root.left.right).value);		
	}

	public boolean isBST(TreeNode root){
		if(root == null)
			return true;
		if(root.left == null && root.right != null)
			return false;
		if(root.left != null && root.right == null)
			return false;			
		if(root.left != null && root.left.value > root.value) return false;
		if(root.right != null && root.right.value < root.value) return false;
		return (isBST(root.left) && isBST(root.right));	
	}
	
	public TreeNode find(TreeNode n1, TreeNode n2){
		return null;
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
}
