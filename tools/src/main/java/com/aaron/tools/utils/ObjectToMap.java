package com.aaron.tools.utils;

import com.aaron.tools.pager.Query;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Created by aaron.qiu on 2016/11/5.
 * 把实体对象转换成map
 */
public class ObjectToMap {

    private static ObjectMapper oMapper = new ObjectMapper();

    public static Map<String,Object> object2Map(Object obj) {
        // object -> Map
        Map<String, Object> map = oMapper.convertValue(obj, Map.class);
        return map;
    }

    public static void main(String args[]) {
        Query query = new Query();
        query.setPage("10");
        query.setPageSize(30);
        Map<String,Object> map = ObjectToMap.object2Map(query);
        System.out.println(map);

    }

}
