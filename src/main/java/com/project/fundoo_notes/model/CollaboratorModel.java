package com.project.fundoo_notes.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;
/**
 * purpose : Model for collaborator
 * @author : Swati
 * @version : 1.0
 * @since : 7-7-21
 **/
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "collaborator")
public class CollaboratorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long noteId;
    private String email;

}
