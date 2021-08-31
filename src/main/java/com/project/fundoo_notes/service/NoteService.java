package com.project.fundoo_notes.service;

import com.project.fundoo_notes.builder.TokenUtil;
import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.model.NoteModel;
import com.project.fundoo_notes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        System.out.println(id);
        Optional<NoteModel> note = repository.findById(id);
        if(note.isPresent()){
            NoteModel updateNote = modelMapper.map(noteDTO,NoteModel.class);
            repository.save(note.get());
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
            return new ResponseDTO("move to trash",200,token);
//            repository.delete(note.get());
        }
        return new ResponseDTO("Note not found",404,token);
    }
}
