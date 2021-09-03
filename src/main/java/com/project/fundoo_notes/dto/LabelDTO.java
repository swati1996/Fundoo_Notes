package com.project.fundoo_notes.dto;

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
//    private LocalDateTime updatedDate;
}
