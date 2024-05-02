package bee.treeeditor.com;

import bee.treeeditor.com.tree.TreeNode;
import bee.treeeditor.com.tree.node.TextNode;
import com.sun.source.tree.Tree;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new TextNode("test1"));
        root.addChild(new TreeNode(new TextNode("test2")));
        System.out.println(root.toString());
        
    }
}