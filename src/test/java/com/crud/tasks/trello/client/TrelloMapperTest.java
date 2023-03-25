package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloListDto> list = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "jeden", list);
        List trelloBoardDtoList = new ArrayList<>();

        //When
        trelloBoardDtoList.add(trelloBoardDto);
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals("1", trelloBoard.get(0).getId());
        assertEquals("jeden", trelloBoard.get(0).getName());
        assertEquals(list, trelloBoard.get(0).getLists());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("2", "dwa", list);
        List trelloBoardList = new ArrayList<>();

        //When
        trelloBoardList.add(trelloBoard);
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals("2", trelloBoardDto.get(0).getId());
        assertEquals("dwa", trelloBoardDto.get(0).getName());
        assertEquals(list, trelloBoardDto.get(0).getLists());
    }

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("3", "trzy", true);
        List trelloListDtoList = new ArrayList<>();

        //When
        trelloListDtoList.add(trelloListDto);
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals("3", trelloList.get(0).getId());
        assertEquals("trzy", trelloList.get(0).getName());
        assertEquals(true, trelloList.get(0).isClosed());
    }


    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("4", "cztery", false);
        List trelloListList = new ArrayList<>();

        //When
        trelloListList.add(trelloList);
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloListList);

        //Then
        assertEquals("4", trelloListDto.get(0).getId());
        assertEquals("cztery", trelloListDto.get(0).getName());
        assertEquals(false, trelloListDto.get(0).isClosed());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("5","test5", "5","5");

        //When
TrelloCardDto trelloCardDto=        trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("5", trelloCardDto.getName());
        assertEquals("test5", trelloCardDto.getDescription());
        assertEquals("5", trelloCardDto.getPos());
        assertEquals("5", trelloCardDto.getListId());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("6","test6", "6","6");

        //When
        TrelloCard trelloCard=        trelloMapper. mapToCard(trelloCardDto);

        //Then
        assertEquals("6", trelloCard.getName());
        assertEquals("test6", trelloCard.getDescription());
        assertEquals("6", trelloCard.getPos());
        assertEquals("6", trelloCard.getListId());
    }
}
