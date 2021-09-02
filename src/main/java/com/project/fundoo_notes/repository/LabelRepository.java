package com.project.fundoo_notes.repository;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.model.LabelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface LabelRepository extends JpaRepository <LabelModel, Long>{

}
