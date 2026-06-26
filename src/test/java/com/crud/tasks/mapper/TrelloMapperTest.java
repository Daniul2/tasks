package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;


    @Test
    void shouldMapToBoardsCorrectly() {
        // Given
        TrelloListDto listDto = new TrelloListDto("1", "My List", false);
        TrelloBoardDto boardDto = new TrelloBoardDto("1", "My Board", List.of(listDto));

        // When
        List<TrelloBoard> result = trelloMapper.mapToBoards(List.of(boardDto));

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("My Board", result.get(0).getName());
        assertEquals(1, result.get(0).getLists().size());
    }

    @Test
    void shouldReturnEmptyListWhenMappingEmptyBoardDtoList() {
        // When
        List<TrelloBoard> result = trelloMapper.mapToBoards(List.of());

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldMapToBoardsDtoCorrectly() {
        // Given
        TrelloList list = new TrelloList("1", "My List", false);
        TrelloBoard board = new TrelloBoard("1", "My Board", List.of(list));

        // When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(List.of(board));

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("My Board", result.get(0).getName());
        assertEquals(1, result.get(0).getLists().size());
    }


    @Test
    void shouldMapToListCorrectly() {
        // Given
        TrelloListDto listDto = new TrelloListDto("2", "Todo", true);

        // When
        List<TrelloList> result = trelloMapper.mapToList(List.of(listDto));

        // Then
        assertEquals(1, result.size());
        assertEquals("2", result.get(0).getId());
        assertEquals("Todo", result.get(0).getName());
        assertTrue(result.get(0).isClosed());
    }

    @Test
    void shouldPreserveClosedFlagWhenMappingToList() {
        // Given
        TrelloListDto open = new TrelloListDto("1", "Open List", false);
        TrelloListDto closed = new TrelloListDto("2", "Closed List", true);

        // When
        List<TrelloList> result = trelloMapper.mapToList(List.of(open, closed));

        // Then
        assertEquals(2, result.size());
        assertFalse(result.get(0).isClosed());
        assertTrue(result.get(1).isClosed());
    }


    @Test
    void shouldMapToListDtoCorrectly() {
        // Given
        TrelloList list = new TrelloList("3", "In Progress", false);

        // When
        List<TrelloListDto> result = trelloMapper.mapToListDto(List.of(list));

        // Then
        assertEquals(1, result.size());
        assertEquals("3", result.get(0).getId());
        assertEquals("In Progress", result.get(0).getName());
        assertFalse(result.get(0).isClosed());
    }


    @Test
    void shouldMapToCardCorrectly() {
        // Given
        TrelloCardDto cardDto = new TrelloCardDto("Fix bug", "Critical fix", "top", "list-99");

        // When
        TrelloCard result = trelloMapper.mapToCard(cardDto);

        // Then
        assertNotNull(result);
        assertEquals("Fix bug", result.getName());
        assertEquals("Critical fix", result.getDescription());
        assertEquals("top", result.getPos());
        assertEquals("list-99", result.getListId());
    }


    @Test
    void shouldMapToCardDtoCorrectly() {
        // Given
        TrelloCard card = new TrelloCard("New feature", "Add dark mode", "bottom", "list-42");

        // When
        TrelloCardDto result = trelloMapper.mapToCardDto(card);

        // Then
        assertNotNull(result);
        assertEquals("New feature", result.getName());
        assertEquals("Add dark mode", result.getDescription());
        assertEquals("bottom", result.getPos());
        assertEquals("list-42", result.getListId());
    }


    @Test
    void cardMappingShouldBeSymmetric() {
        // Given
        TrelloCardDto original = new TrelloCardDto("Task A", "Do something", "top", "list-1");

        // When — DTO → domain → DTO
        TrelloCard domain = trelloMapper.mapToCard(original);
        TrelloCardDto roundTrip = trelloMapper.mapToCardDto(domain);

        // Then
        assertEquals(original.getName(), roundTrip.getName());
        assertEquals(original.getDescription(), roundTrip.getDescription());
        assertEquals(original.getPos(), roundTrip.getPos());
        assertEquals(original.getListId(), roundTrip.getListId());
    }

    @Test
    void boardMappingShouldBeSymmetric() {
        // Given
        TrelloListDto listDto = new TrelloListDto("1", "Backlog", false);
        TrelloBoardDto original = new TrelloBoardDto("10", "Sprint 1", List.of(listDto));

        // When
        List<TrelloBoard> domain = trelloMapper.mapToBoards(List.of(original));
        List<TrelloBoardDto> roundTrip = trelloMapper.mapToBoardsDto(domain);

        // Then
        assertEquals(original.getId(), roundTrip.get(0).getId());
        assertEquals(original.getName(), roundTrip.get(0).getName());
        assertEquals(original.getLists().size(), roundTrip.get(0).getLists().size());
        assertEquals(original.getLists().get(0).getId(), roundTrip.get(0).getLists().get(0).getId());
    }
}
