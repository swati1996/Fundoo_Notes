package com.project.fundoo_notes.service;

import com.project.fundoo_notes.builder.TokenUtil;
import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelListResponse;
import com.project.fundoo_notes.dto.LabelResponseDTO;
import com.project.fundoo_notes.model.LabelModel;
import com.project.fundoo_notes.model.NoteModel;
import com.project.fundoo_notes.repository.LabelRepository;
import com.project.fundoo_notes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LabelService implements ILabelService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private NoteRepository noteRepository;


    @Override
    public LabelResponseDTO create(LabelDTO labelDTO, String token, Long noteId) {
        long id = tokenUtil.decodeToken(token);
        System.out.println(id);
        Optional<NoteModel> noteModel = noteRepository.findByIdAndUserId(noteId,id);
        System.out.println(noteModel.get());
        if(noteModel.isPresent()){
            LabelModel labelModel= new LabelModel();
            labelModel.setUpdatedDate(LocalDateTime.now());
            labelModel.setNoteId(noteId);
            labelModel.setUserId(id);
            labelModel.setLabelname(labelDTO.getLabelname());
            labelRepository.save(labelModel);
            return new LabelResponseDTO("Label Created",200);
        }
        return new LabelResponseDTO("Please check note Id",400);
    }

    @Override
    public LabelResponseDTO update(String token, LabelDTO labelDTO,Long labelId) {
        long id = tokenUtil.decodeToken(token);
        Optional<List> user= labelRepository.findByUserId(id);
        if(user.isPresent()){
            Optional<LabelModel> label = labelRepository.findById(labelId);
            if(label.isPresent()){
                label.get().setLabelname(labelDTO.getLabelname());
                labelRepository.save(label.get());
                return new LabelResponseDTO("Label updated Successfully",200);
            }
        }
            return new LabelResponseDTO("Label not found",400);
    }

    @Override
    public LabelResponseDTO delete(String token,Long labelId) {
        long id = tokenUtil.decodeToken(token);
        Optional<List> user= labelRepository.findByUserId(id);
        if(user.isPresent()){
            Optional<LabelModel> label = labelRepository.findById(labelId);
            if(label.isPresent()) {
                labelRepository.delete(label.get());
                return new LabelResponseDTO("Label deleted",200);
            }
        }
        return new LabelResponseDTO("Label not found",400);
    }
//
    @Override
    public LabelListResponse getLabel(String token) {
        long id = tokenUtil.decodeToken(token);
        Optional<List> user = labelRepository.findByUserId(id);
        List labelList=null;
        if(user.isPresent()){
            labelList = user.get();
            return new LabelListResponse( labelList,200);
        }
        return new LabelListResponse(labelList,400);

    }

    @Override
    public LabelResponseDTO NoteAsLabel(String token, long noteId, long labelId) {
        long id = tokenUtil.decodeToken(token);
        Optional<NoteModel> note = noteRepository.findByIdAndUserId(noteId,id);
        if(note.get().getUserId() == id){
            Optional<LabelModel> label = labelRepository.findByIdAndUserId(labelId,id);
            if(label.get().getUserId()==id){
                note.get().setUpdateDate(LocalDateTime.now());
                note.get().getLabelList().add(label.get());
                label.get().setNoteId(noteId);
                label.get().setUpdatedDate(LocalDateTime.now());
                label.get().getNotes().add(note.get());
                labelRepository.save(label.get());
                noteRepository.save(note.get());
                return new LabelResponseDTO("Note as label created", 200);
            }
        }
        return new LabelResponseDTO("Label or user not present", 400);
    }

    @Override
    public LabelResponseDTO DeleteLabelAsNote(String token, long labelId) {
        long id = tokenUtil.decodeToken(token);
        Optional<List> user = labelRepository.findByUserId(id);
        if (user.isPresent()){
            Optional<LabelModel> label = labelRepository.findById(labelId);
            if (label.isPresent() && label.get().getUserId()==id) {
                labelRepository.delete(label.get());
                return new LabelResponseDTO("Delete label as note",200);
            }
        }
        return new LabelResponseDTO("label is not present",400);
    }
}
