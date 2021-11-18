package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements BoardView {

    BoardPresenter presenter;
    TableLayout board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = findViewById(R.id.board);

        presenter = new BoardPresenter(this);

        for (byte row = 0; row<3;row++){
            TableRow tableRow = (TableRow) board.getChildAt(row);

            for(byte col = 0 ; col < 3 ; col++){
                Button btn = (Button)tableRow.getChildAt(col);
                btn.setOnClickListener(new BoardPresenter.CellClickListener(presenter,row,col));
            }
        }

    }

    @Override
    public void newGame() {

        for (byte row = 0; row<3;row++){
            TableRow tableRow = (TableRow) board.getChildAt(row);

            for(byte col = 0 ; col < 3 ; col++){
                Button btn = (Button) board.getChildAt(col);
                btn.setText("");
                btn.setEnabled(true);
            }
        }

    }

    @Override
    public void putSymbol(char symbol, byte row, byte col) {
        TableRow tableRow = (TableRow) board.getChildAt(row);
        Button btn = (Button)tableRow.getChildAt(col);
        btn.setText(Character.toString(symbol));
    }

    @Override
    public void gameEnded(byte winner) {

        for (byte row = 0; row<3;row++){
            TableRow tableRow = (TableRow) board.getChildAt(row);

            for(byte col = 0 ; col < 3 ; col++){
                Button btn = (Button) board.getChildAt(col);
                btn.setEnabled(false);
            }
        }
        String msg = null;
        if(winner  != 0)
            msg = "Player " + winner + " won the game";
        else
            msg = "It is draw";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();

    }

    @Override
    public void invalidPlay(byte row, byte col) {
        Toast.makeText(this,"Invalid move!",Toast.LENGTH_LONG).show();
    }
}