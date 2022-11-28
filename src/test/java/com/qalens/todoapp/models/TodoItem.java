package com.qalens.todoapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class TodoItem {
    private int id;
    private String caption;
    @Builder.Default
    @JsonProperty("isDone")
    private boolean isDone=false;
}
