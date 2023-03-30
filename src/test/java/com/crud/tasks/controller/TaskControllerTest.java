package com.crud.tasks.controller;

import com.crud.tasks.controller.domain.Task;
import com.crud.tasks.controller.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest( TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DbService service;

    @Test
    void getTasks() throws Exception {
        // Given
        TaskDto taskDto1 = new TaskDto(1L, "z", "zzz");
        TaskDto taskDto2 = new TaskDto(2L, "x", "xxx");
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(taskDto1);
        taskListDto.add(taskDto2);

        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskListDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void getTask() throws Exception {
        // Given
        Task task1 = new Task(1L, "z", "zzz");
        Task task2 = new Task(2L, "x", "xxx");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        TaskDto taskDto1 = new TaskDto(1L, "z", "zzz");
        TaskDto taskDto2 = new TaskDto(2L, "x", "xxx");
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(taskDto1);
        taskListDto.add(taskDto2);

        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto2);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("x")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("xxx")));
    }

    @Test
    void deleteTask() throws Exception {
        // Give

//When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateTask() throws Exception {
        // Given
        Task task1 = new Task(1L, "z", "zzz");
        service.saveTask(task1);
        TaskDto taskDto2 = new TaskDto(1L, "x", "xxx");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto2);

        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto2);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("x")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("xxx")));
    }

    @Test
    void createTask() throws Exception {
        // Given
        TaskDto taskDto1 = new TaskDto(1L, "z", "zzz");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}