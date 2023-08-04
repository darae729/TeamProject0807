package org.sp.tproject.member.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.sp.tproject.main.view.Page;

public class ClientFind extends JFrame{
	JPanel find_north;
	JPanel find_center;
	JPanel find_p_findmenu;	//아이디찾기,비밀번호찾기 버튼을 위한 패널
	JButton find_bt_findid;
	JButton find_bt_findpass;

	
	//각페이지를 상수로 표현
//	public static final int FINDID=0;
//	public static final int IDINFO=1;
//	public static final int FINDPASS=2;
//	public static final int PASSINFO=3;
	
	
	//회원 아이디 및 비밀번호 찾기 페이지
//	FindPage[] pages;
	
	public ClientFind() {
		find_north = new JPanel();
		find_center = new JPanel();
		find_p_findmenu = new JPanel();
		find_bt_findid = new JButton("아이디찾기");
		find_bt_findpass = new JButton("비밀번호찾기");
		
		Dimension find_bt_d = new Dimension(150,40);
		find_north.setPreferredSize(new Dimension(300,70));
		find_center.setPreferredSize(new Dimension(300,300));
		//find_center.setBackground(Color.BLUE);
		find_p_findmenu.setPreferredSize(new Dimension(500,50));
		//find_p_findmenu.setBackground(Color.GREEN);
		find_p_findmenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		find_bt_findid.setPreferredSize(find_bt_d);
		find_bt_findid.setBackground(Color.WHITE);
		find_bt_findpass.setPreferredSize(find_bt_d);
		find_bt_findpass.setBackground(Color.WHITE);
		
		//부착 
		add(find_north, BorderLayout.NORTH);
		add(find_center, BorderLayout.CENTER);
		find_north.add(find_p_findmenu);
		find_p_findmenu.add(find_bt_findid);
		find_p_findmenu.add(find_bt_findpass);
		
		setSize(500,400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
	}
	public static void main(String[] args) {
		new ClientFind();
	}
}
