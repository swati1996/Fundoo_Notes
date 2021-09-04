package com.project.fundoo_notes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@Table(name = "Notes")
public class NoteModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="Title")
    private String title;
    @Column(name="NoteDescription")
    private String description;
    @Column(name="UserId")
    private long userId;
    @Column(name = "registeredDate")
    private LocalDateTime registerDate;
    @Column(name = "UpdatedDate")
    private LocalDateTime updateDate;
    @Column(name="trash")
    private boolean trash = false;
    @Column(name="archieve")
    private boolean isArchieve;
    @Column(name="pin")
    private boolean pin;
    @Column(name="labelid")
    private Long labelId;
    @Column(name="emailid")
    private String emailid;
    @Column(name="color")
    private String color;
    @Column(name="reminder")
    private LocalDateTime remindertime;
    @JsonIgnore()
    @ManyToMany(cascade = CascadeType.ALL)
    private List<LabelModel> labelList;

}
