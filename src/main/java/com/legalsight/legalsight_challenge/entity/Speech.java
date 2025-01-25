package com.legalsight.legalsight_challenge.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "speeches")
public class Speech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "author")
    private String author;

    @Column(name = "speech_text")
    private String speechText;

    @Column(name = "keywords")
    private List<String> keywords;

    @Column(name = "speech_date")
    private LocalDate speechDate;

    public Speech() {

    }

    public Speech(Long id, String author, String speechText, List<String> keywords, LocalDate speechDate) {
        this.id = id;
        this.author = author;
        this.speechText = speechText;
        this.keywords = keywords;
        this.speechDate = speechDate;
    }

    public Speech(String author, String speechText, List<String> keywords, LocalDate speechDate) {
        this.author = author;
        this.speechText = speechText;
        this.keywords = keywords;
        this.speechDate = speechDate;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getSpeechText() {
        return speechText;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public LocalDate getSpeechDate() {
        return speechDate;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSpeechText(String speechText) {
        this.speechText = speechText;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void setSpeechDate(LocalDate speechDate) {
        this.speechDate = speechDate;
    }

    @Override
    public String toString() {
        return "Speech{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", text='" + speechText + '\'' +
                ", keywords=" + keywords +
                ", date=" + speechDate +
                '}';
    }
    
    
}
