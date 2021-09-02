package com.project.fundoo_notes.service;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelResponseDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.model.LabelModel;
import com.project.fundoo_notes.repository.LabelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LabelService implements ILabelService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LabelRepository labelRepository;

    @Override
    public LabelResponseDTO create(LabelDTO labelDTO) {
        LabelModel labelModel = modelMapper.map(labelDTO,LabelModel.class);
        labelRepository.save(labelModel);
        return new LabelResponseDTO("Label Created",200);
    }

    @Override
    public LabelResponseDTO update(long id, LabelDTO labelDTO) {
        Optional<LabelModel> labelModel= labelRepository.findById(id);
        if(labelModel.isPresent()){
            LabelModel updateLabel = modelMapper.map(labelDTO,LabelModel.class);
            labelRepository.save(updateLabel);
            return new LabelResponseDTO("Label updated Successfully",200);

        }else
            return new LabelResponseDTO("Label not found",400);
    }


}
