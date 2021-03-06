package Proyecto.Cluedo.Ventanas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Proyecto.Cluedo.Logica.FicheroCoordenadasPosiciones;

public class Panelcirculos extends JPanel{
	

	public Point [] arrpuertas={new Point(1391,397),new Point(241,121),new Point(209,255),new Point(504,196),new Point(629,113),new Point(1097,289),new Point(1621,185),new Point(1651,325),new Point(1846,174),new Point(1881,334),new Point(1321,771),new Point(573,876),new Point(855,261),new Point(295,104)};
	public Point [] puente = {new Point(216, 637),new Point(1391,397),new Point(86,414),new Point(1251,673)};

	private ArrayList<Point>arrpuntmarrones=new ArrayList<Point>();	
	private HashMap<Point,String> hm=new HashMap();

	public Panelcirculos(){
		FicheroCoordenadasPosiciones fcoordenadas=new FicheroCoordenadasPosiciones();
		arrpuntmarrones=fcoordenadas.leerDeFicheroConBarras("cordeenadascirculos.txt");
		
		for(int i=0;i<arrpuntmarrones.size();i++){
			hm.put(arrpuntmarrones.get(i),"marron" );
			
		}
		for(int j=0;j<arrpuertas.length;j++){
			hm.put(arrpuertas[j],"verde" );
		}
		
		for (Point p: puente){
			hm.put(p, "morado");
		}
	}
	
	
	@Override
	public void paint(Graphics g) {
		BufferedImage imagen;
		try {
			imagen = ImageIO.read(getClass().getResource("Imagenes/fondocirculos.png"));
			
//			System.out.println(imagen.getWidth());
//			
//			System.out.println(imagen.getHeight());
			
			Graphics g3=imagen.getGraphics();
			Graphics2D g2 = (Graphics2D) g;  
			
			for(Point key:hm.keySet()){
			   String color=hm.get(key);
			 if(color=="marron"){
				 g3.setColor(new Color(170,158,117,255));//Color marron

				 
			}else if(color=="verde"){
				g3.setColor(new Color(24,205,95,255));
				
				
				 
				
			}else if (color=="morado"){
				
			g3.setColor(new Color(102, 15,130 ,255));
			}
			else if(color=="rojo"){
				g3.setColor(new Color(219,91,125,255));
				
			}else if(color=="azul"){
				g3.setColor(new Color(57,69,207,255));
				
			}
				
				
			g3.drawOval((int)key.getX()-28,(int)key.getY()-16,55,32 );
			g3.fillOval((int)key.getX()-28,(int)key.getY()-16,55,32 );
			}
			//g2.drawImage( imagen, 0,0, getWidth(), getHeight(), this );
			g2.drawImage( imagen, 0,0, getWidth(), getHeight(), this );
			revalidate();
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		setOpaque(false);
		super.paint(g);
		
	}
	public void meterOcupado(Point p){
		hm.put(p,"rojo" );
	}
	public void meterPosibilidades(ArrayList<Point> p){
		for(int i=0;i<p.size();i++){
			hm.put(p.get(i),"azul" );
		}
		
	}
	public boolean estaEn(Point punto,Point [] array){
		for(int i=0;i<array.length;i++){
			if(punto.equals(array[i])){
				return true;
			}
		}return false;
	}
	public void volverAColorOriginal(ArrayList<Point> p){
		for(int i=0;i<p.size();i++){
			if(estaEn(p.get(i),arrpuertas)){
				hm.put(p.get(i),"verde" );
			}
			else if(estaEn(p.get(i), puente)){
				hm.put(p.get(i),"morado" );
			}else{
				hm.put(p.get(i),"marron" );
			}
			
		}
		
	}
	public ArrayList<Point> arrayBuscar(){
		ArrayList<Point> resultado=new ArrayList<Point>();
		for(Point key:hm.keySet()){
			resultado.add(key);
		}return resultado;
}
}