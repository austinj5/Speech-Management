package com.legalsight.legalsight_challenge.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.legalsight.legalsight_challenge.dto.SpeechDTO;
import com.legalsight.legalsight_challenge.entity.Speech;
import com.legalsight.legalsight_challenge.mapper.SpeechMapper;
import com.legalsight.legalsight_challenge.repository.SpeechRepository;

// Service Layer
@Service // for dependency injection
public class SpeechService {
    private final SpeechRepository speechRepository;

    public SpeechService(SpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
    }

    // VIEW ALL SPEECHES
    public List<SpeechDTO> getAllSpeeches() {
    List<Speech> allSpeeches = speechRepository.findAll();
    List<SpeechDTO> speechDTOs = new ArrayList<>();
    for (Speech speech : allSpeeches) {
        SpeechDTO dto = SpeechMapper.INSTANCE.toDTO(speech);
        speechDTOs.add(dto);
    }

    return speechDTOs;
    }

    // ADD SPEECH
    public SpeechDTO addSpeech(SpeechDTO speechDTO) {
        Speech speech = SpeechMapper.INSTANCE.toEntity(speechDTO);
        Speech savedSpeech = speechRepository.save(speech);

        return SpeechMapper.INSTANCE.toDTO(savedSpeech);
    }

    // EDIT SPEECH
    public SpeechDTO updateSpeech(Long id, SpeechDTO speechDTO) {
        Speech existingSpeech = speechRepository.findById(id).orElse(null);
        if (existingSpeech == null) {
            throw new RuntimeException("Speech not found");
        }

        existingSpeech.setAuthor(speechDTO.getAuthor());
        existingSpeech.setSpeechText(speechDTO.getSpeechText());
        existingSpeech.setKeywords(speechDTO.getKeywords());
        existingSpeech.setSpeechDate(speechDTO.getSpeechDate());

        Speech updatedSpeech = speechRepository.save(existingSpeech);

        return SpeechMapper.INSTANCE.toDTO(updatedSpeech);
    }

    // DELETE SPEECH
    public void deleteSpeech(Long id) {
        speechRepository.deleteById(id);
    }

    // SEARCH SPEECHES
    public List<SpeechDTO> searchSpeeches(String author, LocalDate startDate, LocalDate endDate, String snippet) {
        List<Speech> allSpeeches = speechRepository.findAll();
        List<SpeechDTO> filteredSpeeches = new ArrayList<>();
        
        for (Speech speech : allSpeeches) {
            boolean matchesAuthor = (author == null || speech.getAuthor().equals(author));
            boolean matchesDateRange = (startDate == null || endDate == null ||
                    (speech.getSpeechDate().isAfter(startDate) && speech.getSpeechDate().isBefore(endDate)));
            boolean matchesSnippet = (snippet == null || speech.getSpeechText().contains(snippet));
            
            if (matchesAuthor && matchesDateRange && matchesSnippet) {
                SpeechDTO dto = SpeechMapper.INSTANCE.toDTO(speech);
                filteredSpeeches.add(dto);
            }
        }
        
        return filteredSpeeches;
    }

}
