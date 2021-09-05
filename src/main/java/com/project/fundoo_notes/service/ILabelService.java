package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelListResponse;
import com.project.fundoo_notes.dto.LabelResponseDTO;

public interface ILabelService {
    LabelResponseDTO create(LabelDTO labelDTO, String token, Long noteId);

    LabelResponseDTO update(String token, LabelDTO labelDTO, Long labelId);

    LabelResponseDTO delete(String token, Long labelId);

    LabelListResponse getLabel(String token);

    LabelResponseDTO labelAsNote(String token, long labelId);

    LabelResponseDTO DeleteLabelAsNote(String token, long labelId);
}
