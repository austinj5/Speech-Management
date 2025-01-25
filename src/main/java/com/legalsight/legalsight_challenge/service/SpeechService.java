package com.legalsight.legalsight_challenge.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.legalsight.legalsight_challenge.dto.SpeechDTO;
import com.legalsight.legalsight_challenge.entity.Speech;
import com.legalsight.legalsight_challenge.mapper.SpeechMapper;
import com.legalsight.legalsight_challenge.repository.SpeechRepository;

/**
 * Service layer for managing speeches.
 * Provides methods to perform CRUD operations and search functionalities
 * on speeches stored in the database.
 * 
 * <p> Features include: view all speeches, add/edit/delete speeches,
 * and search speeches based on various criteria</p>
 * 
 * @author Austin Johnson
 */
@Service // for dependency injection
public class SpeechService {
    private final SpeechRepository speechRepository;

    public SpeechService(SpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
    }

    /**
     * Retrieves all speeches from the database.
     * Converts the retrieved speeches into DTOs.
     * 
     * @return a list of SpeechDTO objects representing all speeches
     *         stored in the database.
     * @throws RuntimeException if an error occurs while retrieving speeches
     */
    public List<SpeechDTO> getAllSpeeches() {
    try {
        List<Speech> allSpeeches = speechRepository.findAll();
        List<SpeechDTO> speechDTOs = new ArrayList<>();
        for (Speech speech : allSpeeches) {
            SpeechDTO dto = SpeechMapper.INSTANCE.toDTO(speech);
            speechDTOs.add(dto);
        }

        return speechDTOs;
    } catch (Exception e) {
        throw new RuntimeException("Error retrieving all speeches", e);
    }
        
    }

    /**
     * Adds a speech to the database.
     * Converts the saved speech into a DTO
     * 
     * @return a speechDTO object of the saved peech
     * @param a speechDTO object to be added into the databse
     * @throws RuntimeException if an error occurs while adding the speech
     */
    public SpeechDTO addSpeech(SpeechDTO speechDTO) {
    try {
        Speech speech = SpeechMapper.INSTANCE.toEntity(speechDTO);
        Speech savedSpeech = speechRepository.save(speech);

        return SpeechMapper.INSTANCE.toDTO(savedSpeech);
    } catch (Exception e) {
        throw new RuntimeException("Error adding speech", e);
    }
    }

    /**
     * Edits the speech in the database with the given id.
     * Uses given DTO when setting new values.
     * 
     * @return a speechDTO of the edited speech
     * @param the id of the speech to edit and a speechDTO containing the values to change the speech to 
     * @throws RuntimeException if an error occurs while editing the speech
     */
    public SpeechDTO editSpeech(Long id, SpeechDTO speechDTO) {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException("Error editing speech", e);
        }
        
    }

    /**
     * Deletes the speech in the database with the given id.
     * 
     * @param the id of the speech to delete 
     * @throws RuntimeException if an error occurs while deleting the speech
     */
    public void deleteSpeech(Long id) {
        try {
            speechRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting speech", e);
        }
    }

    /**
     * Searches for a speach based on given criteria.
     * Gets the speeches and checks if the criteria match with it
     * 
     * <p> Criteria include: author, start/end date, text snippet, keyword</p>
     * 
     * @return a list of speechDTOs containing the filtered speeches
     * @param a String for author/snippet/keyword and a LocalDate for start/endDate
     *  used as the search criteria
     * @throws RuntimeException if an error occurs while searching for speeches
     */
    public List<SpeechDTO> searchSpeeches(String author, LocalDate startDate, LocalDate endDate, String snippet, String keyword) {
        try {
            List<Speech> allSpeeches = speechRepository.findAll();
            List<SpeechDTO> filteredSpeeches = new ArrayList<>();
            
            for (Speech speech : allSpeeches) {
                boolean matchesAuthor = (author == null || speech.getAuthor().equals(author));
                boolean matchesDateRange = (startDate == null || endDate == null ||
                        (speech.getSpeechDate().isAfter(startDate) && speech.getSpeechDate().isBefore(endDate)));
                boolean matchesSnippet = (snippet == null || speech.getSpeechText().contains(snippet));
                boolean matchesKeyword = (keyword == null || speech.getKeywords().contains(keyword));
                
                if (matchesAuthor && matchesDateRange && matchesSnippet && matchesKeyword) {
                    SpeechDTO dto = SpeechMapper.INSTANCE.toDTO(speech);
                    filteredSpeeches.add(dto);
                }
            }
            
            return filteredSpeeches;
        } catch (Exception e) {
            throw new RuntimeException("Error searching for speeches", e);
        }
    }

}
