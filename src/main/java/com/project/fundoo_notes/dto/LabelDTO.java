package com.project.fundoo_notes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LabelDTO {
    private String labelname;
    @JsonIgnore
    private long noteId;
    @JsonIgnore
    private LocalDateTime updatedDate;
    @JsonIgnore
    private long userId;
    @JsonIgnore
    private LocalDateTime registrationDate=LocalDateTime.now();
}
