package com.fanyiran.utils.recycleadapter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

public class TypeIdGenerator {
    private HashMap<String, Integer> typeIdMap = new HashMap<String, Integer>();
    private int value = 0;

    private static class TypeIdGeneratorHolder {
        static TypeIdGenerator typeIdGenerator = new TypeIdGenerator();
    }

    public static TypeIdGenerator getInstance() {
        return TypeIdGeneratorHolder.typeIdGenerator;
    }

    public <T extends RvItemData> int getTypeIdByGeneric(RvItemTypeAbstract<T> t) {
        Type type = ((ParameterizedType) t.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Integer result = typeIdMap.get(((Class) type).getSimpleName());
        if (result == null) {
            typeIdMap.put(((Class) type).getSimpleName(), ++value);
            return value;
        }
        return result;
    }

    <T extends RvItemData> int getTypeId(Class<T> t) {
        Integer result = typeIdMap.get(t.getSimpleName());
        if (result == null) {
            typeIdMap.put(t.getSimpleName(), ++value);
            return value;
        }
        return result;
    }
}
