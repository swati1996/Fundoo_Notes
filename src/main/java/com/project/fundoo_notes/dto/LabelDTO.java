package com.project.fundoo_notes.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LabelDTO {
    private String labelname;
    private long noteId;
//    private LocalDateTime updatedDate;
}
