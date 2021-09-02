package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelResponseDTO;
import com.project.fundoo_notes.dto.ResponseDTO;

public interface ILabelService {
    LabelResponseDTO create(LabelDTO labelDTO);

    LabelResponseDTO update(long id, LabelDTO labelDTO);

    LabelResponseDTO delete(long id);

    LabelResponseDTO getLabel(long id);
}
