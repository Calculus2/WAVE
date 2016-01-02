package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		if(game.gameState == STATE.Menu){
			// play button
			if(mouseOver(mx, my, 210, 150,200,64)){
				// game.gameState = STATE.Game;
				
				// handler.addObject(new Player(Game.WIDTH/2 - 32,Game.HEIGHT/2 - 32,ID.Player, handler));
				// handler.clearEnemys();
				
				// handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy, handler));
				game.gameState = STATE.Select;
				return;
			}
			
			//help button
			if(mouseOver(mx,my,210,250,200,64)){
				game.gameState = STATE.Help;
			}
			
			
			
			// quit button
			if(mouseOver(mx,my,210,350,200,64)){
				System.exit(1);
			}
	
		
		}
		
		if(game.gameState == STATE.Select){
			// normal button
			if(mouseOver(mx, my, 210, 150,200,64)){
				
				game.gameState = STATE.Game;
				
				 handler.addObject(new Player(Game.WIDTH/2 - 32,Game.HEIGHT/2 - 32,ID.Player, handler));
				 handler.clearEnemys();
				
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy, handler));
				game.diff = 0;
			}
			
			//hard button
			if(mouseOver(mx,my,210,250,200,64)){
				
				game.gameState = STATE.Game;
				
				 handler.addObject(new Player(Game.WIDTH/2 - 32,Game.HEIGHT/2 - 32,ID.Player, handler));
				 handler.clearEnemys();
				
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy, handler));
				game.diff = 1;
			}
			
			
			
			// back button
			if(mouseOver(mx,my,210,350,200,64)){
				game.gameState = STATE.Menu;
				return;
			}
		}
		//back button for help
				if(game.gameState == STATE.Help){
					if(mouseOver(mx,my,210,350,200,64)){
						game.gameState = STATE.Menu;
						return;
					}
				}
				//try again button
				if(game.gameState == STATE.End){
					if(mouseOver(mx,my,210,350,200,64)){
						game.gameState = STATE.Menu;
						hud.setScore(0);
						hud.setLevel(1);
						
					}
				}
		}
		
	
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx<x + width ){
			if(my > y && my<y + height){
				return true;
			} else return false;
		} else return false;
	}
	
    public void tick(){
	
}
	
	
	 public void render(Graphics g){
		 if(game.gameState == STATE.Menu){
		 Font fnt = new Font("Serif Bold",1,50);
		 Font fnt2 = new Font("Serif Bold",1,30);
		 Font fnt3 = new Font("Serif Bold",1,18);
		 
		 
		 g.setFont(fnt);
		 g.setColor(Color.WHITE);
		 g.drawString("Wave", 240, 70);
		 
		
		 g.setFont(fnt2);
		 g.drawRect(210,150,200,64);
		 g.drawString("Play",270, 190);
		 
		 g.drawRect(210,250, 200, 64);
		 g.drawString("Help",270, 290);
		 
		 g.drawString("Quit",270, 390);
		 g.drawRect(210,350, 200, 64);
		 
		 g.setFont(fnt3);
		 g.drawString("Created by Ahmed Ahres", 205, 440);
		 
		 } else if (game.gameState == STATE.Help){
			 Font fnt = new Font("Serif Bold",1,50);
			 Font fnt2 = new Font("Serif Bold",1,30);
			 Font fnt3 = new Font("Serif Bold",1,17);
			 
			 
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			 g.drawString("Help",240, 70);
			 
			 g.setFont(fnt3);
			 g.drawString("Use Arrows to move player and dodge enemies.", 80, 140);
			 g.drawString("Tap P to pause the game.", 80, 180);
			 g.drawString("You pass to the next level each time score increases by 300.", 80, 220);
			 g.drawString("In Normal mode, enemies stop spawning in level 13.", 80, 260);
			 g.drawString("In Hard mode, enemies stop spawning in level 10.", 80, 300);
			 
			 g.setColor(Color.red);
			 g.drawString("GOOD LUCK!", 80, 340);
			 
			 g.setColor(Color.white);
			 g.setFont(fnt2);
			 g.drawString("Back",270, 390);
			 g.drawRect(210,350, 200, 64);
		 } else if (game.gameState == STATE.End){
			 Font fnt = new Font("Serif Bold",1,50);
			 Font fnt2 = new Font("Serif Bold",1,30);
			 Font fnt3 = new Font("Serif Bold",1,20);
			 
			 
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			 g.drawString("Game Over",180, 70);
			 
			 g.setFont(fnt3);
			 g.drawString("You lost with a score of : " + hud.getScore() , 175, 200);
			 g.drawString("Level : " + hud.getLevel(), 175, 250);
			 
			 g.setFont(fnt2);
			 g.drawString("Try Again",235, 390);
			 g.drawRect(210,350, 200, 64);
	 
	 }
		 if(game.gameState == STATE.Select){
			 Font fnt = new Font("Serif Bold",1,50);
			 Font fnt2 = new Font("Serif Bold",1,30);
			 
			 
			 g.setFont(fnt);
			 g.setColor(Color.WHITE);
			 g.drawString("SELECT DIFFICULTY", 80, 70);
			 
			
			 g.setFont(fnt2);
			 g.drawRect(210,150,200,64);
			 g.drawString("Normal",250, 190);
			 
			 g.drawRect(210,250, 200, 64);
			 g.drawString("Hard",270, 290);
			 
			 g.drawString("Back",270, 390);
			 g.drawRect(210,350, 200, 64);

	 }
	 }
}
		 

