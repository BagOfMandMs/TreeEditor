package bee.treeeditor.com.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class StreamReader {
    public static int readInt(InputStream stream) throws IOException, InvalidStreamLengthException { // not thread safe rn
        if(stream.available() < 4) {
            throw new InvalidStreamLengthException("Stream of "+ stream.available() +" bytes is invalid for type int");
        }
        byte[] buf = new byte[4];
        stream.read(buf, 0, 4); // since we already checked size, this shouldn't ever fail; if it does, good luck :)

        int val = 0;
        for(int i = 0; i < 4; i++) {
            val <<= 8;
            val |= buf[i];
        }
        return val;
    }

    public static long readLong(InputStream stream) throws InvalidStreamLengthException, IOException { // not thread safe rn
        if(stream.available() < 8) {
            throw new InvalidStreamLengthException("Stream of "+ stream.available() +" bytes is invalid for type long");
        }
        byte[] buf = new byte[8];
        stream.read(buf, 0, 8); // since we already checked size, this shouldn't ever fail; if it does, good luck :)

        int val = 0;
        for(int i = 0; i < 8; i++) {
            val <<= 8;
            val |= buf[0];
        }
        return val;
    }

    public static double readDouble(InputStream stream) throws InvalidStreamLengthException, IOException { // not thread safe rn
        if(stream.available() < 8) {
            throw new InvalidStreamLengthException("Stream of "+ stream.available() +" bytes is invalid for type long");
        }
        byte[] buf = new byte[8];
        stream.read(buf, 0, 8); // since we already checked size, this shouldn't ever fail; if it does, good luck :)
        return ByteBuffer.wrap(buf).getDouble();
    }

    public static float readFloat(InputStream stream) throws InvalidStreamLengthException, IOException { // not thread safe rn
        if(stream.available() < 4) {
            throw new InvalidStreamLengthException("Stream of "+ stream.available() +" bytes is invalid for type long");
        }
        byte[] buf = new byte[4];
        stream.read(buf, 0, 4); // since we already checked size, this shouldn't ever fail; if it does, good luck :)
        return ByteBuffer.wrap(buf).getFloat();
    }

    public static String readString(InputStream stream) throws InvalidStreamLengthException, IOException {
        int len = readInt(stream);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++){
            sb.append((char)stream.read());
        }
        return sb.toString();
    }


    public static class InvalidStreamLengthException extends Exception {
        InvalidStreamLengthException(String msg){
            super(msg);
        }
    }
}
