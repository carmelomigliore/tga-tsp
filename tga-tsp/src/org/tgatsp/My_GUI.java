package org.tgatsp;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class My_GUI extends JFrame{
	JPanel my_panel;
	JFrame my_frame;
	Graphics my_g;
	
	
	public My_GUI(JPanel jp){
		my_g = jp.getGraphics(); 
	}
/*	
	public void fill_City(Cliente[] c){
		int i;
		my_g_back.setColor(Color.black);
			
		for(i=0; i<c.length; i++){
			my_g_back.fillOval((int) c[i].getCoordinates().x*2, 
								(int) c[i].getCoordinates().y*2,
								5, 
								5);
		}		
	}
*/
	
	public void drawTSP(Tour t){
		Random r = new Random();
		//Graphics2D g2D = (Graphics2D) g;
		int i=0;
		my_g.clearRect(0, 0, 1000, 1000);
		Color my_color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		for(i=0; i<t.getTour().size()-1; i++){
			my_g.setColor(Color.black);
			my_g.fillOval((int) t.getCliente(i).getCoordinates().x*4, 
				      		   (int) t.getCliente(i).getCoordinates().y*2,
					            5, 
					            5);
			my_g.setColor(my_color);
			my_g.drawLine((int) t.getCliente(i).getCoordinates().x*4, 
					      (int) t.getCliente(i).getCoordinates().y*2,
					      (int) t.getCliente(i+1).getCoordinates().x*4, 
					      (int) t.getCliente(i+1).getCoordinates().y*2
						 );
			}
		my_g.drawLine((int) t.getCliente(i).getCoordinates().x*4, 
			      (int) t.getCliente(i).getCoordinates().y*2,
			      (int) t.getCliente(0).getCoordinates().x*4, 
			      (int) t.getCliente(0).getCoordinates().y*2
				 );
		}
			
	}
