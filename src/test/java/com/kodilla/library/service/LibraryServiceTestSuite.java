package com.kodilla.library.service;

import com.kodilla.library.domain.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LibraryServiceTestSuite {
    @Autowired
    private LibraryService libraryService;

    @Test
    void testSaveTitle(){
        //Given
        Title title = new Title(null, "Test Title", "Test Author", 2024);

        //When
        Title savedTitle = libraryService.saveTitle(title);

        //Then
        assertNotNull(savedTitle.getId());
        assertEquals("Test Title", savedTitle.getTitle());


    }
}
