package org.sp.tproject.main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PomoPan extends JPanel{
	OnePomo pomo;
	JScrollPane scroll;
	
	public PomoPan() {
		//addPomo=new OnePomo();
		scroll=new JScrollPane(this);
		
		setLayout(new FlowLayout());
		//add(addPomo);
		
		//setSize(240, 300);
		setBackground(new Color(129, 125, 119));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(240, 400));
		setVisible(true);
	}
	
	public void addPomo() {
		pomo=new OnePomo();
		this.add(pomo);
	}
}
