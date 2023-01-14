package com.mes.kanban.utils.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@AllArgsConstructor
public class EchartsPieData {
    @JsonProperty("legend")
    public final String[] legend;
    @JsonProperty("series")
    public final List<Map<String, Object>> series;
}
