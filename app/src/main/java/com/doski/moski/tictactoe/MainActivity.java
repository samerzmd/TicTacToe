package com.doski.moski.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;


import com.jrummyapps.android.widget.AnimatedSvgView;

public class MainActivity extends AppCompatActivity {

	Game game;
	TextView hint;
	View player1Selector;
	View player2Selector;

	TextView player1Score;
	TextView player2Score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("statistics",new Object(){}.getClass().getEnclosingMethod().getName());
		Log.i("statistics", String.valueOf(System.nanoTime()));
		setContentView(R.layout.activity_main);

		game = new Game(this);
		hint = (TextView) findViewById(R.id.hint);
		hint.setText(game.hint());

		player1Selector = findViewById(R.id.selectPlayer1);
		player2Selector = findViewById(R.id.selectPlayer2);

		player1Score = (TextView) findViewById(R.id.player1Score);
		player2Score = (TextView) findViewById(R.id.player2Score);
		Log.i("statistics", String.valueOf(System.nanoTime()));
	}

	public void play(View view){
		Log.i("statistics",new Object(){}.getClass().getEnclosingMethod().getName());

		AnimatedSvgView currentView = (AnimatedSvgView) view;

		int cellCounter = Integer.parseInt(currentView.getTag().toString());


		if (game.gameState != GameState.ENDED && game.play(cellCounter)){
			// Draw the Cell
			setSvg(currentView, SVG.values()[game.getCurrentPlayer()]);


			switch (game.gameState){
				case ENDED:
					break;
				case PLAYING:
					game.switchTurn();
					updateCurrentPlayerSelector();
					break;
			}
			hint.setText(game.hint());
			updateScore();
		}
		Log.i("statistics", String.valueOf(System.nanoTime()));
	}

	public void resetGame(View view){
		// Reset the game State and currentPlayer
		Log.i("statistics",new Object(){}.getClass().getEnclosingMethod().getName());
		Log.i("statistics", String.valueOf(System.nanoTime()));
		game.reset();
		updateCurrentPlayerSelector();

		GridLayout gameBoard = (GridLayout) findViewById(R.id.gameBoard);

		// Reset the SVGs to empty state.
		for(int i=0; i<gameBoard.getChildCount(); i++){
			AnimatedSvgView cell = (AnimatedSvgView) gameBoard.getChildAt(i);
			setSvg(cell, SVG.values()[2]);
		}

		hint.setText(game.hint());
		Log.i("statistics", String.valueOf(System.nanoTime()));
	}

	private void updateCurrentPlayerSelector(){
		Log.i("statistics",new Object(){}.getClass().getEnclosingMethod().getName());
		Log.i("statistics", String.valueOf(System.nanoTime()));
		if(game.getCurrentPlayer() == 0){
			player1Selector.setBackgroundColor(getResources().getColor(R.color.currentPlayer));
			player2Selector.setBackgroundColor(getResources().getColor(R.color.otherPlayer));
		}else{
			player1Selector.setBackgroundColor(getResources().getColor(R.color.otherPlayer));
			player2Selector.setBackgroundColor(getResources().getColor(R.color.currentPlayer));
		}
		Log.i("statistics", String.valueOf(System.nanoTime()));
	}

	private void updateScore(){
		Log.i("statistics",new Object(){}.getClass().getEnclosingMethod().getName());
		Log.i("statistics", String.valueOf(System.nanoTime()));
		player1Score.setText(game.getScore(0));
		player2Score.setText(game.getScore(1));
		Log.i("statistics", String.valueOf(System.nanoTime()));
	}

	private void setSvg(AnimatedSvgView svgView, SVG svg) {
		Log.i("statistics",new Object(){}.getClass().getEnclosingMethod().getName());
		Log.i("statistics", String.valueOf(System.nanoTime()));
		svgView.setGlyphStrings(svg.glyphs);
		svgView.setFillColors(svg.colors);
		svgView.setViewportSize(svg.width, svg.height);
		svgView.setTraceResidueColor(0x32000000);
		svgView.setTraceColors(svg.colors);
		svgView.rebuildGlyphData();
		svgView.start();
		Log.i("statistics", String.valueOf(System.nanoTime()));
	}
}
