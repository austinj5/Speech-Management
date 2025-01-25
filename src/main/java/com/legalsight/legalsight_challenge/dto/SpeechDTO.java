package com.legalsight.legalsight_challenge.dto;


import java.time.LocalDate;
import java.util.List;

public class SpeechDTO {

    private Long id;
    private String author;
    private String speechText;
    private List<String> keywords;
    private LocalDate speechDate;

    public SpeechDTO () {

    }

    public SpeechDTO(Long id, String author, String speechText, List<String> keywords, LocalDate speechDate) {
        this.id = id;
        this.author = author;
        this.speechText = speechText;
        this.keywords = keywords;
        this.speechDate = speechDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSpeechText() {
        return speechText;
    }

    public void setSpeechText(String speechText) {
        this.speechText = speechText;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public LocalDate getSpeechDate() {
        return speechDate;
    }

    public void setSpeechDate(LocalDate speechDate) {
        this.speechDate = speechDate;
    }
}