//package org.sp.tproject.member.view;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//public class FindIdPage extends FindPage{
//
//
//	JPanel find_p_name;
//	JTextField find_t_name;
//	JPanel find_p_email;
//	JTextField find_t_email;
//	JButton find_bt_email;	//전송하기 버튼
//	
//	JPanel find_p_auth;
//	JTextField find_t_auth;
//	JButton find_bt_auth;	//인증하기 버튼
//	
//	public FindIdPage() {
//
//
//		find_p_name = new JPanel();
//		find_t_name = new JTextField();
//		
//		find_p_email = new JPanel();
//		find_t_email = new JTextField();
//		find_bt_email = new JButton("전송하기");
//		
//		find_p_auth = new JPanel();
//		find_t_auth = new JTextField();
//		find_bt_auth = new JButton("인증하기");
//
//
//		
//		Dimension find_bt_d = new Dimension(150,40);
//		Dimension find_p_d = new Dimension(470,100);
//		Dimension find_text_d = new Dimension(300,50);
//		find_north.setPreferredSize(new Dimension(300,70));
//		find_center.setPreferredSize(new Dimension(300,300));
//		//find_center.setBackground(Color.BLUE);
//		find_p_findmenu.setPreferredSize(new Dimension(500,50));
//		//find_p_findmenu.setBackground(Color.GREEN);
//		find_p_findmenu.setLayout(new FlowLayout(FlowLayout.LEFT));
//		find_bt_findid.setPreferredSize(find_bt_d);
//		find_bt_findid.setBackground(Color.WHITE);
//		find_bt_findpass.setPreferredSize(find_bt_d);
//		find_bt_findpass.setBackground(Color.WHITE);
//		
//		find_p_name.setPreferredSize(find_p_d);
//		find_p_name.setLayout(new FlowLayout(FlowLayout.LEFT));
//		find_t_name.setPreferredSize(find_text_d);
//
//		find_p_email.setPreferredSize(find_p_d);
//		find_p_email.setLayout(new FlowLayout(FlowLayout.LEFT));
//		find_t_email.setPreferredSize(find_text_d);
//
//		find_p_auth.setPreferredSize(find_p_d);
//		find_p_auth.setLayout(new FlowLayout(FlowLayout.LEFT));
//		find_t_auth.setPreferredSize(find_text_d);
//
//		
//		//폰트
//		Font find_bt_font = new Font("goyang", Font.PLAIN, 15);
//		find_bt_findid.setFont(find_bt_font);
//		find_bt_findpass.setFont(find_bt_font);
//		
//		//부착
//		find_center.add(find_p_name);
//		find_p_name.add(find_t_name);
//		
//		find_center.add(find_p_email);
//		find_p_email.add(find_t_email);
//		find_p_email.add(find_bt_email);
//		find_center.add(find_p_auth);
//		find_p_auth.add(find_t_auth);
//		find_p_auth.add(find_bt_auth);
//	}
//}
