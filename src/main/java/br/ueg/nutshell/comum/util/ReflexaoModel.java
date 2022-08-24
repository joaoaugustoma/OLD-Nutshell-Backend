package br.ueg.nutshell.comum.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflexaoModel {
    public static <T> T getIDValue(Object model, T pkType) {
        Class<?> cls = model.getClass();

        try {
            Field flds[] = cls.getDeclaredFields();
            for (Field fld : flds) {
                if (fld.isAnnotationPresent(javax.persistence.Id.class)) {
                    String nameField = "get"
                            +fld.getName().substring(0,1).toUpperCase()
                            +fld.getName().substring(1);
                    Method fieldMethod = cls.getMethod(nameField, null);
                    return (T) fieldMethod.invoke(model);
                }
            }
        }catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return  null;
    }
}
