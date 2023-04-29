import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Sophie Ho
 * @version: 4/28/23
 *
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        // Use the boolean helper method to assist the search
        return helperSearch(root, val);
    }

    // Helper method of the search method
    // Helps run search by recursively checking the tree's nodes
    public boolean helperSearch(BSTNode n, int val)
    {
        // If val is equal to the current node, return true
        if(n.equals(val))
        {
            return true;
        }
        // If current node is null, return false
        if(n.equals(null))
        {
            return false;
        }
        // If the current node is greater than target value, recurse on the smaller child node
        if(n.getVal() > val)
        {
            return helperSearch(n.getRight(), val);
        }
        // If the current node is less than target value, recurse on the greater child node
        return helperSearch(n.getLeft(), val);
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        // Left --> Root --> Right
        // Adds the nodes in a "leftmost-first order"
        ArrayList<BSTNode> inorder = new ArrayList<BSTNode>();
        // Use the recursive helper-method to add to the ArrayList in order.
        helperGetInorder(root, inorder);
        return inorder;
    }

    public void helperGetInorder(BSTNode node, ArrayList<BSTNode> inorder)
    {
        // Base Case: If there are no more nodes left to search, return.
        if (node == null)
        {
            return;
        }
        // Left: traverse through all the left-most nodes recursively
        helperGetInorder(node.getLeft(), inorder);
        // Root: add the node to the arrayList
        inorder.add(node);
        // Right: traverse the right-most nodes recursively
        helperGetInorder(node.getRight(), inorder);
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {
        // Root → Left → Right
        ArrayList<BSTNode> preorder = new ArrayList<BSTNode>();
        helperGetPreorder(root, preorder);
        return preorder;
    }

    public void helperGetPreorder(BSTNode node, ArrayList<BSTNode> preorder)
    {
        // Base case: If there are no more nodes left to search, return.
        if (node == null)
        {
            return;
        }
        // Complete traversal in specified order of Root → Left → Right:
        // Root
        preorder.add(node);
        // Left
        helperGetPreorder(node.getLeft(), preorder);
        // Right
        helperGetPreorder(node.getRight(), preorder);
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        // Left → Right → Root
        ArrayList<BSTNode> postorder = new ArrayList<BSTNode>();
        helperGetPostorder(root, postorder);
        return postorder;
    }

    public void helperGetPostorder(BSTNode node, ArrayList<BSTNode> postorder)
    {
        // Base case: If there are no more nodes left to search, return.
        if (node == null)
        {
            return;
        }
        // Complete traversal in specified order of Left → Right → Root:
        // Left
        helperGetPreorder(node.getLeft(), postorder);
        // Right
        helperGetPreorder(node.getRight(), postorder);
        // Root
        postorder.add(node);
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        root = helperInsert(val, root);
    }

    public BSTNode helperInsert(int val, BSTNode node)
    {
        // If the passed in node is empty:
        if (node == null)
        {
            // Create and set the new root as the target value.
            BSTNode newRoot = new BSTNode(val);
            return newRoot;
        }
        // If there is a match between the passed in value and node, return that node.
        if (val == node.getVal())
        {
            return node;
        }
        // If the target value is smaller than the current node, recurse to the left until otherwise:
        if (val < node.getVal())
        {
            // Then set the node to the left as the target value, and return that node.
            node.setLeft(helperInsert(val, node.getLeft()));
            return node;
        }
        // If the target value is bigger than the current node, recurse to the right until otherwise
        node.setRight(helperInsert(val, node.getRight()));
        // Return the node.
        return node;
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // TODO: Optional Challenge!
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
