package com.legalsight.legalsight_challenge;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.legalsight.legalsight_challenge.dto.SpeechDTO;
import com.legalsight.legalsight_challenge.entity.Speech;
import com.legalsight.legalsight_challenge.mapper.SpeechMapper;
import com.legalsight.legalsight_challenge.repository.SpeechRepository;
import com.legalsight.legalsight_challenge.service.SpeechService;


public class ServiceTests {
    @Mock
    private SpeechRepository speechRepository;

    @InjectMocks
    private SpeechService speechService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSpeeches() {
        List<Speech> s = List.of(
            new Speech(1L, "Author 1", "text 1", List.of("keyword1"), LocalDate.of(2025, 1, 1)),
            new Speech(2L, "Author 2", "text 2", List.of("keyword2"), LocalDate.of(2025, 2, 1))
        );

        when(speechRepository.findAll()).thenReturn(s);

        List<SpeechDTO> result = speechService.getAllSpeeches();

        assertEquals(2, result.size());
        assertEquals("Author 1", result.get(0).getAuthor());
        assertEquals("text 1", result.get(0).getSpeechText());
        
    }

    @Test
    public void testAddSpeech() {
        SpeechDTO speechDTO = new SpeechDTO();
        speechDTO.setAuthor("Author 1");
        speechDTO.setSpeechText("text");
        speechDTO.setKeywords(Arrays.asList("keyword1", "keyword2"));
        speechDTO.setSpeechDate(LocalDate.of(2025, 1, 1));

        Speech mockSpeech = SpeechMapper.INSTANCE.toEntity(speechDTO);
        mockSpeech.setId(1L);

        when(speechRepository.save(any(Speech.class))).thenReturn(mockSpeech);

        SpeechDTO result = speechService.addSpeech(speechDTO);

        assertEquals("Author 1", result.getAuthor());
        
    }

    @Test
    public void testEditSpeech() {
        
        Speech existingSpeech = new Speech(1L, "Author 1", "text", List.of("keyword1"), LocalDate.of(2024, 1, 1));
        SpeechDTO updatedSpeechDTO = new SpeechDTO();
        updatedSpeechDTO.setAuthor("Updated author");
        updatedSpeechDTO.setSpeechText("Updated text");
        updatedSpeechDTO.setKeywords(List.of("updatedKeyword"));
        updatedSpeechDTO.setSpeechDate(LocalDate.of(2025, 1, 1));

        when(speechRepository.findById(1L)).thenReturn(Optional.of(existingSpeech));
        when(speechRepository.save(any(Speech.class))).thenReturn(existingSpeech);

        SpeechDTO result = speechService.editSpeech(1L, updatedSpeechDTO);

        assertEquals("Updated author", result.getAuthor());
        assertEquals("Updated text", result.getSpeechText());
        assertEquals(List.of("updatedKeyword"), result.getKeywords());
        assertEquals(LocalDate.of(2025, 1, 1), result.getSpeechDate());
    }

    @Test
    public void testDeleteSpeech() {
        Long id = 1L;

        doNothing().when(speechRepository).deleteById(id);

        speechService.deleteSpeech(id);

    }

    @Test
    public void testSearchSpeechesAll() {
        List<Speech> s = Arrays.asList(
            new Speech(1L, "Author 1", "text and word", List.of("keyword1", "keyword2"), LocalDate.of(2023, 1, 15)),
            new Speech(2L, "Author 2", "text 2", List.of("keyword3"), LocalDate.of(2023, 2, 15))
        );

        when(speechRepository.findAll()).thenReturn(s);

        List<SpeechDTO> result = speechService.searchSpeeches(
            "Author 1",
            LocalDate.of(2023, 1, 1),
            LocalDate.of(2023, 1, 31),
            "word",
            "keyword1"
        );

        assertEquals(1, result.size());
        assertEquals("Author 1", result.get(0).getAuthor());
        assertTrue(result.get(0).getSpeechText().contains(""));
        assertTrue(result.get(0).getKeywords().contains("keyword1"));

    }

    @Test
    public void testSearchSpeechesSome() {
        List<Speech> s = Arrays.asList(
            new Speech(1L, "Author 1", "text and word", List.of("keyword1", "keyword2"), LocalDate.of(2023, 1, 15)),
            new Speech(2L, "Author 2", "text 2", List.of("keyword3"), LocalDate.of(2023, 2, 15))
        );

        when(speechRepository.findAll()).thenReturn(s);

        List<SpeechDTO> result = speechService.searchSpeeches(
            "Author 1",
            null,
            null,
            null,
            "keyword1"
        );

        assertEquals(1, result.size());
        assertEquals("Author 1", result.get(0).getAuthor());
        assertTrue(result.get(0).getKeywords().contains("keyword1"));
    }

    @Test
    public void testSearchSpeechesOne() {
        List<Speech> s = Arrays.asList(
            new Speech(1L, "Author 1", "text and word", List.of("keyword1", "keyword2"), LocalDate.of(2023, 1, 15)),
            new Speech(2L, "Author 2", "text 2", List.of("keyword3"), LocalDate.of(2023, 2, 15))
        );

        when(speechRepository.findAll()).thenReturn(s);

        List<SpeechDTO> result = speechService.searchSpeeches(
            null,
            null,
            null,
            "text",
            null
        );

        assertEquals(2, result.size());
    }



    
}
