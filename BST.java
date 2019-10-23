//Comparable<T> contained in java.lang package JKD 11
public class BST<T> {

    Node root;

    class Node<T> {
        Comparable data;
        Node left;
        Node right;

        Node(Comparable item) {
            data = item;
        }
    }

    public boolean find(Comparable item) {
        return find(item, root);
    }
    //This will return false if there is no other child node to find, else it will return true if it's found.
    //will recursively call it self passing children nodes as the sNode arguement
    private boolean find(Comparable item, Node sNode) {
        if(sNode == null)
            return false;
        if(sNode.data.compareTo(item) == 0) {
            return true;
        }
        else if(sNode.data.compareTo(item) < 0) {
            return find(item, sNode.right);
        }
        else {
            return find(item, sNode.left);
        }
    }

    public void insert(Comparable item) {
        root = insert(item, root);
    }
    //will return Node and assign it to root if there's no root, otherwise will recursively call itself and
    //create new left and right children nodes
    private Node insert(Comparable item, Node pNode) {
        if(pNode == null)
            return new Node(item);
        if(pNode.data.compareTo(item) < 0) {
            pNode.right = insert(item, pNode.right);
        }
        else {
            pNode.left = insert(item, pNode.left); //will keep duplicates and insert them as left nodes
        }
        return pNode;
    }

    public void delete(Comparable item) {
        root = delete(item, root);
    }

    private Node delete(Comparable item, Node dNode) {
        if(dNode == null)
            return null;
        if(dNode.data.compareTo(item) == 0) { //check if we found the Node we want to delete
            if(dNode.left == null) {
                return dNode.right;
            }
            else if(dNode.right == null) {
                return dNode.left;
            }
            else {
                if(dNode.right.left == null) {
                    dNode.data = dNode.right.data;
                    dNode.right = dNode.right.right;
                    return dNode;
                }
                else {
                    dNode.data = moveSmallest(dNode.right);
                    return dNode;
                }
            }
        }
        else if(dNode.data.compareTo(item) < 0) {
            dNode.right = delete(item, dNode.right); //recursively calls itself to keep searching
            return dNode;                            //for which node contains the data we want to delete
        }
        else {
            dNode.left = delete(item, dNode.left);
            return dNode;
        }
    }
    //will take the smallest value of the Node we want to delete and replace the Node with that smallest value
    private Comparable moveSmallest(Node node) {
        if(node.left.left == null) {
            Comparable smallest = node.left.data;
            node.left = node.left.right;
            return smallest;
        }
        else {
            return moveSmallest(node.left);
        }
    }

    public void print() {
        print(root);
    }
    //if root isn't null, it will check if left child is null first, print left child and children
    //of that left child first if not null, then will print root, then will print right child and all
    //right side children until
    private void print(Node root) {
        if(root != null) {
            print(root.left);
            System.out.println(root.data);
            print(root.right);
        }
    }
}