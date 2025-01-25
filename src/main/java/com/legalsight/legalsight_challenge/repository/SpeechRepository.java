package com.legalsight.legalsight_challenge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.legalsight.legalsight_challenge.entity.Speech;

@Repository
public interface SpeechRepository extends JpaRepository<Speech, Long>{

    // custom queries to search for specific fields
    List<Speech> findByAuthor(String author);
    List<Speech> findBySpeechDateBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT s FROM Speech s WHERE s.speechText LIKE %:snippet%")
    List<Speech> searchByTextSnippet(@Param("snippet") String snippet);
    
}
