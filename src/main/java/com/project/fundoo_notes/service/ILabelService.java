package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelResponseDTO;

public interface ILabelService {
    LabelResponseDTO create(LabelDTO labelDTO, String token, Long noteId);

    LabelResponseDTO update(String token, LabelDTO labelDTO, Long labelId);

//
//    LabelResponseDTO delete(String token);
//
//    LabelResponseDTO getLabel(String token);
//
//    LabelResponseDTO labelAsNote(String token, long noteId, long labelId);
//
//    LabelResponseDTO DeleteLabelAsNote(String token, long noteId, long labelId);
}
