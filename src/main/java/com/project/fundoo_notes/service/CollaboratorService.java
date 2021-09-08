package com.project.fundoo_notes.service;

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
    private NoteRepository noteRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

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
    public ResponseDTO addCollaboratorForNote(CollaboratorDTO collaboratorDTO) {
        Optional<NoteModel> note = noteRepository.findById(collaboratorDTO.getNoteId());
        if(note.isPresent() ) {
            collaboratorDTO.setEmail(collaboratorDTO.getEmail());
            collaboratorDTO.setNoteId(note.get().getId());
            CollaboratorModel coll = modelMapper.map(collaboratorDTO,CollaboratorModel.class);
            collaboratorRepository.save(coll);

            noteDTO.setTitle(note.get().getTitle());
            noteDTO.setDescription(note.get().getDescription());
            NoteModel newNote = modelMapper.map(noteDTO,NoteModel.class);
            newNote.setUserId(note.get().getUserId());
            newNote.setColor(note.get().getColor());
            newNote.setRegisterDate(note.get().getRegisterDate());
            newNote.setUpdateDate(LocalDateTime.now());
            noteRepository.save(newNote);

            mailService.send(collaboratorDTO.getEmail(), "Title: " + note.get().getTitle(),
                    "Description: " + note.get().getDescription());
            return new ResponseDTO("Collabration sucessfulll",200);
        }else
            return new ResponseDTO("User or note not found",400);
    }

    /**
     * purpose : Remove collaborator from note
     * @author : Swati
     * @version : 1.0
     * @since : 7-7-21
     **/
    @Override
    public ResponseDTO removeCollaborator(Long noteId, String email) {
        Optional<CollaboratorModel> user = collaboratorRepository.findByEmailAndNoteId(email,noteId);
        if(user.isPresent()){
            collaboratorRepository.delete(user.get());
            return new ResponseDTO("remove collaborator",200);
        }
        return new ResponseDTO("Note or email not found",400);
    }

    /**
     * purpose : get all collaborator
     * @author : Swati
     * @version : 1.0
     * @since : 7-7-21
     **/
    @Override
    public List getCollaborator(Long noteId) {
        List user = (List<CollaboratorModel>) collaboratorRepository.findByNoteId(noteId);
        if(user.isEmpty()){
            return  null;
        }else {
            return user;
        }
    }
}
