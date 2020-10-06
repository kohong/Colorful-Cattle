package com.colorfulcattle.todo.service;

import com.colorfulcattle.todo.model.ToDo;
import com.colorfulcattle.todo.repo.ToDoRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class ToDoService {

    private final ToDoRepo toDoRepo;

    public boolean saveToDo(ToDo toDo) {
        log.info("Saving ToDo");
        ToDo savedToDo = toDoRepo.save(toDo);
        return toDoRepo.existsById(savedToDo.getId());
    }

    public boolean editToDo(ToDo toDo) throws IOException {
        log.info("Editing ToDo");
        saveToDo(toDo);
        return toDo.equals(findToDo(toDo.getId()));
    }

    public void removeToDo(Long id) {
        log.info("Removing ToDo with id: " + id);
        toDoRepo.deleteById(id);
    }

    public List<ToDo> allToDo() {
        log.info("Retrieving all ToDo");
        return toDoRepo.findAll();
    }

    public ToDo findToDo(Long id) throws IOException {
        log.info("Retrieving ToDo with id: " + id);
        return toDoRepo.findById(id).orElseThrow(() -> new IOException("ToDo not Found"));
    }


    public void removeAllToDo() {
        log.info("Delete all ToDos");
        toDoRepo.deleteAll();
    }
}
