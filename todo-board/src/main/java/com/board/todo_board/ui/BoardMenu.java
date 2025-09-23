package com.board.todo_board.ui;

import com.board.todo_board.entities.BoardEntity;
import com.board.todo_board.entities.CardEntity;
import com.board.todo_board.entities.ColumEntity;
import com.board.todo_board.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BoardMenu {

    @Autowired
    BoardService boardService;

    Scanner sc = new Scanner(System.in);

    public void execute(BoardEntity board){

        AtomicInteger optionsIndex = new AtomicInteger(1);
        boolean runnig = true;

        displayBoard(board);

        System.out.println("""
                *****************************************************
                              Ações Disponíveis
                *****************************************************""");
        while(runnig){
            System.out.println("BOARD: " + board.getName());
            System.out.println(optionsIndex.getAndIncrement() + " - Criar um Card");
            System.out.println(optionsIndex.getAndIncrement() + " - Mover Card");
            System.out.println(optionsIndex.getAndIncrement() + " - Editar Card");
            System.out.println(optionsIndex.getAndIncrement() + " - Cancelar um Card");
            System.out.println(optionsIndex.getAndIncrement() + " - Bloquear um Card");
            System.out.println(optionsIndex.getAndIncrement() + " - Desbloquear um Card");
            System.out.println(optionsIndex.getAndIncrement() + " - Sair");

            int response = Integer.parseInt(sc.nextLine());

            switch (response){
                case 1:
                    System.out.println("Criando novo card");
                    createCard(board.getId());
                    break;
                case 2:
                    System.out.println("Movendo um novo card");
                    moveCard();
                    break;
                case 3:
                    System.out.println("Editar um card");
                    editCard(board);
                    break;
                case 4:
                    System.out.println("Cancelando um card");
                    cancelCard();
                    break;
                case 5:
                    System.out.println("Bloqueando um card");
                    blockCard();
                    break;
                case 6:
                    System.out.println("Desbloqueando um card");
                    unblockCard();
                    break;
                case 7:
                    System.out.println("Saindo do sistema...");
                    runnig = false;
                    break;
            }
        }
    }

    public void displayBoard(BoardEntity board){
        List<ColumEntity> columnsList = boardService.getAllColumnsByBoardId(board.getId());
        AtomicInteger cardIndex = new AtomicInteger(1);

        //todo: Fazer com que após o usuário realizar alguma ação, mostrar novamente as informações abaixo
        System.out.println("*****************************************************");
        System.out.println("                    Board de Tarefas");
        System.out.println("*****************************************************");
        System.out.println(">> Nome: " + board.getName());
        columnsList.forEach(column -> {
            List<CardEntity> cardsList = boardService.getAllCardByColumnId(column.getId());

            System.out.println("-----------------------------------------------------\n" +
                    " COLUNA: " + column.getName() + "\n" +
                    "-----------------------------------------------------");
            cardsList.forEach(card -> {
                System.out.println("   >> Card #" + cardIndex.getAndIncrement() + " | " + card.getTitle() + "\n" +
                        "      " + card.getDescription() +"\n");
            });
        });
    }

    private void createCard(Long boardId){
        System.out.println("Digite o título do card:");
        String cardTitle = sc.nextLine();

        System.out.println("Digite uma descrição para o card");
        String cardDescription = sc.nextLine();

        boardService.createCard(boardId, cardTitle, cardDescription);
    }

    private void moveCard() {}

    private void editCard(BoardEntity board){
        List<CardEntity> cardsList = new ArrayList<>();
        List<ColumEntity> columnList = boardService.getAllColumnsByBoardId(board.getId());
        AtomicInteger cardIndex = new AtomicInteger(1);

        columnList.forEach(column -> cardsList.addAll(boardService.getAllCardByColumnId(column.getId())));

        System.out.println("*****************************************************");
        System.out.println("           Selecione um Card para Edição");
        System.out.println("*****************************************************");
        cardsList.forEach(card -> {
            System.out.println("   >> Card #" + cardIndex.getAndIncrement() + " | " + card.getTitle() + " (ID: " + card.getId() + ")");
        });
    }

    private void cancelCard(){}

    private void blockCard(){}

    private void unblockCard(){}
}
