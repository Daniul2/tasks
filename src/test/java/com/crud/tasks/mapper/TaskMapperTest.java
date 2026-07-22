package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TaskMapperTest {

    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    void shouldMapToTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");

        // When
        Task result = taskMapper.mapToTask(taskDto);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("Test title", result.getTitle());
        assertEquals("Test content", result.getContent());
    }

    @Test
    void shouldMapToTaskDto() {
        // Given
        Task task = new Task(1L, "Test title", "Test content");

        // When
        TaskDto result = taskMapper.mapToTaskDto(task);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("Test title", result.getTitle());
        assertEquals("Test content", result.getContent());
    }

    @Test
    void shouldMapToTaskDtoList() {
        // Given
        List<Task> tasks = List.of(
                new Task(1L, "Title 1", "Content 1"),
                new Task(2L, "Title 2", "Content 2")
        );

        // When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(tasks);

        // Then
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Title 1", result.get(0).getTitle());
        assertEquals("Content 1", result.get(0).getContent());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Title 2", result.get(1).getTitle());
        assertEquals("Content 2", result.get(1).getContent());
    }

    @Test
    void shouldReturnEmptyListWhenMappingEmptyTaskList() {
        // Given
        List<Task> tasks = List.of();

        // When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(tasks);

        // Then
        assertTrue(result.isEmpty());
    }
}
