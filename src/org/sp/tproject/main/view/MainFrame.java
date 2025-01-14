package org.sp.tproject.main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sp.tproject.calendar.domain.Client;
import org.sp.tproject.calendar.model.ClientDAO;
import org.sp.tproject.calendar.model.PlanDAO;
import org.sp.tproject.calendar.view.DiaryPage;
//import org.sp.tproject.main.domain.Pomocount;
//import org.sp.tproject.main.domain.Pomodate;
//import org.sp.tproject.main.model.PomocountDAO;
//import org.sp.tproject.main.model.PomodateDAO;
import org.sp.tproject.member.view.LoginForm;
import org.sp.tproject.member.view.MyPage;

import util.DBManager;

public class MainFrame extends JFrame{
	LoginForm loginForm;
	
	JPanel p_north; //네비게이션 영역
	JPanel p_content; //각 페이지 및 컨텐츠들이 배치될 메인 영역
	
	//네비게이션 이미지경로 배열
	String[] naviImg= 
		{"img/naviIcon/home_main.png", "img/naviIcon/calendar.png", "img/naviIcon/git.png", "img/naviIcon/logout.png"}; 
	//네비게이션(라벨) 배열
	ArrayList<JLabel> naviIcon;
	
	Page[] pages; //페이지 배열
	public static final int MAIN=0;
	public static final int DIARY=1;
	public static final int MYPAGE=2;
	public static final int LOGOUT=3;
	
	int width=1230;
	int height=800;
	
	//DB관련
	DBManager dbManager=new DBManager();
	//Pomodate pomodate;	
	//Pomocount pomocount=new Pomocount();
	ClientDAO clientDAO=new ClientDAO(dbManager);
	//PomodateDAO pomodateDAO=new PomodateDAO(dbManager);
	//PomocountDAO pomocountDAO=new PomocountDAO(dbManager);
	PlanDAO planDAO=new PlanDAO(dbManager);
	
	//보안 때문에 보통 메서드로 함 -> 바꾸자.. 근데 얘 지금 null임, 클라이언트DTO는 로그인폼에서 땡겨옴
	public Client client;
	
	public MainFrame() {
		p_north=new JPanel();
		p_content=new JPanel();
		createNavi();
		
		pages=new Page[3]; //4
		pages[MAIN]=new MainPage(this);
		pages[DIARY]=new DiaryPage(this);
		pages[MYPAGE]=new MyPage();
		//pages[LOGOUT]=new LogIn();
			
		//스타일
		p_north.setBackground(new Color(233, 233, 233));
		p_north.setPreferredSize(new Dimension(width, 50));
		p_content.setBackground(Color.WHITE);
		//p_content.setBorder(new LineBorder(Color.WHITE, 1));
		
		//조립
		for (int i = 0; i < pages.length; i++) {
			p_content.add(pages[i]);
		}
		add(p_north, BorderLayout.NORTH);
		add(p_content);
		
		setSize(width, height);
		setVisible(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//로그인폼 생성
		loginForm=new LoginForm(this);
		
		//디폴트 페이지는 메인페이지
		showHide(MAIN);
		
		for(int i=0; i<naviIcon.size(); i++) {
			JLabel obj=naviIcon.get(i);
			obj.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					int index=naviIcon.indexOf(e.getSource()); //클릭한 네비아이콘이 몇번째 라벨인지
					showHide(index);
				
				}
			});
		}
		
	}
	
	public void login() { //로그인 시 호출하는 메서드
		//1) 프레임창에 회원 닉네임 출력
		this.setTitle(loginForm.clientDTO.getName()+" 님 안녕하세요~*");
		//2) 현재 메인프레임을 보이게 처리
		this.setVisible(true);
		//3) 로그인폼을 보이지 않게 처리
		loginForm.setVisible(false);
	}
	public void logout() { //로그아웃 시 호출하는 메서드
		
	}
	
	public void createNavi() { //네비 생성 메서드
		naviIcon=new ArrayList<JLabel>();
		
		for(int i=0; i<naviImg.length; i++) {
			URL url=ClassLoader.getSystemResource(naviImg[i]);
			
			try {
				BufferedImage buffimg=ImageIO.read(url);
				Image image=buffimg;
				image=image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				JLabel la_navi=new JLabel(new ImageIcon(image));
				la_navi.setPreferredSize(new Dimension(40, 40));
				naviIcon.add(la_navi);
				p_north.add(la_navi);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public void showHide(int n) { //페이지 전환처리 메서드
		for (int i = 0; i < pages.length; i++) {
			if (i == n) { // 넘겨받은 매개변수와 i가 일치할때만 보이게함
				pages[i].setVisible(true);
			} else {
				pages[i].setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}