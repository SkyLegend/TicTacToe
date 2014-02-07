package com.sky.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private TextView playerTwoScore;
    //Top Row
    private Button topLeft;
    private Button topCenter;
    private Button topRight;

    //Center Row
    private Button centerLeft;
    private Button center;
    private Button centerRight;

    //Bottom Row
    private Button bottomLeft;
    private Button bottomCenter;
    private Button bottomRight;

    private ImageView playerOneMarker;
    private ImageView playerTwoMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //Top Row
        topLeft = (Button) findViewById(R.id.btnTopLeft);
        topCenter = (Button) findViewById(R.id.btnTopCenter);
        topRight = (Button) findViewById(R.id.btnTopRight);

        //Center Row
        centerLeft = (Button) findViewById(R.id.btnCenterLeft);
        center = (Button) findViewById(R.id.btnCenter);
        centerRight = (Button) findViewById(R.id.btnCenterRight);

        //Bottom Row
        bottomLeft = (Button) findViewById(R.id.btnBottomLeft);
        bottomCenter = (Button) findViewById(R.id.btnBottomCenter);
        bottomRight = (Button) findViewById(R.id.btnBottomRight);

        playerOneMarker = (ImageView) findViewById(R.id.playerOneTurn);
        playerTwoMarker = (ImageView) findViewById(R.id.playerTwoTurn);

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
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerTwoScore.setText(Integer.toString(mPlayerTwoScore));

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
                if(!(determineVictory(partitionNumber))){
                    playerOneTurnMarker = false;
                    playerOneMarker.setImageResource(R.drawable.neutral_turn);
                    playerTwoTurnMarker = true;
                    playerTwoMarker.setImageResource(R.drawable.blue_turn);
                }
            } else{
                gameGrid[row][column] = false;
                tempButton.setTextColor(Color.BLUE);
                tempButton.setText("O");
                if(!(determineVictory(partitionNumber))){
                    playerTwoTurnMarker = false;
                    playerTwoMarker.setImageResource(R.drawable.neutral_turn);
                    playerOneTurnMarker = true;
                    playerOneMarker.setImageResource(R.drawable.red_turn);
                }
            }
        }
        if(drawGame()){
            gameReset();
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
    private boolean determineVictory(int patternNum){
        ArrayList<int[]> tempPatterns;
        tempPatterns = gridPatterns.get(patternNum);
        //Check Patterns
        for(int i = 0; i < tempPatterns.size(); i++){
            int[] temp = tempPatterns.get(i);
            if((gameGrid[temp[0]][temp[1]] != null && gameGrid[temp[0]][temp[1]])
                    && (gameGrid[temp[2]][temp[3]] != null && gameGrid[temp[2]][temp[3]])
                    && (gameGrid[temp[4]][temp[5]] != null && gameGrid[temp[4]][temp[5]])){
                playerOneScore.setText(Integer.toString(++mPlayerOneScore));
                choiceDialog();
                return true;
            } else if((gameGrid[temp[0]][temp[1]] != null && !(gameGrid[temp[0]][temp[1]]))
                    && (gameGrid[temp[2]][temp[3]] != null && !(gameGrid[temp[2]][temp[3]]))
                    && (gameGrid[temp[4]][temp[5]] != null && !(gameGrid[temp[4]][temp[5]]))){
                playerTwoScore.setText(Integer.toString(++mPlayerTwoScore));
                choiceDialog();
                return true;
            }
        }
        return false;


        //Check Current Player
    }

    private void gameReset(){
        topLeft.setText("");
        topCenter.setText("");
        topRight.setText("");
        centerLeft.setText("");
        center.setText("");
        centerRight.setText("");
        bottomLeft.setText("");
        bottomCenter.setText("");
        bottomRight.setText("");

        gameGrid = new Boolean[3][3];

    }

    private void initializeVictoryPatterns(){
        gridPatterns = new HashMap<Integer, ArrayList<int[]>>();

        ArrayList<int[]> zeroRow = new ArrayList<int[]>();
        ArrayList<int[]> oneRow = new ArrayList<int[]>();
        ArrayList<int[]> twoRow = new ArrayList<int[]>();
        ArrayList<int[]> threeRow = new ArrayList<int[]>();
        ArrayList<int[]> fourRow = new ArrayList<int[]>();
        ArrayList<int[]> fiveRow = new ArrayList<int[]>();
        ArrayList<int[]> sixRow = new ArrayList<int[]>();
        ArrayList<int[]> sevenRow = new ArrayList<int[]>();
        ArrayList<int[]> eightRow = new ArrayList<int[]>();

        int[] zeroPattern = {0, 0, 0, 1, 0, 2};
        int[] zeroPattern2 = {0, 0, 1, 0, 2, 0};
        int[] zeroPattern3 = {0, 0, 1, 1, 2, 2};
        int[] onePattern = {0, 1, 1, 1, 2, 1};
        int[] onePattern2 = {0, 1, 0, 0, 0, 2};
        int[] twoPattern = {0, 2, 1, 2, 2, 2};
        int[] twoPattern2 = {0, 2, 1, 1, 2, 0};
        int[] twoPattern3 = {0, 2, 0, 1, 0, 0};
        int[] threePattern = {1, 0, 1, 1, 1, 2};
        int[] threePattern2 = {1, 0, 0, 0, 2, 0};
        int[] fourPattern ={1, 1, 1, 0, 1, 2};
        int[] fourPattern2 ={1, 1, 0, 1, 2, 1};
        int[] fourPattern3 ={1, 1, 0, 2, 2, 0};
        int[] fourPattern4 ={1, 1, 0, 0, 2, 2};
        int[] fivePattern ={1, 2, 1, 1, 1, 0};
        int[] fivePattern2 ={1, 2, 0, 2, 2, 2};
        int[] sixPattern = {2, 0, 2, 1, 2, 2};
        int[] sixPattern2 = {2, 0, 1, 0, 0, 0};
        int[] sixPattern3 = {2, 0, 1, 1, 0, 2};
        int[] sevenPattern = {2, 1, 2, 0, 2, 2};
        int[] sevenPattern2 = {2, 1, 1, 1, 0, 1};
        int[] eightPattern = {2, 2, 2, 1, 2, 0};
        int[] eightPattern2 = {2, 2, 1, 2, 0, 2};
        int[] eightPattern3 = {2, 2, 1, 1, 0, 0};

        zeroRow.add(zeroPattern);
        zeroRow.add(zeroPattern2);
        zeroRow.add(zeroPattern3);
        oneRow.add(onePattern);
        oneRow.add(onePattern2);
        twoRow.add(twoPattern);
        twoRow.add(twoPattern2);
        twoRow.add(twoPattern3);
        threeRow.add(threePattern);
        threeRow.add(threePattern2);
        fourRow.add(fourPattern);
        fourRow.add(fourPattern2);
        fourRow.add(fourPattern3);
        fourRow.add(fourPattern4);
        fiveRow.add(fivePattern);
        fiveRow.add(fivePattern2);
        sixRow.add(sixPattern);
        sixRow.add(sixPattern2);
        sixRow.add(sixPattern3);
        sevenRow.add(sevenPattern);
        sevenRow.add(sevenPattern2);
        eightRow.add(eightPattern);
        eightRow.add(eightPattern2);
        eightRow.add(eightPattern3);

        gridPatterns.put(0, zeroRow);
        gridPatterns.put(1, oneRow);
        gridPatterns.put(2, twoRow);
        gridPatterns.put(3, threeRow);
        gridPatterns.put(4, fourRow);
        gridPatterns.put(5, fiveRow);
        gridPatterns.put(6, sixRow);
        gridPatterns.put(7, sevenRow);
        gridPatterns.put(8, eightRow);
    }

    private boolean drawGame(){
        for(int i = 0; i < gameGrid.length; i++){
            for(int j = 0; j < gameGrid[i].length; j++){
                if(gameGrid[i][j] == null){
                    return false;
                }
            }
        }
        return true;
    }

    private void choiceDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(R.string.choice_title);

        // set dialog message
        alertDialogBuilder
            .setMessage(R.string.choice_message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    gameReset();
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    finalScoreDialog();
                }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void finalScoreDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(R.string.final_score_title);

        // set dialog message
        alertDialogBuilder
            .setMessage(getString(R.string.final_score_message, mPlayerOneScore, mPlayerTwoScore))
            .setCancelable(false)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    finish();
                }
            });
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
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
