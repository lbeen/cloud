package com.mes.kanban.utils.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScrollData {
    @JsonProperty("header")
    public final String[] header;
    @JsonProperty("data")
    public final String[][] data;

    public ScrollData(String[][] data) {
        this(null, data);
    }

    public ScrollData(String[] header, String[][] data) {
        this.header = header;
        this.data = data;
    }
}
