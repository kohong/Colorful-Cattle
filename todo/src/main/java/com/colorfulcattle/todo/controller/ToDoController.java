package com.colorfulcattle.todo.controller;

import com.colorfulcattle.todo.model.ToDo;
import com.colorfulcattle.todo.service.ToDoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping({"/todo"})
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping("/all")
    public ResponseEntity<List<ToDo>> allToDo() {
        log.info("Returning all ToDos");
        List<ToDo> toDos = toDoService.allToDo();
        return !toDos.isEmpty() ? ResponseEntity.ok(toDos) : ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addToDo(@RequestBody ToDo toDo) {
        log.info("Adding new ToDo");
        return toDoService.saveToDo(toDo) ? ResponseEntity.ok().build() : ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/edit")
    public ResponseEntity<ToDo> getToDo(@RequestParam Long id) {
        log.info("Getting ToDo");
        try {
            return ResponseEntity.ok(toDoService.findToDo(id));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ToDo not found");
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<Boolean> editToDo(@RequestBody ToDo toDo) {
        log.info("Editing ToDo with id: " + toDo.getId());
        try {
            return toDoService.editToDo(toDo) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ToDo could not be edited");
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<Boolean> deleteToDo(@RequestParam String id) {
        log.info("Deleting ToDo");
        if (id.contentEquals("all")) {
            toDoService.removeAllToDo();
            return toDoService.allToDo().isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
        } else if (id.matches("[0-9]+")) {
            toDoService.removeToDo(Long.valueOf(id));
            try {
                toDoService.findToDo(Long.valueOf(id));
                return ResponseEntity.badRequest().build();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.OK, "ToDo with id " + id + " deleted");
            }
        }
        return ResponseEntity.badRequest().build();
    }

}
