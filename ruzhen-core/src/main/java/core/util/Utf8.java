package core.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * 〈一句话功能简述〉<br>
 * 〈Utf8转换字符串〉
 *
 * @author lizhen
 * @create 2018/7/5
 * @since 1.0.0
 */
public final class Utf8 {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public Utf8() {
    }

    public static byte[] encode(CharSequence string) {
        try {
            ByteBuffer bytes = CHARSET.newEncoder().encode(CharBuffer.wrap(string));
            byte[] bytesCopy = new byte[bytes.limit()];
            System.arraycopy(bytes.array(), 0, bytesCopy, 0, bytes.limit());
            return bytesCopy;
        } catch (CharacterCodingException var3) {
            throw new IllegalArgumentException("Encoding failed", var3);
        }
    }

    public static String decode(byte[] bytes) {
        try {
            return CHARSET.newDecoder().decode(ByteBuffer.wrap(bytes)).toString();
        } catch (CharacterCodingException var2) {
            throw new IllegalArgumentException("Decoding failed", var2);
        }
    }
}