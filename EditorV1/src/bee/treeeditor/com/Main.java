package bee.treeeditor.com;

import bee.treeeditor.com.tree.TreeNode;
import bee.treeeditor.com.tree.node.TextNode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Main {
    public static void main(String[] args) {
        try {
            TreeNode root = new TreeNode(new TextNode("test1"));
            TreeNode child1 = new TreeNode(new TextNode("test2"));
            TreeNode child2 = new TreeNode(new TextNode("test3"));
            child1.addChild(new TreeNode(new TextNode("test4")));
            root.addChild(child1);
            root.addChild(child2);
            System.out.println(root);
            ByteArrayOutputStream tobin = new ByteArrayOutputStream();
            root.serialize(tobin);
            ByteArrayInputStream frombin = new ByteArrayInputStream(tobin.toByteArray());
            TreeNode clone = TreeNode.deserialize(frombin);
            System.out.println(clone);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}