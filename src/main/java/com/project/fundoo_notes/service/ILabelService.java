package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelListResponse;
import com.project.fundoo_notes.dto.LabelResponseDTO;


/**
 * purpose : Interface for label
 * @author : Swati
 * @version : 1.0
 * @since : 4-7-21
 **/

public interface ILabelService {
    LabelResponseDTO create(LabelDTO labelDTO, String token, Long noteId);

    LabelResponseDTO update(String token, LabelDTO labelDTO, Long labelId);

    LabelResponseDTO delete(String token, Long labelId);

    LabelListResponse getLabel(String token);

    LabelResponseDTO NoteAsLabel(String token, long noteId, long labelId);

    LabelResponseDTO DeleteLabelAsNote(String token, long labelId);
}
