package com.project.fundoo_notes.repository;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.model.LabelModel;
import com.project.fundoo_notes.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * purpose : Repository  for label
 * @author : Swati
 * @version : 1.0
 * @since : 4-7-21
 **/
@Repository
@EnableJpaRepositories
public interface LabelRepository extends JpaRepository <LabelModel, Long>{
    Optional<List> findByUserId(Long userId);
    Optional<LabelModel> findByIdAndUserId(Long labelId, Long userId);


}
