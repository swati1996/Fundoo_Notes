package com.project.fundoo_notes.service;

import com.project.fundoo_notes.builder.TokenUtil;
import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.NoteResponseDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.model.NoteModel;
import com.project.fundoo_notes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * purpose : Service for note
 * @author : Swati
 * @version : 1.0
 * @since : 3-7-21
 **/
@Service
public class NoteService implements INoteService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * purpose : create note
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/

    @Override
    public ResponseDTO create(NoteDTO noteDTO, String token) {
        Long id = tokenUtil.decodeToken(token);
        noteDTO.setUserId(id);
        NoteModel note=modelMapper.map(noteDTO,NoteModel.class);
        noteRepository.save(note);
        return new ResponseDTO("Note created successfully",200);
    }
    /**
     * purpose : Get all notes
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/

    @Override
    public NoteResponseDTO getNote(String token) {
        Long id = tokenUtil.decodeToken(token);
        List notesList  = Collections.singletonList(noteRepository.findByUserId(id));
        if(notesList.isEmpty()){
            return new NoteResponseDTO(notesList,404);
        }else
            return new NoteResponseDTO(notesList, 200);
    }
    /**
     * purpose : update existing note
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/

    @Override
    public ResponseDTO updateNote(String token, NoteDTO noteDTO, long noteId) {
        long id = tokenUtil.decodeToken(token);
        Optional<List> notesList = noteRepository.findByUserId(id);
        if(notesList.isPresent()){
            Optional<NoteModel> notes = noteRepository.findById(noteId);
            if(notes.isPresent()) {
                NoteModel note=modelMapper.map(noteDTO,NoteModel.class);
                note.setUpdateDate(LocalDateTime.now());
                note.setId(noteId);
                note.setUserId(id);
                noteRepository.save(note);
                return new ResponseDTO("Note updated Successfully", 200);
            }
            return new ResponseDTO("Note is not present",404);
        }else
            return new ResponseDTO("User not present", 400);
    }

    /**
     * purpose : delete note
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/
    @Override
    public ResponseDTO deleteNote(String token, long noteId) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List> notesList = noteRepository.findByUserId(id);
        if(notesList.isPresent()){
            Optional<NoteModel> note = noteRepository.findById(noteId);
            if(note.isPresent()){
                note.get().setTrash(true);
                noteRepository.save(note.get());
                return new ResponseDTO("move to trash",200);
            }
        }
        return new ResponseDTO("Note not found",404);
    }

    /**
     * purpose : Ability to archieve notes
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/
    @Override
    public ResponseDTO archieveNote(String token, long noteId) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List> notes = noteRepository.findByUserId(id);
        if(notes.isPresent()){
            Optional<NoteModel> note = noteRepository.findById(noteId);
            if(note.isPresent() && note.get().getUserId()==id) {
                note.get().setArchieve(true);
                noteRepository.save(note.get());
                return new ResponseDTO("Note in archive", 200);
            }
        }
        return new ResponseDTO("Note or user not found",400);
    }
    /**
     * purpose : Ability to pin a note
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/

    @Override
    public ResponseDTO pinNote(String token, long noteId) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List>  user = noteRepository.findByUserId(id);
        if(user.isPresent()){
            Optional<NoteModel> note = noteRepository.findById(noteId);
            if(note.isPresent() && note.get().getUserId() == id) {
                note.get().setPin(true);
                noteRepository.save(note.get());
                return new ResponseDTO("Note is pined", 200);
            }
        }
        return new ResponseDTO("User or Note not found",400);
    }

    /**
     * purpose :Ability to get all note from trash
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/
    @Override
    public NoteResponseDTO getAllNoteFromTrash(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List> notes = noteRepository.findByUserId(id);
        List getAllTrashNote=null;
        if (notes.isPresent()){
            getAllTrashNote = noteRepository.findAll().stream().
                    filter(var-> var.getUserId()==id && var.isTrash()==true ).collect(Collectors.toList());
            return new NoteResponseDTO(getAllTrashNote,200);
        }
       return new NoteResponseDTO(getAllTrashNote,400);
    }

    /**
     * purpose : Ability to get all pin note
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/
    @Override
    public NoteResponseDTO getAllPinNote(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List>  notes = noteRepository.findByUserId(id);
        List getAllPinedNote = null;
        if (notes.isPresent()){
            getAllPinedNote = noteRepository.findAll().stream().
                    filter(var->var.getUserId()==id && var.isPin()==true ).collect(Collectors.toList());
            return new  NoteResponseDTO(getAllPinedNote,200);
        }
        return new NoteResponseDTO(getAllPinedNote,400);
    }
    /**
     * purpose : Ability to get all note from Archieve
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/

    @Override
    public NoteResponseDTO getAllNoteFromArchieve(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List>  notes = noteRepository.findByUserId(id);
        List getAllArchieveNote = null;
        if (notes.isPresent()) {
            getAllArchieveNote = noteRepository.findAll().stream().
                    filter(var -> var.getUserId()==id && var.isArchieve() == true ).collect(Collectors.toList());
            return new NoteResponseDTO(getAllArchieveNote,200);
        }
        return new NoteResponseDTO(getAllArchieveNote,400);
    }
    /**
     * purpose : Get all note from trash and archieve
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/

    @Override
    public NoteResponseDTO getAllNoteFromTrashAndArchieve(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List>  notes = noteRepository.findByUserId(id);
        List getAllTrashArchiveNote = null;
        if (notes.isPresent()) {
            getAllTrashArchiveNote = noteRepository.findAll().stream().
                    filter(var -> var.getUserId() == id && var.isArchieve() == true &&  var.isTrash() == true ).collect(Collectors.toList());
            return new  NoteResponseDTO(getAllTrashArchiveNote,200);
        }
        return new NoteResponseDTO(getAllTrashArchiveNote,200);
    }
    /**
     * purpose : Ability to set color of note
     * @author : Swati
     * @version : 1.0
     * @since : 3-7-21
     **/

    @Override
    public ResponseDTO setNoteColor(String token, Long noteId, String color) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List>  user = noteRepository.findByUserId(id);
        if(user.isPresent()){
            Optional<NoteModel> note = noteRepository.findById(noteId);
            if(note.isPresent() && note.get().getUserId()==id){
                note.get().setColor(color);
                noteRepository.save(note.get());
                return new ResponseDTO("Note color change",200);
            }
        }
        return new ResponseDTO("Please check user or note id",400);
    }
}
