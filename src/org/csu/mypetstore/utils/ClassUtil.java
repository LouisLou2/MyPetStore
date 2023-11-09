package org.csu.mypetstore.utils;

public class ClassUtil {
    public static boolean isDirect(Object object) {
        return object instanceof String || object instanceof Byte || object instanceof Short || object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double || object instanceof Character ||object instanceof Boolean;
    }
}
