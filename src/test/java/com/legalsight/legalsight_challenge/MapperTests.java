package com.legalsight.legalsight_challenge;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.legalsight.legalsight_challenge.mapper.SpeechMapper;

public class MapperTests {
    private SpeechMapper speechMapper;

    @BeforeEach
    public void setup() {
        speechMapper = SpeechMapper.INSTANCE;
    }

    @AfterEach
    public void teardown() {
        speechMapper = null;
    }

    // STRING-TO-LIST (STL)
    @Test
    public void testSTLNull() {
        String keywords = null;

        List<String> result = speechMapper.stringToList(keywords);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSTLEmpty() {
        String keywords = "";

        List<String> result = speechMapper.stringToList(keywords);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSTLValid() {
        String keywords = "k1,k2,k3";

        List<String> result = speechMapper.stringToList(keywords);

        assertEquals(3, result.size());
        assertEquals(Arrays.asList("k1", "k2", "k3"), result);
    }

    // LIST-TO-STRING (LTS)
    @Test
    public void testLTSNull() {
        List<String> keywords = null;

        String result = speechMapper.listToString(keywords);

        assertNull(result);
    }

    @Test
    public void testLTSEmpty() {
        List<String> keywords = List.of();

        String result = speechMapper.listToString(keywords);

        assertNull(result);
    }

    @Test
    public void testLTSValid() {
        List<String> keywords = List.of("k1", "k2", "k3");

        String result = speechMapper.listToString(keywords);

        assertNotNull(result);
        assertEquals("k1,k2,k3", result);
    }

}
