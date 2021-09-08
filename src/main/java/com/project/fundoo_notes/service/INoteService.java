package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.NoteResponseDTO;
import com.project.fundoo_notes.dto.ResponseDTO;

import java.util.List;


/**
 * purpose : interface for note
 * @author : Swati
 * @version : 1.0
 * @since : 3-7-21
 **/
public interface INoteService {
    ResponseDTO create(NoteDTO noteDTO, String token);

    NoteResponseDTO getNote(String token);

    ResponseDTO updateNote(String token, NoteDTO noteDTO, long noteId);

    ResponseDTO deleteNote(String token, long noteId);

    ResponseDTO archieveNote(String token, long noteId);

    ResponseDTO pinNote(String token, long noteId);

    NoteResponseDTO getAllNoteFromTrash(String token);

    NoteResponseDTO getAllPinNote(String token);

    NoteResponseDTO getAllNoteFromArchieve(String token);

    NoteResponseDTO getAllNoteFromTrashAndArchieve(String token);

    ResponseDTO setNoteColor(String s, Long noteId, String token);
}
