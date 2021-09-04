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
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public ResponseDTO create(NoteDTO noteDTO, String token) {
        Long id = tokenUtil.decodeToken(token);
        noteDTO.setUserId(id);
        NoteModel note=modelMapper.map(noteDTO,NoteModel.class);
        noteRepository.save(note);
        return new ResponseDTO("Note created successfully",200);
    }

    @Override
    public NoteResponseDTO getNote(String token) {
        Long id = tokenUtil.decodeToken(token);
        List notesList  = Collections.singletonList(noteRepository.findByUserId(id));
        if(notesList.isEmpty()){
            return new NoteResponseDTO(notesList,404);
        }else {
            return new NoteResponseDTO(notesList, 200);
        }
    }

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

    @Override
    public ResponseDTO archieveNote(String token, long noteId) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List> notes = noteRepository.findByUserId(id);
        if(notes.isPresent()){
            Optional<NoteModel> note = noteRepository.findById(noteId);
            if(note.get().isArchieve()){
                return new ResponseDTO("Note in archive",200);
            }else{
                return new ResponseDTO("Note not in archive",200);
            }
        }
        return new ResponseDTO("Note not found",400);
    }

    @Override
    public ResponseDTO pinNote(String token, long noteId) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List>  notes = noteRepository.findByUserId(id);
        if(notes.isPresent()){
            Optional<NoteModel> note = noteRepository.findById(noteId);
            if(note.get().isPin()){
                return new ResponseDTO("Note in pin",200);
            }else{
                return new ResponseDTO("Note not pin",200);
            }
        }
        return new ResponseDTO("Note not found",400);
    }

    @Override
    public NoteResponseDTO getAllNoteByTrash(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<List>  notes = noteRepository.findByUserId(id);
        List getAllTrashNote=null;
        if (notes.isPresent()){
            getAllTrashNote = noteRepository.findAll().stream().
                    filter(trash->trash.isTrash()==true).collect(Collectors.toList());
            return new NoteResponseDTO(getAllTrashNote,200);
        }
       return new NoteResponseDTO(getAllTrashNote,400);

    }

    @Override
    public List getAllNoteByPin() {
        List getAllPinedNote = noteRepository.findAll().stream().filter(pin->pin.isPin()==true).collect(Collectors.toList());
        return getAllPinedNote;
    }

    @Override
    public List getAllNoteByArchieve() {
        List getAllArchieveNote = noteRepository.findAll().stream().filter(arc->arc.isArchieve()==true).collect(Collectors.toList());
        return getAllArchieveNote;
    }

    @Override
    public List getAllNoteByTrashAndArchieve() {
        Predicate<NoteModel> isTrash = t -> t.isTrash() == true;
        Predicate<NoteModel> isArchive = a -> a.isArchieve() == true;
        List getAllTrashArchiveNote = noteRepository.findAll().stream().
                filter(isTrash.and(isArchive)).collect(Collectors.toList());
        System.out.println(getAllTrashArchiveNote);
        return getAllTrashArchiveNote;
    }
}
