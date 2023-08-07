package org.sp.tproject.main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

//토마토 스티커(이미지) 1개
public class OnePomo extends JPanel{
	
	public OnePomo() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(50, 50));
	}
	
	public void paint(Graphics g) { 
		//이미지 그리기
		Toolkit tool=Toolkit.getDefaultToolkit();
		//툴킷 객체 생성
		Image image=tool.getImage("res/img/naviIcon/tomato.png");
		g.drawImage(image, 0, 0, 40, 40, this);
	
	}
}