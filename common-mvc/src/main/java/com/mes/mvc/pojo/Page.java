package com.mes.mvc.pojo;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Page {
    private final int count;
    private final List<Record> list;

    private Page(int count, List<Record> list) {
        this.count = count;
        this.list = list;
    }

    public static Page create(int count, List<Record> list) {
        return new Page(count, list);
    }

    public static Page empty() {
        return create(0, Collections.emptyList());
    }
}