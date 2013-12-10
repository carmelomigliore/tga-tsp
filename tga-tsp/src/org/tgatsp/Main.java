package org.tgatsp;

import java.util.Random;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		
		final int populationSize=50;
		final int maxEpoch=1000;
		final int deadlockThreshold=100; //TODO deadlock= a popsize
		final float tabuCoefficient=0.4F;
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        
        
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
            	super.paintComponent(g);              
            }
        };
       panel.setLayout(null);
        
     //panel_back.setBackground(new Color(0,0,0,125));
      
        //panel_back.setOpaque(true);
        
        
        frame.add(panel);
       
        frame.validate(); // because you added panel after setVisible was called
        frame.repaint(); // because you added panel after setVisible was called


        My_GUI my_gui = new My_GUI(panel);
         
		Population pop= new Population(populationSize);
		TGA algorithm=new TGA(pop,populationSize,maxEpoch,deadlockThreshold,tabuCoefficient, my_gui);
		
		Cliente.init(args[0]);
		Random rand= new Random(System.currentTimeMillis());
		//Population.randomPopulation(0, 200, pop, rand);
		//Population.nearestNeighbour(pop, 80, 20, rand);
		//Population.nearestNeighbour(pop, 947);
		Population.randomPopulation2Opt(0, 50, pop, rand);
		//System.out.println(pop);
		algorithm.startEngine();
		}
		
		
	}

