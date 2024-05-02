package bee.treeeditor.com.tree.node;

import bee.treeeditor.com.tree.INodeData;
import bee.treeeditor.com.util.StreamReader;
import bee.treeeditor.com.util.StreamWriter;
import java.io.*;

public class TextNode implements INodeData {
    private StringBuilder content;

    public TextNode(){
        content = new StringBuilder();
    }

    public TextNode(String s){
        content = new StringBuilder(s);
    }

    @Override
    public int buildPrefix(StringBuilder sb)  {
        sb.append(content);
        return content.length();
    }

    @Override
    public int prefixLength() {
        return content.length();
    }

    @Override
    public int buildSuffix(StringBuilder sb) {
        sb.append("\n");
        return 1;
    }

    @Override
    public int suffixLength() {
        return 0;
    }

    @Override
    public boolean canDivide() {
        return false;
    }

    @Override
    public void serialize(OutputStream out) throws IOException {
        StreamWriter.writeString(out, content.toString());
    }

    public static INodeData deserialize(InputStream in) {
        try {
            TextNode newNode = new TextNode();
            newNode.content = new StringBuilder(StreamReader.readString(in));
            return newNode;
        }catch (Exception e){
            throw new UncheckedIOException(e.getMessage(), (IOException) e.getCause());
        }
    }
}
