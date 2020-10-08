package com.colorfulcattle.todo.service;

import com.colorfulcattle.todo.model.ToDo;
import com.colorfulcattle.todo.repo.ToDoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;


class ToDoServiceTest {

    @Mock
    private ToDoRepo toDoRepo;
    @Mock
    private ToDo toDo;
    private ToDoService toDoService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        toDoService = new ToDoService(toDoRepo);
        when(toDo.getId()).thenReturn(1L);
        when(toDoRepo.save(toDo)).thenReturn(toDo);
        when(toDoRepo.existsById(1L)).thenReturn(true);
        when(toDoRepo.findById(1L)).thenReturn(Optional.of(toDo));
    }

    @Test
    void saveToDo() {
        toDoService.saveToDo(toDo);
        verify(toDoRepo, atMostOnce()).save(toDo);
    }

    @Test
    void saveToDo2() {
        toDoService.saveToDo(toDo);
        verify(toDoRepo, atMostOnce()).existsById(1L);
    }

    @Test
    void editToDo() throws IOException {
        toDoService.editToDo(toDo);
    }

    @Test
    void removeToDo() {
        toDoService.removeToDo(1L);
        verify(toDoRepo, atMostOnce()).deleteById(1L);
    }

    @Test
    void allToDo() {
        toDoService.allToDo();
        verify(toDoRepo, atMostOnce()).findAll();
    }

    @Test
    void findToDo() throws IOException {
        toDoService.findToDo(1L);
        verify(toDoRepo, atMostOnce()).findById(1L);
    }

    @Test
    void removeAllToDo() {
        toDoService.removeAllToDo();
        verify(toDoRepo, atMostOnce()).deleteAll();
    }
}
