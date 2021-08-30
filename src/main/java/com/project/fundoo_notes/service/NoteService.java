package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.model.NoteModel;
import com.project.fundoo_notes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService implements INoteService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NoteRepository repository;

    @Override
    public ResponseDTO create(NoteDTO noteDTO) {
        NoteModel note=modelMapper.map(noteDTO,NoteModel.class);
        repository.save(note);
        return new ResponseDTO("Created",200);
    }
}
