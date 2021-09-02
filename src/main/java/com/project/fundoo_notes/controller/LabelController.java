package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelResponseDTO;
import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LabelController {
    @Autowired
    private ILabelService service;

    @PostMapping(value = "createlabel")
    public ResponseEntity<ResponseDTO> createLabel(@RequestBody LabelDTO labelDTO){
        LabelResponseDTO res = service.create(labelDTO);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
    @PutMapping(value = "updatelabel/{id}")
    public ResponseEntity<ResponseDTO> updateLabel(@RequestParam long id,
                                                   @RequestBody LabelDTO labelDTO){
        LabelResponseDTO res = service.update(id,labelDTO);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "deletelabel/{id}")
    public ResponseEntity<ResponseDTO> deleteLabel(@RequestParam long id){
        LabelResponseDTO res = service.delete(id);
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
}
