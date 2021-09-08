package com.project.fundoo_notes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
/**
 * purpose : DTO for Label
 * @author : Swati
 * @version : 1.0
 * @since : 5-7-21
 **/

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
