package com.leychina.utils;

import java.util.List;

/**
 * Created by yuandunlong on 11/21/15.
 */
public class ListUtils {

    public static int getSize(List list){

        if(list==null){
            return 0;
        }
        return list.size();
    }

    public static  boolean isEmpty(List list){

        return list.size()==0;
    }

    public static  boolean isNotEmpty(List list){
        return !isEmpty(list);
    }

}
