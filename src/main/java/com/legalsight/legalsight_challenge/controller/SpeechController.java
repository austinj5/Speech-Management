package com.legalsight.legalsight_challenge.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.legalsight.legalsight_challenge.dto.SpeechDTO;
import com.legalsight.legalsight_challenge.service.SpeechService;

// API Layer
@RestController
@RequestMapping("/api/speeches")
public class SpeechController {
    private final SpeechService speechService;

    public SpeechController(SpeechService speechService) {
        this.speechService = speechService;
    }

    // GET
    @GetMapping
    public List<SpeechDTO> getAllSpeeches() {
        return speechService.getAllSpeeches();
    }

    // POST
    @PostMapping
    public SpeechDTO addSpeech(@RequestBody SpeechDTO speechDTO) {
        return speechService.addSpeech(speechDTO);
    }

    // PUT
    @PutMapping("/{id}")
    public SpeechDTO editSpeech(@PathVariable Long id, @RequestBody SpeechDTO speechDTO) {
        return speechService.editSpeech(id, speechDTO);
    }

    // DELETE   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeech(@PathVariable Long id) {
        speechService.deleteSpeech(id);
        return ResponseEntity.noContent().build();
    }

    // GET
    @GetMapping("/search")
    public List<SpeechDTO> searchSpeeches(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String snippet,
            @RequestParam(required = false) String keyword) {
        return speechService.searchSpeeches(author, startDate, endDate, snippet, keyword);
    }
}