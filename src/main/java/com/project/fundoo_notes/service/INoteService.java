package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.ResponseDTO;

public interface INoteService {
    ResponseDTO create(NoteDTO noteDTO);

    ResponseDTO getNote(String token);

    ResponseDTO updateNote(String token, NoteDTO noteDTO);
}
