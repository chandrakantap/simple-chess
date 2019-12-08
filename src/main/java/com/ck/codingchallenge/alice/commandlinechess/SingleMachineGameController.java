package com.ck.codingchallenge.alice.commandlinechess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class SingleMachineGameController implements ApplicationRunner {
    private Board board;
    private UIInterface uiInterface;
    private BufferedReader reader;

    @Autowired
    public SingleMachineGameController(Board board,UIInterface uiInterface) {
        this.board = board;
        this.uiInterface=uiInterface;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        board.startNewGame();

        uiInterface.drawBoard();

        while (true){
            String nextInput = getNextInput();
            if(nextInput.equalsIgnoreCase("q")){
                reader.close();
                break;
            }
            if(nextInput.equalsIgnoreCase("reset")){
                board.startNewGame();
                uiInterface.drawBoard();
                continue;
            }
            if(nextInput.equalsIgnoreCase("h")){
                uiInterface.drawBoard();
                showHelpText();
                continue;
            }
            String[] cells = nextInput.toUpperCase().split("-");

            if(cells.length!=2){
                showResult(MoveResult.invalidMove("Please provide from and to cell."));
                continue;
            }

            BoardCell fromCell = null;
            BoardCell toCell =null;

            try {
                fromCell = BoardCell.valueOf(cells[0].trim());
            }
            catch (IllegalArgumentException ex){
                showResult(MoveResult.invalidMove(cells[0].trim()+" is not a valid cell."));
                continue;
            }

            try{
                toCell = BoardCell.valueOf(cells[1].trim());
            }catch (IllegalArgumentException ex){
                showResult(MoveResult.invalidMove("Invalid cell provided."));
                continue;
            }

            MoveResult result = board.move(fromCell,toCell);
            uiInterface.drawBoard();
            showResult(result);

        }
    }

    private void showHelpText() {
        System.out.println("Provide move as fromCell-toCell. e.g. d2-d4, g1-h3");
        System.out.println("Type 'reset' to reset the game.");
        System.out.println("Press 'q' to quit the game.");
        System.out.println("");
    }

    private String getNextInput() throws IOException {
        if(this.reader==null){
            this.reader = new BufferedReader(new InputStreamReader(System.in));
        }
        System.out.print(board.getCurrentPlayer().name()+" player's move:> ");
        return this.reader.readLine().trim();
    }

    private void showResult(MoveResult result){
        switch (result.getStatus()){
            case INVALID:
                System.out.println("Error: "+result.getErrorMessage());
                break;
        }
    }
}
