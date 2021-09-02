package com.project.fundoo_notes.service;

import com.project.fundoo_notes.builder.TokenUtil;
import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.model.NoteModel;
import com.project.fundoo_notes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NoteRepository repository;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public ResponseDTO create(NoteDTO noteDTO) {
        NoteModel note=modelMapper.map(noteDTO,NoteModel.class);
        repository.save(note);
        String token = tokenUtil.createToken(note.getId());
        return new ResponseDTO("Created",200,token);
    }

    @Override
    public ResponseDTO getNote(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<NoteModel> note = repository.findById(id);
        if(note.isPresent()){
            return new ResponseDTO("Note is present",200,token);

        }else
            return new ResponseDTO("Note is not present",404,token);
    }

    @Override
    public ResponseDTO updateNote(String token, NoteDTO noteDTO) {
        long id = tokenUtil.decodeToken(token);
        Optional<NoteModel> note = repository.findById(id);
        if(note.isPresent()){
            NoteModel updateNote = modelMapper.map(noteDTO,NoteModel.class);
            repository.save(updateNote);
            return new ResponseDTO("Note updated Successfully",200, token);
        }else
            return new ResponseDTO("Note is not present",404,token);
    }

    @Override
    public ResponseDTO deleteNote(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<NoteModel> note = repository.findById(id);
        if(note.isPresent()){
            note.get().setTrash(true);
            repository.save(note.get());
            return new ResponseDTO("move to trash",200,token);
        }
        return new ResponseDTO("Note not found",404,token);
    }

    @Override
    public ResponseDTO archieveNote(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<NoteModel> note = repository.findById(id);
        if(note.isPresent()){
            if(note.get().isArchieve()){
                return new ResponseDTO("Note in archive",200,token);
            }else{
                return new ResponseDTO("Note not in archive",200,token);
            }
        }
        return new ResponseDTO("Note not found",400,token);
    }

    @Override
    public ResponseDTO pinNote(String token) {
        Long id = tokenUtil.decodeToken(token);
        Optional<NoteModel> note = repository.findById(id);
        if(note.isPresent()){
            if(note.get().isPin()){
                return new ResponseDTO("Note in pin",200,token);
            }else{
                return new ResponseDTO("Note not pin",200,token);
            }
        }
        return new ResponseDTO("Note not found",400,token);
    }

    @Override
    public List getAllNoteByTrash() {
        List getAllTrashNote =repository.findAll().stream().filter(trash->trash.isTrash()==true).collect(Collectors.toList());
        return getAllTrashNote;
    }

    @Override
    public List getAllNoteByPin() {
        List getAllPinedNote =repository.findAll().stream().filter(pin->pin.isPin()==true).collect(Collectors.toList());
        return getAllPinedNote;
    }

    @Override
    public List getAllNoteByArchieve() {
        List getAllArchieveNote =repository.findAll().stream().filter(arc->arc.isArchieve()==true).collect(Collectors.toList());
        return getAllArchieveNote;
    }

    @Override
    public List getAllNoteByTrashAndArchieve() {
        Predicate<NoteModel> isTrash = t -> t.isTrash() == true;
        Predicate<NoteModel> isArchive = a -> a.isArchieve() == true;
        List getAllTrashArchiveNote =repository.findAll().stream().
                filter(isTrash.and(isArchive)).collect(Collectors.toList());
        System.out.println(getAllTrashArchiveNote);
        return getAllTrashArchiveNote;
    }
}
