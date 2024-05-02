package bee.treeeditor.com.tree;

import java.io.IOException;
import java.io.OutputStream;

public interface INodeData {
    int buildPrefix(StringBuilder sb);
    int prefixLength();

    int buildSuffix(StringBuilder sb);
    int suffixLength();

    boolean canDivide();

    void serialize(OutputStream out) throws IOException;
}
