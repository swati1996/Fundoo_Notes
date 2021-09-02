package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabelController {
    @Autowired
    private ILabelService service;

    @PostMapping(value = "createlabel")
    public ResponseEntity<ResponseDTO> createNote(@RequestBody LabelDTO labelDTO){
        ResponseDTO res = service.create(labelDTO);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
}
