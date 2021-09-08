package com.project.fundoo_notes.repository;

import com.project.fundoo_notes.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * purpose : Repository  for Note
 * @author : Swati
 * @version : 1.0
 * @since : 3-7-21
 **/
@Repository
@EnableJpaRepositories
public interface NoteRepository extends JpaRepository<NoteModel,Long> {
    NoteModel findByIdAndUserId(Long noteId, Long userId);
    Optional<List> findByUserId(Long userId);

}
