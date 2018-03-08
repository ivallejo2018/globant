package globant;

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
		System.out.println(ts.isBST(root));
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
}
