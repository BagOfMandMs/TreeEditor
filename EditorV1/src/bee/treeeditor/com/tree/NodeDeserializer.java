package bee.treeeditor.com.tree;

import bee.treeeditor.com.tree.node.TextNode;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

public class NodeDeserializer {
    private static HashMap<Class<?>, Integer> typeMap;
    private static HashMap<Integer, Function<FileInputStream, INodeData>> nodeTypeDeserializers;

    private static final NodeDeserializer instance = new NodeDeserializer();

    public NodeDeserializer() {
        nodeTypeDeserializers = new HashMap<>();
        typeMap = new HashMap<>();
        nodeTypeDeserializers.put(0, TextNode::deserialize);
        typeMap.put(TextNode.class, 0);
    }

    public static INodeData deserialize(FileInputStream in) throws IOException {
        int id = in.read();
        if(nodeTypeDeserializers.containsKey(id)) {
            return nodeTypeDeserializers.get(id).apply(in);
        } else {
            throw new UnsupportedOperationException("Unsupported node type");
        }
    }

    public static int getTypeID(INodeData inode){
        return typeMap.get(inode.getClass());
    }
}
