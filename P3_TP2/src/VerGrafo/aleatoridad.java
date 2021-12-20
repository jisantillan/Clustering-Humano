package VerGrafo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
/*para que no se pisen los nodos*/
public class aleatoridad {
	
	Random r=new Random();
	int ini,fin;
	int lim=30;//60;
	int limite=300;//530;
	int limite1=400;//530
	ArrayList<Point>puntos;//puntos de cada vertice
	public aleatoridad(int cantvert){
		/*ini='A';
		this.fin=fin;*/
		ini=0;
		this.fin=cantvert;
		puntos=new ArrayList<Point>();
		crearCirculos();
	}
	public boolean rango(int distancia,int e){
		boolean salida=false;
		if (puntos.isEmpty()){
			return true;
		}
		else{
			for (Point i:puntos){
				int x=i.x;
				int y=i.y;
				if((((distancia>=x+lim)||(distancia<=x-lim))&&e==1)||(((distancia>=y+lim)||(distancia<=y-lim))&&e==0)){
					return true;

				}
			}
		}
		return salida;
	}
	public void crearCirculos(){
		if (ini>fin){

		}
		else{
			int xx=r.nextInt(limite);
			int y=r.nextInt(limite);
			if(rango(xx,1)||rango(y,0)){//si el rango excede o limita al vertice anterior
				puntos.add(new Point (xx,y));
				ini++;
			}
			crearCirculos();
		}
	}
	public ArrayList<Point> getList(){
		return puntos;
	}

}