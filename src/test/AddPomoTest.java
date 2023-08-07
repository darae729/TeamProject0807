package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddPomoTest extends JPanel{
	
	public AddPomoTest() {
		//setOpaque(true);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(50, 50));
	}
	
	public void paint(Graphics g) { 
		//System.out.println("왜 안붙니");
		
		//이미지 그려보기
		Toolkit tool=Toolkit.getDefaultToolkit();
		//툴킷 객체 생성
		Image image=tool.getImage("res/img/naviIcon/tomato.png");
		g.drawImage(image, 0, 0, 40, 40, this);
	
	}
}
