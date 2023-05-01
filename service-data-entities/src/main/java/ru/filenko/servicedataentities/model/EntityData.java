package ru.filenko.servicedataentities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityData implements Serializable {
    private long id;
    private long idUser;
    private String title;
}
