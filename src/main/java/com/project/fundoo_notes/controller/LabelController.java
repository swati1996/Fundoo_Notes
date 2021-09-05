package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.LabelDTO;
import com.project.fundoo_notes.dto.LabelListResponse;
import com.project.fundoo_notes.dto.LabelResponseDTO;
import com.project.fundoo_notes.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LabelController {
    @Autowired
    private ILabelService labelService;

    @PostMapping(value = "createLabel/{noteId}")
    public ResponseEntity<LabelResponseDTO> createLabel(@RequestBody LabelDTO labelDTO,
                                                        @RequestHeader String token,
                                                        @PathVariable Long noteId){
        LabelResponseDTO res = labelService.create(labelDTO,token,noteId);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
    @PutMapping(value = "updateLabel/{labelId}")
    public ResponseEntity<LabelResponseDTO> updateLabel(@RequestHeader String token,
                                                        @RequestBody LabelDTO labelDTO,
                                                        @PathVariable Long labelId){
        LabelResponseDTO res = labelService.update(token,labelDTO,labelId);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deleteLabel/{labelId}")
    public ResponseEntity<LabelResponseDTO> deleteLabel(@RequestHeader String token,
                                                        @PathVariable Long labelId){
        LabelResponseDTO res = labelService.delete(token,labelId);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping(value = "getLabel")
    public ResponseEntity<LabelListResponse> getLabel(@RequestHeader String token){
        LabelListResponse res = labelService.getLabel(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PostMapping(value = "createNoteAsLabel/{noteId}")
    public ResponseEntity<LabelResponseDTO> NodeAsLabel(@RequestHeader String token,
                                                        @PathVariable long noteId) {
        LabelResponseDTO res = labelService.NoteAsLabel(token, noteId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @DeleteMapping(value = "deleteLabelAsNote/{labelId}")
    public ResponseEntity<LabelResponseDTO> deleteLabelAsNote(@RequestHeader String token,
                                                        @PathVariable long labelId) {
        LabelResponseDTO res = labelService.DeleteLabelAsNote(token, labelId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
