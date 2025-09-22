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

    List<ColumEntity> columnsList = new ArrayList<>();
    List<CardEntity> cardsList = new ArrayList<>();

    public void execute(BoardEntity board){

        boolean runnig = true;

        columnsList = boardService.getAllColumnsByBoardId(board.getId());

        //todo: Fazer com que após o usuário realizar alguma ação, mostrar novamente as informações abaixo
        System.out.println("*****************************************************");
        System.out.println("                    Board de Tarefas");
        System.out.println("*****************************************************");
        System.out.println(">> Nome: " + board.getName());
        columnsList.forEach(column -> {
            AtomicInteger atomicInteger = new AtomicInteger();
            cardsList = boardService.getAllCardByColumnId(column.getId());

            System.out.println("-----------------------------------------------------\n" +
                    " COLUNA: " + column.getName() + "\n" +
                    "-----------------------------------------------------");
            cardsList.forEach(card -> {
                System.out.println("   >> Card #" + atomicInteger.getAndIncrement()+1 +" | " + card.getTitle() + "\n" +
                        "      " + card.getDescription() +"\n");
            });
        });

        System.out.println("*****************************************************\n" +
                "              Ações Disponíveis\n" +
                "*****************************************************");

        while(runnig){
            System.out.println("BOARD: " + board.getName());
            System.out.println("1 - Criar um Card");
            System.out.println("2 - Mover Card");
            System.out.println("3 - Cancelar um Card");
            System.out.println("4 - Bloquear um Card");
            System.out.println("5 - Desbloquear um Card");
            System.out.println("6 - Sair");

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
                    System.out.println("Cancelando um card");
                    cancelCard();
                    break;
                case 4:
                    System.out.println("Bloqueando um card");
                    blockCard();
                    break;
                case 5:
                    System.out.println("Desbloqueando um card");
                    unblockCard();
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    runnig = false;
                    break;
            }
        }
    }

    public void createCard(Long boardId){
        System.out.println("Digite o título do card:");
        String cardTitle = sc.nextLine();

        System.out.println("Digite uma descrição para o card");
        String cardDescription = sc.nextLine();

        boardService.createCard(boardId, cardTitle, cardDescription);
    }

    private void moveCard() {}

    private void cancelCard(){}

    private void blockCard(){}

    private void unblockCard(){}

    //private List<CardEntity> loadCards(Long id){}
}
