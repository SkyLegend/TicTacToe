package com.sky.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class GameScreenActivity extends Activity {
    private int mPlayerOneScore = 0;
    private int mPlayerTwoScore = 0;
    private boolean playerOneTurnMarker = true;
    private boolean playerTwoTurnMarker = false;
    private Boolean[][] gameGrid;
    private HashMap<Integer, ArrayList<int[]>> gridPatterns;
    private TextView playerOneScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //Top Row
        final Button topLeft = (Button) findViewById(R.id.btnTopLeft);
        final Button topCenter = (Button) findViewById(R.id.btnTopCenter);
        final Button topRight = (Button) findViewById(R.id.btnTopRight);

        //Center Row
        final Button centerLeft = (Button) findViewById(R.id.btnCenterLeft);
        final Button center = (Button) findViewById(R.id.btnCenter);
        final Button centerRight = (Button) findViewById(R.id.btnCenterRight);

        //Bottom Row
        final Button bottomLeft = (Button) findViewById(R.id.btnBottomLeft);
        final Button bottomCenter = (Button) findViewById(R.id.btnBottomCenter);
        final Button bottomRight = (Button) findViewById(R.id.btnBottomRight);

        topLeft.setOnClickListener(gameClicks);
        topCenter.setOnClickListener(gameClicks);
        topRight.setOnClickListener(gameClicks);
        centerLeft.setOnClickListener(gameClicks);
        center.setOnClickListener(gameClicks);
        centerRight.setOnClickListener(gameClicks);
        bottomLeft.setOnClickListener(gameClicks);
        bottomCenter.setOnClickListener(gameClicks);
        bottomRight.setOnClickListener(gameClicks);

        initializeVictoryPatterns();

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerOneScore.setText(Integer.toString(mPlayerOneScore));

        gameGrid = new Boolean[3][3];
    }

    private View.OnClickListener gameClicks = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnTopLeft:
                    gridMarker(v, 0, 0, 0);
                    break;
                case R.id.btnTopCenter:
                    gridMarker(v, 0, 1, 1);
                    break;
                case R.id.btnTopRight:
                    gridMarker(v, 0, 2, 2);
                    break;
                case R.id.btnCenterLeft:
                    gridMarker(v, 1, 0, 3);
                    break;
                case R.id.btnCenter:
                    gridMarker(v, 1, 1, 4);
                    break;
                case R.id.btnCenterRight:
                    gridMarker(v, 1, 2, 5);
                    break;
                case R.id.btnBottomLeft:
                    gridMarker(v, 2, 0, 6);
                    break;
                case R.id.btnBottomCenter:
                    gridMarker(v, 2, 1, 7);
                    break;
                case R.id.btnBottomRight:
                    gridMarker(v, 2, 2, 8);
                    break;
            }
        }
    };

    private void gridMarker(View v, int row, int column, int partitionNumber){
        final Button tempButton = (Button)findViewById(v.getId());
        if(gameGrid[row][column] == null){
            if(playerOneTurnMarker){
                gameGrid[row][column] = true;
                tempButton.setTextColor(Color.RED);
                tempButton.setText("X");
                playerOneTurnMarker = false;
                playerTwoTurnMarker = true;
                determineVictory(row, partitionNumber);
            } else{
                gameGrid[row][column] = false;
                tempButton.setTextColor(Color.BLUE);
                tempButton.setText("O");
                playerTwoTurnMarker = false;
                playerOneTurnMarker = true;
                determineVictory(row, partitionNumber);
            }
        }
    }

    /**
     * determines wither or not a Victory criteria has been achieved.
     * Victory Combo (Does Not Check for Reverse Sequences of existing combos):
     * 1 {1,2,3}, {1,4,7}, {1,5,9}
     * 2 {2,5,8}
     * 3 {3,4,7}, {3,6,9}
     * 4 {4,5,6}
     * 5 null
     * 6 null
     * 7 {7,8,9}
     * 8 null
     * 9 null
     */
    private void determineVictory(int row, int patternNum){
        ArrayList<int[]> tempPatterns;
        tempPatterns = gridPatterns.get(patternNum);
        //Check Patterns
        if(tempPatterns != null){
            for(int i = 0; i < tempPatterns.size() - 1; i++){
                int[] temp = tempPatterns.get(i);
                if((gameGrid[row][temp[0]] != null && gameGrid[row][temp[0]])
                        && (gameGrid[row][temp[1]] != null && gameGrid[row][temp[1]])
                        && (gameGrid[row][temp[2]] != null && gameGrid[row][temp[2]])){
                    if(playerOneTurnMarker){
                        playerOneScore.setText(++mPlayerOneScore);
                    } else{
                        mPlayerTwoScore++;
                    }
                }
            }
        }

        //Check Current Player
    }

    private void initializeVictoryPatterns(){
        gridPatterns = new HashMap<Integer, ArrayList<int[]>>();

        ArrayList<int[]> zeroRow = new ArrayList<int[]>();
        ArrayList<int[]> oneRow = new ArrayList<int[]>();
        ArrayList<int[]> twoRow = new ArrayList<int[]>();
        ArrayList<int[]> threeRow = new ArrayList<int[]>();
        ArrayList<int[]> sixRow = new ArrayList<int[]>();

        int[] zeroPattern = {0, 1, 2};
        int[] zeroPattern2 = {0, 3, 6};
        int[] zeroPattern3 = {0, 4, 6};
        int[] onePattern = {1, 4, 7};
        int[] twoPattern = {2, 5, 8};
        int[] twoPattern2 = {2, 4, 6};
        int[] threePattern = {3, 4, 5};
        int[] sixPattern = {6, 7, 8};

        zeroRow.add(zeroPattern);
        zeroRow.add(zeroPattern2);
        zeroRow.add(zeroPattern3);
        oneRow.add(onePattern);
        twoRow.add(twoPattern);
        twoRow.add(twoPattern2);
        threeRow.add(threePattern);
        sixRow.add(sixPattern);

        gridPatterns.put(0, zeroRow);
        gridPatterns.put(1, oneRow);
        gridPatterns.put(2, twoRow);
        gridPatterns.put(3, threeRow);
        gridPatterns.put(4, null);
        gridPatterns.put(5, null);
        gridPatterns.put(6, sixRow);
        gridPatterns.put(7, null);
        gridPatterns.put(8, null);
    }

    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_screen, menu);
        return true;
    }
    */
}
