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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
  /*      Task task1 = new Task();
        Task task2 = new Task();
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
*/
        TaskDto taskDto1 = new TaskDto(1L,"z","zzz");
        TaskDto taskDto2 = new TaskDto(2L,"x", "xxx");
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(taskDto1);
        taskListDto.add(taskDto2);

   //     when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(any())).thenReturn( taskListDto);

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
        Task task1 = new Task(1L,"z","zzz");
        Task task2 = new Task(2L,"x", "xxx");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        TaskDto taskDto1 = new TaskDto(1L,"z","zzz");
        TaskDto taskDto2 = new TaskDto(2L,"x", "xxx");
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(taskDto1);
        taskListDto.add(taskDto2);

        when(taskMapper.mapToTaskDto (any())).thenReturn(taskDto2);  //Czy tu może być np 2L

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is( taskDto2)));// nie mogę porównywac do DTO ??
        // i tworzyć 3 testy czy jest obejście
    }

    @Test
    void deleteTask() throws Exception {
        // Given
        Task task1 = new Task(1L,"z","zzz");
        Task task2 = new Task(2L,"x", "xxx");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        TaskDto taskDto1 = new TaskDto(1L,"z","zzz");
        TaskDto taskDto2 = new TaskDto(2L,"x", "xxx");
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(taskDto1);
        taskListDto.add(taskDto2);
        service.saveTask(task1);
        service.saveTask(task2);

//When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertEquals(2,service.getAllTasks().size());


    }

    @Test
    void updateTask() throws Exception {
        // Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createTask() throws Exception {
        // Given
        TaskDto taskDto1 = new TaskDto(1L, "z", "zzz");
        Task task1 = new Task(1L,"z","zzz");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("z")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("zzz")));
    }
}