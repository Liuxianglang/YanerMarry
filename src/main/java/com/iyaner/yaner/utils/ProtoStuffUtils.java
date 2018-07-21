package com.iyaner.yaner.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import io.protostuff.Schema;

public class ProtoStuffUtils {


    /**
     * 序列号对象
     */
    public static <T> byte[] serialize(T t,Class<T> cls){
        Schema schema=RuntimeSchema.createFrom(cls);
        LinkedBuffer buffer=LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] res=ProtobufIOUtil.toByteArray(t,schema,buffer);
        return res;
    }

    /**
     * 反序列化对象
     */
    public static <T> T deSerialize(byte[] data,Class<T> cls){
        Schema<T> schema=RuntimeSchema.createFrom(cls);
        T t=schema.newMessage();
        ProtobufIOUtil.mergeFrom(data,t,schema);
        return t;
    }

}
