package com.project.fundoo_notes.dto;

import lombok.*;

import java.util.List;

/**
 * purpose : response DTO as list  for Label
 * @author : Swati
 * @version : 1.0
 * @since : 7-7-21
 **/

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabelListResponse {
    private List labels;
    private int statuscode;
}
