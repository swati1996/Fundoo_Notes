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


/**
 * purpose : Service for label
 * @author : Swati
 * @version : 1.0
 * @since : 4-7-21
 **/
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

    /**
     * purpose :create label for note
     * @author : Swati
     * @version : 1.0
     * @since : 4-7-21
     **/

    @Override
    public LabelResponseDTO create(LabelDTO labelDTO, String token, Long noteId) {
        long id = tokenUtil.decodeToken(token);
        NoteModel noteModel = noteRepository.findByIdAndUserId(noteId,id);
        if(noteModel.getUserId()==id){
            LabelModel labelModel= new LabelModel();
            labelModel.setUpdatedDate(LocalDateTime.now());
            labelModel.setNoteId(noteId);
            labelModel.setUserId(id);
            labelModel.setLabelname(labelDTO.getLabelname());
            labelRepository.save(labelModel);
            noteModel.getLabelList().add(labelModel);
            noteRepository.save(noteModel);
            return new LabelResponseDTO("Label Created",200);
        }
        return new LabelResponseDTO("Please check note Id",400);
    }
    /**
     * purpose : update existing label
     * @author : Swati
     * @version : 1.0
     * @since : 4-7-21
     **/

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

    /**
     * purpose : delete existing label
     * @author : Swati
     * @version : 1.0
     * @since : 4-7-21
     **/

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

    /**
     * purpose : get all label
     * @author : Swati
     * @version : 1.0
     * @since : 4-7-21
     **/

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
    /**
     * purpose : Ability to create note as label
     * @author : Swati
     * @version : 1.0
     * @since : 4-7-21
     **/

    @Override
    public LabelResponseDTO NoteAsLabel(String token, long noteId, long labelId) {
        System.out.println("test");
        long id = tokenUtil.decodeToken(token);
        System.out.println(id);
        NoteModel note = noteRepository.findByIdAndUserId(noteId, id);
        System.out.println(note);
        if(note.getUserId() == id){
            Optional<LabelModel> label = labelRepository.findByIdAndUserId(labelId,id);
            if(label.get().getUserId()==id){
                note.setUpdateDate(LocalDateTime.now());
                note.getLabelList().add(label.get());
                System.out.println(note.getLabelList());
                label.get().setNoteId(noteId);
                label.get().setUpdatedDate(LocalDateTime.now());
                label.get().getNotes().add(note);
                labelRepository.save(label.get());
                noteRepository.save(note);
                return new LabelResponseDTO("Note as label created", 200);
            }
        }
        return new LabelResponseDTO("Label or user not present", 400);
    }

    /**
     * purpose : Ability to delete note as label
     * @author : Swati
     * @version : 1.0
     * @since : 4-7-21
     **/
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
