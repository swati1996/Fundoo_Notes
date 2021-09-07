package com.project.fundoo_notes.service;

import com.project.fundoo_notes.builder.TokenUtil;
import com.project.fundoo_notes.dto.CollaboratorDTO;
import com.project.fundoo_notes.model.NoteModel;
import com.project.fundoo_notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollaboratorService implements ICollaboratorService{
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private MailService mailService;

    @Override
    public void addCollaboratorForNote(String token, Long noteId, CollaboratorDTO collDTO) {
        Optional<NoteModel> note = noteRepository.findById(noteId);
        Long id = tokenUtil.decodeToken(token);
        if(note.isPresent() && note.get().getUserId()==id){
            for (int i=0;i<collDTO.getCollList().size();i++){
//                mailService.send((String) collDTO.getCollList().get(i),"Title: "+note.get().getTitle(),
//                        "Description: "+note.get().getDescription());
            }
        }
    }
}
