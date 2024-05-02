package bee.treeeditor.com.tree;

import bee.treeeditor.com.tree.node.TextNode;
import bee.treeeditor.com.util.StreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.function.Function;

public class NodeDeserializer {
    private static HashMap<Class<?>, Integer> typeMap;
    private static HashMap<Integer, Function<InputStream, INodeData>> nodeTypeDeserializers;

    // SINGLETON
    private static final NodeDeserializer instance = new NodeDeserializer();
    private NodeDeserializer() {
        nodeTypeDeserializers = new HashMap<>();
        typeMap = new HashMap<>();
        nodeTypeDeserializers.put(0, TextNode::deserialize);
        typeMap.put(TextNode.class, 0);
    }

    public static INodeData deserialize(InputStream in) throws IOException {
        int id;
        try{
            id = StreamReader.readInt(in);
        }catch (Exception e){
            throw new IOException("Malformatted binary");
        }
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
