package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.ResponseDTO;

public interface ILabelService {
    ResponseDTO create(LabelDTO labelDTO);
}
