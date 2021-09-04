package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.NoteResponseDTO;
import com.project.fundoo_notes.dto.ResponseDTO;

import java.util.List;

public interface INoteService {
    ResponseDTO create(NoteDTO noteDTO, String token);

    NoteResponseDTO getNote(String token);

    ResponseDTO updateNote(String token, NoteDTO noteDTO);

    ResponseDTO deleteNote(String token);

    ResponseDTO archieveNote(String token);

    ResponseDTO pinNote(String token);

    List getAllNoteByTrash();

    List getAllNoteByPin();

    List getAllNoteByArchieve();

    List getAllNoteByTrashAndArchieve();
}
