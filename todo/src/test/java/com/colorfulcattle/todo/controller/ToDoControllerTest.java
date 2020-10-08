package com.colorfulcattle.todo.controller;

import com.colorfulcattle.todo.model.ToDo;
import com.colorfulcattle.todo.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;

class ToDoControllerTest {

    @Mock
    private ToDo toDo;
    @Mock
    private ToDoService toDoService;
    private ToDoController toDoController;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        toDoController = new ToDoController(toDoService);
    }

    @Test
    void allToDo() {
        toDoController.allToDo();
        verify(toDoService, atMostOnce()).allToDo();
    }

    @Test
    void addToDo() {
        toDoController.addToDo(toDo);
        verify(toDoService, atMostOnce()).saveToDo(toDo);
    }

    @Test
    void getToDo() throws IOException {
        toDoController.getToDo(1L);
        verify(toDoService, atMostOnce()).findToDo(1L);
    }

    @Test
    void editToDo() throws IOException {
        toDoController.editToDo(toDo);
        verify(toDoService, atMostOnce()).editToDo(toDo);
    }

    @Test
    void deleteToDo() {
        toDoController.deleteToDo("1");
        verify(toDoService, atMostOnce()).removeToDo(1L);
    }


    @Test
    void deleteAllToDo() {
        toDoController.deleteToDo("all");
        verify(toDoService, atMostOnce()).removeAllToDo();
    }
}
