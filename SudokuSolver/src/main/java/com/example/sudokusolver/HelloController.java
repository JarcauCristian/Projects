package com.example.sudokusolver;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HelloController {

    @FXML
    private GridPane fPane;

    @FXML
    private GridPane sPane;

    @FXML
    private GridPane tPane;

    @FXML
    private Button genBoard;

    @FXML
    private Button solveBoard;

    private Label[][] fL;
    private Label[][] sL;
    private Label[][] tL;
    private Label[][] finalL;

    @FXML
    protected void generateBoard()
    {

        fL = new Label[3][9];
        sL = new Label[3][9];
        tL = new Label[3][9];
        ObservableList<Node> fList = fPane.getChildren();
        ObservableList<Node> sList = sPane.getChildren();
        ObservableList<Node> tList = tPane.getChildren();


        try(Scanner cin = new Scanner(new File("Board.txt")))
        {
            int counter = 1;
            String line = new String();
            while (cin.hasNextLine())
            {
                line = cin.nextLine();
                if(counter >= 1 && counter <= 3)
                {
                    int cnt = 0;
                    String[] s = line.split(",");
                    if(counter == 1)
                    {
                        for (int i = 0; i < 9; i++)
                        {
                            Label lbl = (Label) fList.get(i);
                            lbl.setText(s[cnt++]);
                        }
                        cnt = 0;
                    }
                    if(counter == 2)
                    {
                        for (int i = 9; i < 18; i++)
                        {
                            Label lbl = (Label) fList.get(i);
                            lbl.setText(s[cnt++]);
                        }
                        cnt = 0;
                    }
                    if(counter == 3)
                    {
                        for (int i = 18; i < 27; i++)
                        {
                            Label lbl = (Label) fList.get(i);
                            lbl.setText(s[cnt++]);
                        }
                        cnt = 0;
                    }
                }
                if(counter >= 4 && counter <= 6)
                {
                    int cnt = 0;
                    String[] s = line.split(",");
                    if(counter == 4)
                    {
                        for (int i = 0; i < 9; i++)
                        {
                            Label lbl = (Label) sList.get(i);
                            lbl.setText(s[cnt++]);
                        }
                        cnt = 0;
                    }
                    if(counter == 5)
                    {
                        for (int i = 9; i < 18; i++)
                        {
                            Label lbl = (Label) sList.get(i);
                            lbl.setText(s[cnt++]);
                        }
                        cnt = 0;
                    }
                    if(counter == 6)
                    {
                        for (int i = 18; i < 27; i++)
                        {
                            Label lbl = (Label) sList.get(i);
                            lbl.setText(s[cnt++]);
                        }
                        cnt = 0;
                    }
                }
                if(counter >= 7 && counter <= 9)
                {
                    int cnt = 0;
                    String[] s = line.split(",");
                    if(counter == 7)
                    {
                        for (int i = 0; i < 9; i++)
                        {
                            Label lbl = (Label) tList.get(i);
                            lbl.setText(s[cnt++]);
                        }
                        cnt = 0;
                    }
                    if(counter == 8)
                    {
                        for (int i = 9; i < 18; i++)
                        {
                            Label lbl = (Label) tList.get(i);
                            lbl.setText(s[cnt++]);
                        }
                        cnt = 0;
                    }
                    if(counter == 9)
                    {
                        for (int i = 18; i < 27; i++)
                        {
                            Label lbl = (Label) tList.get(i);
                            lbl.setText(s[cnt++]);
                        }
                        cnt = 0;
                    }
                }
                counter++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        int cnt = 0;
        for(int i = 0; i < 9; i++)
        {
            fL[0][cnt++] = (Label) fList.get(i);
        }

        cnt = 0;
        for(int i = 9; i < 18; i++)
        {
            fL[1][cnt++] = (Label) fList.get(i);
        }
        cnt = 0;

        for(int i = 18; i < 27; i++)
        {
            fL[2][cnt++] = (Label) fList.get(i);
        }
        cnt = 0;
        for(int i = 0; i < 9; i++)
        {
            sL[0][cnt++] = (Label) sList.get(i);
        }
        cnt = 0;
        for(int i = 9; i < 18; i++)
        {
            sL[1][cnt++] = (Label) sList.get(i);
        }
        cnt = 0;
        for(int i = 18; i < 27; i++)
        {
            sL[2][cnt++] = (Label) sList.get(i);
        }
        cnt = 0;
        for(int i = 0; i < 9; i++)
        {
            tL[0][cnt++] = (Label) tList.get(i);
        }
        cnt = 0;
        for(int i = 9; i < 18; i++)
        {
            tL[1][cnt++] = (Label) tList.get(i);
        }
        cnt = 0;
        for(int i = 18; i < 27; i++)
        {
            tL[2][cnt++] = (Label) tList.get(i);
        }

        finalL = new Label[9][9];

        cnt = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                finalL[i][j] = fL[cnt][j];
            }
            cnt++;
        }

        cnt = 0;
        for (int i = 3; i < 6; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                finalL[i][j] = sL[cnt][j];
            }
            cnt++;
        }
        cnt = 0;
        for (int i = 6; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                finalL[i][j] = tL[cnt][j];
            }
            cnt++;
        }
    }

    @FXML
    protected void result()
    {
        solveSudoku(finalL);
    }

    private boolean solveSudoku(Label[][] lbl) {
        lbl = finalL;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (lbl[row][column].getText().equals("0")) {
                    for (int numberToTry = 1; numberToTry <= 9; numberToTry++) {
                        if (isValidPlacement(String.valueOf(numberToTry), row, column)) {
                            finalL[row][column].setText(String.valueOf(numberToTry));
                            finalL[row][column].textAlignmentProperty();

                            if (solveSudoku(lbl)) {
                                return true;
                            }
                            else {
                                finalL[row][column].setText("0");
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNumberInRow(String number, int row) {
        for (int i = 0; i < 9; i++) {
            if (finalL[row][i].getText().equals(number)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumn(String number, int column) {
        for (int i = 0; i < 9; i++) {
            if (finalL[i][column].getText().equals(number)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInBox(String number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (finalL[i][j].getText().equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPlacement(String number, int row, int column) {
        return !isNumberInRow(number, row) &&
                !isNumberInColumn(number, column) &&
                !isNumberInBox(number, row, column);
    }

}