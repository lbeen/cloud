package com.mes.kanban.utils.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EchartsData {
    @JsonProperty("legend")
    public final String[] legend;
    @JsonProperty("xAxis")
    public final String[] xAxis;
    @JsonProperty("yAxis")
    public final double[][] yAxis;
}
