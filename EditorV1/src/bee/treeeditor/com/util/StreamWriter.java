package bee.treeeditor.com.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class StreamWriter {
    public static void writeInt(OutputStream stream, int val) throws IOException {
        byte[] buf = new byte[4];

        for(int i = 4; i != 0; i--) {
            buf[i-1] = (byte) (val & 0xFF);
            val <<= 8;
        }
        stream.write(buf);
    }

    public static void writeLong(OutputStream stream, long val) throws IOException {
        byte[] buf = new byte[8];

        for(int i = 8; i != 0; i--) {
            buf[i-1] = (byte) (val & 0xFF);
            val <<= 8;
        }
        stream.write(buf);
    }

    public static void writeDouble(OutputStream stream, double val) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putDouble(val);
        stream.write(bb.array());
    }

    public static void writeFloat(OutputStream stream, float val) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putFloat(val);
        stream.write(bb.array());
    }

    public static void writeString(OutputStream stream, String s) throws IOException {
        writeInt(stream, s.length());
        stream.write(s.getBytes());
    }
}
