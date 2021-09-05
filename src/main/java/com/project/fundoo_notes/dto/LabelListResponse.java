package com.project.fundoo_notes.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabelListResponse {
    private List labels;
    private int statuscode;
}
