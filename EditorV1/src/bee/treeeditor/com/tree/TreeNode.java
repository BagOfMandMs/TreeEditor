package bee.treeeditor.com.tree;

import bee.treeeditor.com.util.StreamReader;
import bee.treeeditor.com.util.StreamWriter;

import java.io.*;
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

    public void serialize(OutputStream stream) throws IOException {
        StreamWriter.writeInt(stream, NodeDeserializer.getTypeID(content));
        this.content.serialize(stream);
        StreamWriter.writeInt(stream, this.children.size());
        for (TreeNode node : this.children) {
            node.serialize(stream);
        }
    }

    public static TreeNode deserialize(InputStream stream) throws IOException {
        try {
            INodeData nodeContent = NodeDeserializer.deserialize(stream);
            TreeNode node = new TreeNode(nodeContent);
            int childCount = StreamReader.readInt(stream);
            for(int i = 0; i < childCount; i++){
                node.addChild(deserialize(stream));
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
