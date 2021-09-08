package com.project.fundoo_notes.repository;

import com.project.fundoo_notes.model.CollaboratorModel;
import com.project.fundoo_notes.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

/**
 * purpose : repository for collaborator
 * @author : Swati
 * @version : 1.0
 * @since : 7-7-21
 **/
@Repository
@EnableJpaRepositories
public interface CollaboratorRepository  extends JpaRepository<CollaboratorModel,Long> {

    Optional<CollaboratorModel> findByUserIdAndEmail(Long userId,String email);
    List<CollaboratorModel> findByUserIdAndNoteId(Long userId, long noteId);
}
