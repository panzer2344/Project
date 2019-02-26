package com.azino.project.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JArrayUtils {

    public static <T> Boolean contains(List<T> list, T object){
        if(null == object){
            return false;
        }
        for (T e : list){
            if(object.equals(e)){
                return true;
            }
        }
        return false;
    }

}
