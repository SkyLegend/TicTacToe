package com.sky.tictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class GameScreenActivity extends Activity {
    private int mPlayerOneScore;
    private int mPlayerTwoScore;
    private int mComputerScore;
    private boolean[][] gameGrid;

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

        gameGrid = new boolean[3][3];
    }

    private View.OnClickListener gameClicks = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnTopLeft:
                    gridMarker(v);

                    determineVictory();
                    break;
                case R.id.btnTopCenter:
                    gridMarker(v);

                    determineVictory();
                    break;
                case R.id.btnTopRight:
                    gridMarker(v);

                    determineVictory();
                    break;
                case R.id.btnCenterLeft:
                    gridMarker(v);

                    determineVictory();
                    break;
                case R.id.btnCenter:
                    gridMarker(v);

                    determineVictory();
                    break;
                case R.id.btnCenterRight:
                    gridMarker(v);

                    determineVictory();
                    break;
                case R.id.btnBottomLeft:
                    gridMarker(v);

                    determineVictory();
                    break;
                case R.id.btnBottomCenter:
                    gridMarker(v);

                    determineVictory();
                    break;
                case R.id.btnBottomRight:
                    gridMarker(v);

                    determineVictory();
                    break;
            }
        }
    };

    private void gridMarker(View v){
        final Button tempButton = (Button)findViewById(v.getId());
        //Determine whos turn it is

        //Set the font color to red(X) or blue(O)

        //Mark the button with the corresponding X's or O's
    }

    /**
     * Sets the players chosen button to the proper sign (X's or O's).
     */
    private void playerOneSelection(){

    }

    /**
     * Sets the players chosen button to the proper sign (X's or O's).
     */
    private void playerTwoSelection(){

    }

    /**
     * Sets the computer chosen button to the proper sign (X's or O's).
     */
    private void computerSelection(){

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
    private boolean determineVictory(){

        return false;
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
