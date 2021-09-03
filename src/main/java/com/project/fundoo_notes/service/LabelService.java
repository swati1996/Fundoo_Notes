package com.project.fundoo_notes.service;

import com.project.fundoo_notes.builder.TokenUtil;
import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelResponseDTO;
import com.project.fundoo_notes.model.LabelModel;
import com.project.fundoo_notes.model.NoteModel;
import com.project.fundoo_notes.repository.LabelRepository;
import com.project.fundoo_notes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public LabelResponseDTO create(LabelDTO labelDTO,String token) {
        long id = tokenUtil.decodeToken(token);
//        Optional<UserModel> userData= userRepository.findById(id);
//        if(!userData.isPresent()){
//            throw new UserNotFoundException("User not found",UserNotFoundException.ExceptionType.USER_NOT_FOUND);
//
//        }
        LabelModel labelModel = modelMapper.map(labelDTO,LabelModel.class);
        labelRepository.save(labelModel);
        return new LabelResponseDTO("Label Created",200);
    }

    @Override
    public LabelResponseDTO update(String token, LabelDTO labelDTO) {
        long id = tokenUtil.decodeToken(token);
        Optional<LabelModel> labelModel= labelRepository.findById(id);
        if(labelModel.isPresent()){
            LabelModel updateLabel = modelMapper.map(labelDTO,LabelModel.class);
            labelRepository.save(updateLabel);
            return new LabelResponseDTO("Label updated Successfully",200);

        }else
            return new LabelResponseDTO("Label not found",400);
    }

    @Override
    public LabelResponseDTO delete(String token) {
        long id = tokenUtil.decodeToken(token);
        Optional<LabelModel> labelModel = labelRepository.findById(id);
        if(labelModel.isPresent()){
            labelRepository.delete(labelModel.get());
            return new LabelResponseDTO("Label deleted",200);
        }
        return new LabelResponseDTO("Label not found",400);
    }

    @Override
    public LabelResponseDTO getLabel(String token) {
        long id = tokenUtil.decodeToken(token);
        Optional<LabelModel> labelModel = labelRepository.findById(id);
        if(labelModel.isPresent()){
            return new LabelResponseDTO("Label present",200);
        }
        return new LabelResponseDTO("Label not present",400);

    }

    @Override
    public LabelResponseDTO labelAsNote(String token, long noteId, long labelId) {
        long id = tokenUtil.decodeToken(token);
        Optional<NoteModel> note = noteRepository.findByIdAndUserId(noteId,id);
        Optional<LabelModel> label = labelRepository.findById(labelId);
        note.get().setUpdateDate(LocalDateTime.now());
        note.get().getLabelList().add(label.get());
        noteRepository.save(note.get());

        return new LabelResponseDTO("Label as note",200);
    }

    @Override
    public LabelResponseDTO DeleteLabelAsNote(String token, long noteId, long labelId) {
        long id = tokenUtil.decodeToken(token);
        Optional<NoteModel> note = noteRepository.findByIdAndUserId(noteId,id);
        Optional<LabelModel> label = labelRepository.findById(labelId);
        noteRepository.delete(note.get());
        return new LabelResponseDTO("Delete label",200);
    }


}