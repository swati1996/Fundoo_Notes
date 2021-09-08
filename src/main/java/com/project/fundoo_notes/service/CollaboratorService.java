package com.project.fundoo_notes.service;

import com.project.fundoo_notes.builder.TokenUtil;
import com.project.fundoo_notes.dto.CollaboratorDTO;
import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.model.CollaboratorModel;
import com.project.fundoo_notes.model.NoteModel;
import com.project.fundoo_notes.repository.CollaboratorRepository;
import com.project.fundoo_notes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * purpose : Service for collaborator
 * @author : Swati
 * @version : 1.0
 * @since : 7-7-21
 **/
@Service
public class CollaboratorService implements ICollaboratorService{
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private MailService mailService;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private CollaboratorModel collaboratorModel;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NoteDTO noteDTO;
    /**
     * purpose : Add collaborator for note
     * @author : Swati
     * @version : 1.0
     * @since : 7-7-21
     **/

    @Override
    public ResponseDTO addCollaboratorForNote(String token, Long noteId, String email) {
        Optional<NoteModel> note = noteRepository.findById(noteId);
        Long id = tokenUtil.decodeToken(token);
        if(note.isPresent() && note.get().getUserId()==id) {
            collaboratorModel.setEmail(note.get().getEmailid());
            collaboratorModel.setNoteId(note.get().getId());
            collaboratorRepository.save(collaboratorModel);
            noteDTO.setTitle(note.get().getTitle());
            noteDTO.setDescription(note.get().getDescription());
            NoteModel newNote = modelMapper.map(noteDTO,NoteModel.class);
            newNote.setUserId(id);
            newNote.setColor(note.get().getColor());
            newNote.setRegisterDate(note.get().getRegisterDate());
            newNote.setUpdateDate(LocalDateTime.now());
            noteRepository.save(newNote);

            mailService.send(email, "Title: " + note.get().getTitle(),
                    "Description: " + note.get().getDescription());
            return new ResponseDTO("Collabration sucessfulll",200);
        }
        return new ResponseDTO("User or note not found",400);
    }

    /**
     * purpose : Remove collaborator from note
     * @author : Swati
     * @version : 1.0
     * @since : 7-7-21
     **/
    @Override
    public ResponseDTO removeCollaborator(String token, Long noteId, String email) {
        Long id = tokenUtil.decodeToken(token);
        Optional<CollaboratorModel> user = collaboratorRepository.findByUserIdAndEmail(id,email);
        if(user.isPresent()){
            collaboratorRepository.delete(user.get());
            return new ResponseDTO("remove collaborator",200);
        }
        return new ResponseDTO("User or email not found",400);
    }

    /**
     * purpose : get all collaborator
     * @author : Swati
     * @version : 1.0
     * @since : 7-7-21
     **/
    @Override
    public List getCollaborator(String token, Long noteId) {
        Long id = tokenUtil.decodeToken(token);
        List user = (List<CollaboratorModel>) collaboratorRepository.findByUserIdAndNoteId(id,noteId);
        if(user.isEmpty()){
            return  null;
        }else {
            return user;
        }
    }
}
