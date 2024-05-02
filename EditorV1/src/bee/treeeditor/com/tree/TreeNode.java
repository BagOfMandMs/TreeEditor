package bee.treeeditor.com.tree;

import bee.treeeditor.com.util.StreamReader;
import bee.treeeditor.com.util.StreamWriter;
import com.sun.source.tree.Tree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TreeNode {
    private ArrayList<TreeNode> children;

    private INodeData content;
    public TreeNode(INodeData content){
        this.children = new ArrayList<>();
        this.content = content;
    }

    public void addChild(TreeNode child){
        this.children.add(child);
    }

    public void serialize(FileOutputStream fout) throws IOException {
        StreamWriter.writeInt(fout, NodeDeserializer.getTypeID(content));
        this.content.serialize(fout);
        StreamWriter.writeInt(fout, this.children.size());
        for (TreeNode node : this.children) {
            node.serialize(fout);
        }
    }

    public static TreeNode deserialize(FileInputStream fin) throws IOException {
        try {
            INodeData nodeContent = NodeDeserializer.deserialize(fin);
            TreeNode node = new TreeNode(nodeContent);
            int childCount = StreamReader.readInt(fin);
            for(int i = 0; i < childCount; i++){
                node.addChild(deserialize(fin));
            }
            return node;
        }catch (StreamReader.InvalidStreamLengthException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildToString(sb);
        return sb.toString();
    }

    private void buildToString(StringBuilder sb){
        content.buildPrefix(sb);
        for(TreeNode child : children){
            child.buildToString(sb);
        }
        content.buildSuffix(sb);
    }
}
