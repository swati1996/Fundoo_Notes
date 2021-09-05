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
        Optional<List> noteModel = noteRepository.findByIdAndUserId(noteId,id);
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
        System.out.println(id);
        Optional<List> user= labelRepository.findByUserId(id);
        System.out.println(user);
        if(user.isPresent()){
            Optional<LabelModel> label = labelRepository.findById(labelId);
            System.out.println(label);
            if(label.isPresent()){
                System.out.println("***************");
                label.get().setLabelname(labelDTO.getLabelname());
//                LabelModel labelModel= modelMapper.map(label,LabelModel.class);
//                System.out.println(labelModel);
//                labelModel.setUpdatedDate(LocalDateTime.now());
//                labelModel.setUserId(id);
                labelRepository.save(label.get());
                return new LabelResponseDTO("Label updated Successfully",200);
            }
        }
            return new LabelResponseDTO("Label not found",400);
    }
//
//    @Override
//    public LabelResponseDTO delete(String token) {
//        long id = tokenUtil.decodeToken(token);
//        Optional<LabelModel> labelModel = labelRepository.findById(id);
//        if(labelModel.isPresent()){
//            labelRepository.delete(labelModel.get());
//            return new LabelResponseDTO("Label deleted",200);
//        }
//        return new LabelResponseDTO("Label not found",400);
//    }
//
//    @Override
//    public LabelResponseDTO getLabel(String token) {
//        long id = tokenUtil.decodeToken(token);
//        Optional<LabelModel> labelModel = labelRepository.findById(id);
//        if(labelModel.isPresent()){
//            return new LabelResponseDTO("Label present",200);
//        }
//        return new LabelResponseDTO("Label not present",400);
//
//    }
//
//    @Override
//    public LabelResponseDTO labelAsNote(String token, long noteId, long labelId) {
//        long id = tokenUtil.decodeToken(token);
//        Optional<NoteModel> note = noteRepository.findByIdAndUserId(noteId,id);
//        Optional<LabelModel> label = labelRepository.findById(labelId);
//        note.get().setUpdateDate(LocalDateTime.now());
//        note.get().getLabelList().add(label.get());
//        noteRepository.save(note.get());
//
//        return new LabelResponseDTO("Label as note",200);
//    }
//
//    @Override
//    public LabelResponseDTO DeleteLabelAsNote(String token, long noteId, long labelId) {
//        long id = tokenUtil.decodeToken(token);
//        Optional<NoteModel> note = noteRepository.findByIdAndUserId(noteId,id);
//        Optional<LabelModel> label = labelRepository.findById(labelId);
//        noteRepository.delete(note.get());
//        return new LabelResponseDTO("Delete label",200);
//    }
//

}
