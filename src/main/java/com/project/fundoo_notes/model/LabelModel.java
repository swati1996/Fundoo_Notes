package com.project.fundoo_notes.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Label")
public class LabelModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "labelname")
    private String labelname;
    @Column(name = "userId")
    private long userId;
    @Column(name = "noteId")
    private long noteId;
    @Column(name = "registrationDate")
    private LocalDateTime registrationDate;
    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;
    @ManyToMany(mappedBy = "labelList")
    private List<NoteModel> notes;
}
