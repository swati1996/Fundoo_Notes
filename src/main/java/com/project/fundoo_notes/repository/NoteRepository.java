package com.project.fundoo_notes.repository;

import com.project.fundoo_notes.model.LabelModel;
import com.project.fundoo_notes.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface NoteRepository extends JpaRepository<NoteModel,Long> {
    Optional<NoteModel> findById(long id);
    Optional<NoteModel> findByIdAndUserId(Long noteId, Long userId);
    Optional<List> findByUserId(Long userId);

}
