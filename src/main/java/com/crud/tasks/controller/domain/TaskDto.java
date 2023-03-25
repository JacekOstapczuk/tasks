package com.crud.tasks.controller.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}
