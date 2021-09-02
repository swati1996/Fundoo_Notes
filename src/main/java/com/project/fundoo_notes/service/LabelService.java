package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.model.LabelModel;
import com.project.fundoo_notes.repository.LabelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService implements ILabelService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LabelRepository labelRepository;

    @Override
    public ResponseDTO create(LabelDTO labelDTO) {
        LabelModel labelModel = modelMapper.map(labelDTO,LabelModel.class);
        labelRepository.save(labelModel);
        return new ResponseDTO("created",200,"");
    }
}
