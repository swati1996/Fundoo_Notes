package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelResponseDTO;
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
    public ResponseEntity<LabelResponseDTO> createLabel(@RequestBody LabelDTO labelDTO,
                                                        @RequestHeader String token){
        LabelResponseDTO res = service.create(labelDTO,token);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
    @PutMapping(value = "updatelabel/")
    public ResponseEntity<LabelResponseDTO> updateLabel(@RequestHeader String token,
                                                   @RequestBody LabelDTO labelDTO){
        LabelResponseDTO res = service.update(token,labelDTO);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "deletelabel")
    public ResponseEntity<LabelResponseDTO> deleteLabel(@RequestHeader String token){
        LabelResponseDTO res = service.delete(token);
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @GetMapping(value = "getLabel")
    public ResponseEntity<LabelResponseDTO> getLabel(@RequestHeader String token){
        LabelResponseDTO res = service.getLabel(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @PostMapping(value = "createlabelasnote")
    public ResponseEntity<LabelResponseDTO> labelAsNote(@RequestHeader String token,
                                                        @RequestParam long noteId,
                                                        @RequestParam long labelId) {
        LabelResponseDTO res = service.labelAsNote(token, noteId, labelId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @DeleteMapping(value = "deletelabelasnote")
    public ResponseEntity<LabelResponseDTO> deleteLabelAsNote(@RequestHeader String token,
                                                        @RequestParam long noteId,
                                                        @RequestParam long labelId) {
        LabelResponseDTO res = service.DeleteLabelAsNote(token, noteId, labelId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
