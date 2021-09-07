package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.CollaboratorDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ICollaboratorService {
    void addCollaboratorForNote(String token, Long noteId, CollaboratorDTO userEmail);
}
