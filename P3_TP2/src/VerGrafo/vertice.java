package VerGrafo;

import java.awt.Color;
import java.awt.Graphics;

public class vertice {
	Color color;
	int x,y;
	String nombre;//nombre del vertice
	public vertice (String nombre,int x, int y){//nombre y posicion
		this.x=x-20;
		this.y=y-20;
		this.nombre=nombre;
	}
	public void paint(Graphics g){
		g.setColor(Color.PINK);
		g.fillOval(x,y,40,40);
		g.setColor(Color.WHITE);//nombre en blanco
		g.drawString(""+nombre,x,y+17);
	}
}
