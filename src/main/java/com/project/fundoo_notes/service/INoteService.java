package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.NoteResponseDTO;
import com.project.fundoo_notes.dto.ResponseDTO;

import java.util.List;

public interface INoteService {
    ResponseDTO create(NoteDTO noteDTO, String token);

    NoteResponseDTO getNote(String token);

    ResponseDTO updateNote(String token, NoteDTO noteDTO, long noteId);

    ResponseDTO deleteNote(String token, long noteId);

    ResponseDTO archieveNote(String token, long noteId);

    ResponseDTO pinNote(String token, long noteId);

    NoteResponseDTO getAllNoteFromTrash(String token);

    NoteResponseDTO getAllPinNote(String token);

    NoteResponseDTO getAllNoteByArchieve(String token);

    NoteResponseDTO getAllNoteByTrashAndArchieve(String token);
}
