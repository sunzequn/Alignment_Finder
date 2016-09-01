package com.sunzequn.af.utils;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * Created by sloriac on 16-9-1.
 */
public class SerializableUtil {

    public static String object2Str(Object object) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String serStr = Base64.getEncoder().encodeToString(byteArray);
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    public static Object str2Object(String serStr) throws Exception {
        byte[] byteArray = Base64.getDecoder().decode(serStr);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return object;
    }
}
