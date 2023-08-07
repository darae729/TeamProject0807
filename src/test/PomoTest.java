package test;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PomoTest extends JFrame{

	AddPomoTest pomo1;
	AddPomoTest pomo2;
	AddPomoTest pomo3;
	AddPomoTest pomo4;
	AddPomoTest pomo5;
	
	
	//ArrayList<AddPomoTest> pomoList;
	
	public PomoTest() {
	
		//pomoList=new ArrayList<AddPomoTest>();
		//pomoList=new AddPomoTest[4];
		
		pomo1=new AddPomoTest();
		pomo2=new AddPomoTest();
		pomo3=new AddPomoTest();
		pomo4=new AddPomoTest();
		pomo5=new AddPomoTest();
		
		setLayout(new FlowLayout());
		
		add(pomo1);
		add(pomo2);
		add(pomo3);
		add(pomo4);
		add(pomo5);
			
		setSize(240, 300);
		this.getContentPane().setBackground(Color.WHITE);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	public static void main(String[] args) {
		new PomoTest();
		
	}
}
