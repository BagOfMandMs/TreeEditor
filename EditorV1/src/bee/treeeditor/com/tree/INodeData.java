package bee.treeeditor.com.tree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public interface INodeData {
    int buildPrefix(StringBuilder sb);
    int prefixLength();

    int buildSuffix(StringBuilder sb);
    int suffixLength();

    boolean canDivide();

    void serialize(FileOutputStream out) throws IOException;
}
