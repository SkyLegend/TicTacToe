package com.sky.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button cpuButton = (Button) findViewById(R.id.btnCPU);
        final Button playerButton = (Button) findViewById(R.id.bthPlayer);

        cpuButton.setOnClickListener(gameStartListener);
        playerButton.setOnClickListener(gameStartListener);
    }

    private View.OnClickListener gameStartListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, GameScreenActivity.class);
            startActivity(intent);
        }
    };

    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */
}
