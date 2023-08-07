package org.sp.tproject.member.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.sp.tproject.calendar.domain.Client;
import org.sp.tproject.calendar.model.ClientDAO;
import org.sp.tproject.main.view.MainFrame;

import util.DBManager;
import util.HashConverter;



public class LoginForm extends JFrame{
	MainFrame mainFrame;
	
	JTextField login_id;
	JPasswordField login_pass;
	JButton login_bt;
	JPanel login_south;
	JLabel login_regist;
	JLabel login_line;
	JLabel login_find;
	
	//db 관련
	DBManager dbManager;
	HashConverter hashConverter;
	
	//DAO를 이용하여 db관련 업무 수행
	ClientDAO clientDAO;
	
	public Client clientDTO;
		
	public LoginForm(MainFrame mainFrame) {
		super("사용자 로그인");
		this.mainFrame=mainFrame;
		
		login_id = new JTextField("아이디를 입력하세요");
		login_pass = new JPasswordField("비밀번호를 입력하세요");
		login_bt = new JButton("로그인");
		login_south = new JPanel();
		login_regist = new JLabel("회원가입");
		login_line = new JLabel("|");
		login_find = new JLabel("아이디 | 비밀번호찾기");
		dbManager = new DBManager();
		clientDAO = new ClientDAO(dbManager);
		
		//비밀번호 해시
		hashConverter = new HashConverter();
		
		//텍스트 필드 안에 회색 글자
		Font t_loginFont = new Font("돋움", Font.PLAIN ,20);
		Color t_loginColor = Color.GRAY;
		//폰트적용
		login_id.setForeground(t_loginColor);
		login_id.setFont(t_loginFont);
		login_pass.setForeground(t_loginColor);
		login_pass.setFont(t_loginFont);
		
		//스타일
		Dimension d = new Dimension(380,45);
		login_id.setPreferredSize(d);
		login_pass.setPreferredSize(d);
		login_bt.setPreferredSize(new Dimension(300,45));
		login_south.setPreferredSize(new Dimension(380,50));
		login_regist.setPreferredSize(new Dimension(50,50));
		setLayout(new FlowLayout());
		
		
		//조립
		add(login_id);
		add(login_pass);
		add(login_bt);
		add(login_south);
		login_south.add(login_regist);
		login_south.add(login_line);
		login_south.add(login_find);
		

		setSize(500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		login_id.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				login_id.setText("");
			}
		});
		login_pass.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				login_pass.setText("");
			}
		});
		//로그인 버튼 눌렀을 때, 동작
		login_bt.addActionListener((e)->{
			loginCheck();
		});
		login_regist.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JLabel la = (JLabel)e.getSource();
				System.out.println("회원가입 클릭");
			}
		
		});
		login_find.addMouseListener(new MouseAdapter() {
			//마우스 구현 실패
			public void mouseEntered(MouseEvent e) {
				JLabel la = (JLabel)e.getSource();
				la.setBackground(Color.BLACK);
				la.setBackground(Color.WHITE);
			}

			public void mouseClicked(MouseEvent e) {
				JLabel la = (JLabel)e.getSource();
				System.out.println("찾기 클릭");
			}
		});
		
	}
	
	public void loginCheck() {
		
		//사용자가 입력한 아이디와 패스워드를 채워넣을 빈(empty) 상태의 DTO 생성
		String id = login_id.getText();	//사용자가 입력한 아이디
		String pass = new String(login_pass.getPassword());
		
		Client client = new Client();	//empty
		client.setId(id);	//아이디 대입
		client.setPass(hashConverter.convertToHash(new String(login_pass.getPassword())));	//비밀번호 대입
		clientDTO = clientDAO.login(client);
		System.out.println(client.getId());
		System.out.println(client.getPass());
		
		if(clientDTO == null) {	//로그인 실패
			JOptionPane.showMessageDialog(this, "로그인실패");
			
		}else { //로그인 성공
			JOptionPane.showMessageDialog(this, "로그인성공");
			mainFrame.login();
			mainFrame.client=clientDTO;
		}
		
		
	}
	
	/*
	public static void main(String[] args) {
		new LoginForm();
	}
	*/
	
}