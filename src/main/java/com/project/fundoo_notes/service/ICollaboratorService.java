package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.CollaboratorDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * purpose : interface for collaborator
 * @author : Swati
 * @version : 1.0
 * @since : 7-7-21
 **/

@Service
public interface ICollaboratorService {
    ResponseDTO addCollaboratorForNote(CollaboratorDTO collaboratorDTO);

    ResponseDTO removeCollaborator(Long noteId, String email);
//
    List getCollaborator(Long noteId);
}
