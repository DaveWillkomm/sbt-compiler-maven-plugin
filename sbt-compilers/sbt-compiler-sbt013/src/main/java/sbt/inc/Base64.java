package sbt.inc;

import java.lang.reflect.InvocationTargetException;

public interface Base64 {
    String encode(byte[] bytes);
    byte[] decode(String string);
}

class Base64Factory {
    public static sbt.inc.Base64 create() {
        return new Java89Encoder();
    }
}

class Java89Encoder implements sbt.inc.Base64 {
    private static final Class<?> Base64_class;
    private static final java.lang.reflect.Method Base64_getEncoder;
    private static final java.lang.reflect.Method Base64_getDecoder;
    private static final Class<?> Base64_Encoder_class;
    private static final Class<?> Base64_Decoder_class;
    private static final java.lang.reflect.Method Base64_Encoder_encodeToString;
    private static final java.lang.reflect.Method Base64_Decoder_decode;

    static {
        try {
            Base64_class = Class.forName("java.util.Base64");
            Base64_getEncoder = Base64_class.getMethod("getEncoder");
            Base64_getDecoder = Base64_class.getMethod("getDecoder");
            Base64_Encoder_class = Class.forName("java.util.Base64$Encoder");
            Base64_Decoder_class = Class.forName("java.util.Base64$Decoder");
            Base64_Encoder_encodeToString = Base64_Encoder_class.getMethod("encodeToString", byte[].class);
            Base64_Decoder_decode = Base64_Decoder_class.getMethod("decode", String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String encode(byte[] bytes) {
        try {
            Object encoder = Base64_getEncoder.invoke(null);
            return (String) Base64_Encoder_encodeToString.invoke(encoder, (Object) bytes);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex.getCause());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] decode(String string) {
        try {
            Object decoder = Base64_getDecoder.invoke(null);
            return (byte[]) Base64_Decoder_decode.invoke(decoder, string);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex.getCause());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
