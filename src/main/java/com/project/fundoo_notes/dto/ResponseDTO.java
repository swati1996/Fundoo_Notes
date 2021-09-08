package com.project.fundoo_notes.dto;


import lombok.*;
/**
 * purpose : Response DTO
 * @author : Swati
 * @version : 1.0
 * @since : 4-7-21
 **/
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO {
    private String message;
    private int statuscode;
//    private String token;
}
