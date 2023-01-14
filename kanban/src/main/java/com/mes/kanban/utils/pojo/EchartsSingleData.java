package com.mes.kanban.utils.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class EchartsSingleData {
    @JsonProperty("xAxis")
    public final String[] xAxis;
    @JsonProperty("yAxis")
    public final double[] yAxis;
}