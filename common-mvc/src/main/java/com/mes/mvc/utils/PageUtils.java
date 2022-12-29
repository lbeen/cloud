package com.mes.mvc.utils;

import com.mes.mvc.pojo.Page;
import com.mes.mvc.pojo.Record;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PageUtils {

    public static Page getPage(Function<Map<String, Object>, Integer> countFun,
                               Function<Map<String, Object>, List<Record>> listFun, Map<String, Object> param) {
        Integer count = countFun.apply(param);
        if (count == null || count <= 0) {
            return Page.empty();
        }

        int page = toInt(param.remove("page"), 1);
        int pageSize = toInt(param.remove("pageSize"), 50);

        int start = pageSize * (page - 1);
        int end = start + pageSize;
        param.put("start", start);
        param.put("end", end);
        return Page.create(count, listFun.apply(param));
    }

    private static int toInt(Object object, int defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        return Integer.parseInt(object.toString());
    }
}
