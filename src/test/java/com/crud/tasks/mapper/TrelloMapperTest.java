package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void shouldMapToBoards() {
        // Given
        List<TrelloListDto> lists = List.of(new TrelloListDto("1", "my_list", false));
        List<TrelloBoardDto> boardDtos = List.of(new TrelloBoardDto("1", "my_board", lists));

        // When
        List<TrelloBoard> result = trelloMapper.mapToBoards(boardDtos);

        // Then
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("my_board", result.get(0).getName());
        assertEquals(1, result.get(0).getLists().size());
    }

    @Test
    void shouldMapToBoardsDto() {
        // Given
        List<TrelloList> lists = List.of(new TrelloList("1", "my_list", false));
        List<TrelloBoard> boards = List.of(new TrelloBoard("1", "my_board", lists));

        // When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(boards);

        // Then
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("my_board", result.get(0).getName());
        assertEquals(1, result.get(0).getLists().size());
    }

    @Test
    void shouldMapToList() {
        // Given
        List<TrelloListDto> listDtos = List.of(
                new TrelloListDto("1", "list_one", false),
                new TrelloListDto("2", "list_two", true)
        );

        // When
        List<TrelloList> result = trelloMapper.mapToList(listDtos);

        // Then
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("list_one", result.get(0).getName());
        assertFalse(result.get(0).isClosed());
        assertEquals("2", result.get(1).getId());
        assertTrue(result.get(1).isClosed());
    }

    @Test
    void shouldMapToListDto() {
        // Given
        List<TrelloList> lists = List.of(
                new TrelloList("1", "list_one", false),
                new TrelloList("2", "list_two", true)
        );

        // When
        List<TrelloListDto> result = trelloMapper.mapToListDto(lists);

        // Then
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("list_one", result.get(0).getName());
        assertFalse(result.get(0).isClosed());
        assertTrue(result.get(1).isClosed());
    }

    @Test
    void shouldMapToCard() {
        // Given
        TrelloCardDto cardDto = new TrelloCardDto("card", "description", "top", "1");

        // When
        TrelloCard result = trelloMapper.mapToCard(cardDto);

        // Then
        assertEquals("card", result.getName());
        assertEquals("description", result.getDescription());
        assertEquals("top", result.getPos());
        assertEquals("1", result.getListId());
    }

    @Test
    void shouldMapToCardDto() {
        // Given
        TrelloCard card = new TrelloCard("card", "description", "top", "1");

        // When
        TrelloCardDto result = trelloMapper.mapToCardDto(card);

        // Then
        assertEquals("card", result.getName());
        assertEquals("description", result.getDescription());
        assertEquals("top", result.getPos());
        assertEquals("1", result.getListId());
    }

    @Test
    void shouldReturnEmptyListWhenMappingEmptyBoards() {
        // Given
        List<TrelloBoardDto> emptyList = List.of();

        // When
        List<TrelloBoard> result = trelloMapper.mapToBoards(emptyList);

        // Then
        assertTrue(result.isEmpty());
    }
}
