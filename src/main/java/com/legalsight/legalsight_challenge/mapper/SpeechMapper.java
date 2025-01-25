package com.legalsight.legalsight_challenge.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.legalsight.legalsight_challenge.dto.SpeechDTO;
import com.legalsight.legalsight_challenge.entity.Speech;

@Mapper(componentModel = "spring")
public interface SpeechMapper {
    SpeechMapper INSTANCE = Mappers.getMapper(SpeechMapper.class);

    // Convert Speech to SpeechDTO
    SpeechDTO toDTO(Speech speech);

    // Convert SpeechDTO to Speech
    Speech toEntity(SpeechDTO speechDTO);


    // converters centralized in mapper class for simplicity
    @Named("stringToList")
    default List<String> stringToList(String keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.asList(keywords.split(","));
    }

    @Named("listToString")
    default String listToString(List<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return null;
        }

        return String.join(",", keywords);
    }
    
}
