package com.colorfulcattle.todo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToDo {

    @GeneratedValue(strategy = AUTO)
    @Id
    private long id;
    @NotNull
    private String description;
    private boolean done;
    @Column(name = "DONE_BY")
    private LocalDateTime doneBy;
}
