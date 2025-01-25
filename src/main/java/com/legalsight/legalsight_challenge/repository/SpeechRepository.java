package com.legalsight.legalsight_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.legalsight.legalsight_challenge.entity.Speech;

@Repository
public interface SpeechRepository extends JpaRepository<Speech, Long>{
    
}
