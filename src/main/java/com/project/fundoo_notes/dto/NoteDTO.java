package com.project.fundoo_notes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteDTO {
    @NotBlank(message = "Title should not be empty")
    private String title;
    @NotBlank(message = "Please enter description")
    private String description;
    private String color;
//    @JsonIgnore
//    @NotBlank(message = "Please enter valid email")
//    @Email
//    private String emailid;
    @JsonIgnore
    private long userId;
    private boolean inPin;
    private boolean inarchieved;
    @JsonIgnore
    private LocalDateTime updateDate=LocalDateTime.now();
    @JsonIgnore
    private LocalDateTime registerDate=LocalDateTime.now();
}
