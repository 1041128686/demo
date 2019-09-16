package com.my.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonUtil {

    private static  ObjectMapper objectMapper = new ObjectMapper();


    // Object转换为JSON
    public static String objectToJson(Object object){
        String json=null;
        try {
            json=objectMapper.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    // JSON转换为Object
    public static <T> T jsonToObject(String json,Class<T> clazz){

        try {
            return objectMapper.readValue(json,clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    // JSON转换为List
    public static <T>List<T> jsonToList(String json,Class<T> clazz){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);

        try {
            return objectMapper.readValue(json,javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
