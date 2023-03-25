package com.crud.tasks.mapper;

import com.crud.tasks.controller.domain.Task;
import com.crud.tasks.controller.domain.TaskDto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TaskMapper {


    public Task mapToTask(final TaskDto taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getContent()
        );
    }

    public TaskDto mapToTaskDto(final Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getContent()
        );
    }

    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList) {
        return taskList.stream()
                .map(this::mapToTaskDto)
                .toList();
    }
}