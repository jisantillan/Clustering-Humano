package VerGrafo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class arista {
	Color color;
	Random r=new Random();
	int x,y,x1,y1;
	int valor;//nombre del vertice
	public arista (int d,int x, int y,int x1, int y1){//nombre, posicion y distancia
		color=new Color (r.nextInt(256),r.nextInt(256),r.nextInt(256));
		this.x=x;
		this.y=y;
		this.x1=x1;
		this.y1=y1;
		valor=d;
	}
	public void paint(Graphics g){
		g.setColor(color);
		g.drawLine(x,y,x1,y1);//dibuja la linea
		g.drawString(""+valor,((x+x1)/2),((y+y1)/2));
	}
}
