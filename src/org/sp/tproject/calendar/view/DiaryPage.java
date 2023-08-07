package org.sp.tproject.calendar.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.sp.tproject.calendar.domain.Client;
import org.sp.tproject.calendar.domain.Plan;
import org.sp.tproject.calendar.model.ClientDAO;
import org.sp.tproject.calendar.model.IconDAO;
import org.sp.tproject.calendar.model.PlanDAO;
import org.sp.tproject.main.view.MainFrame;
import org.sp.tproject.main.view.Page;

import util.DBManager;
import util.RoundedButton;
import util.StringManager;

//다이어리 만들기 
public class DiaryPage extends Page{

	//JPanel p_north;
	JPanel p_west;
	JPanel p_center;
	
	//JPanel p_east;
	MainFrame mainFrame;
	
	
	RoundedButton bt_prev;
	RoundedButton bt_next;
	JLabel la_title;
	JLabel la_title2;
	String[] dayTitle= {"SUN","MON","TUE","WED","THU","FRI","SAT"};
	
	Calendar cal; //이전, 다음 버튼등에 의해 조작될 날짜 객체
	int currentYear; //현재 사용자가 보게될 연도
	int currentMonth;//현재 사용자가 보게될 월 
	
	int width=1230;
	int height=800;
	
	
	//날짜 셀 
	NumCell[][] numCells=new NumCell[6][7]; 
	
	
	//데이터베이스
	DBManager dbManager = new DBManager();
	//IconDAO iconDAO = new IconDAO(dbManager);
	PlanDAO planDAO = new PlanDAO(dbManager);
	ClientDAO clientDAO = new ClientDAO(dbManager);
	
	//Client client;
	
	Popup popup;
	List<Plan> planList; //일정 정보 목록
	
	public DiaryPage(MainFrame mainFrame) {
		this.mainFrame=mainFrame;
		
		setBackground(Color.WHITE);
		
		
		Border border;
		border=new LineBorder(Color.LIGHT_GRAY, 1, true);
		
		//UI 생성하기
		//p_north = new JPanel();
		p_west = new JPanel();
		p_center = new JPanel();
		//p_east = new JPanel();
		la_title = new JLabel("2023");
		la_title2 = new JLabel("08");
		bt_prev = new RoundedButton("◀");
		bt_next = new RoundedButton("▶");
		
		cal = Calendar.getInstance(); //날짜 객체 생성(디폴트=현재날짜)
		
		//날짜 객체로 부터 연, 월 구하기 
		currentYear		= cal.get(Calendar.YEAR);
		currentMonth	= cal.get(Calendar.MONTH);
		
	
		//스타일 
		la_title2.setFont(new Font("arial", Font.BOLD, 70));
		la_title.setFont(new Font("arial", Font.BOLD, 35));

		la_title.setBackground(Color.WHITE);
		p_center.setBackground(Color.WHITE);
		p_west.setBackground(Color.WHITE);
		
		
		p_west.setPreferredSize(new Dimension(100, 700));
		p_center.setPreferredSize(new Dimension(750, 800));
		//p_east.setPreferredSize(new Dimension(100, 900));
		
		//p_west.setBorder(new TitledBorder(border, ""));
		//p_center.setBorder(new TitledBorder(border, ""));
		
		
		//조립
		p_west.add(la_title2);
		p_west.add(la_title);
		p_west.add(bt_prev);
		p_west.add(bt_next);
		
		p_west.setLayout(new FlowLayout());
		
		//add(p_north, BorderLayout.NORTH);
		add(p_west, BorderLayout.WEST);
		add(p_center);
		//add(p_east, BorderLayout.EAST);
		
		login();
		
		createCell(); //달력에 사용될 셀 생성하기
		printTitle(); //달력 제목 출력
		getPlanList();
		printNum(); //날짜 출력
		
		setSize(width, height);
		setVisible(true);
		
		popup = new Popup(this);
		
		
		bt_prev.addActionListener((e)->{
			prev();
		});
		
		bt_next.addActionListener((e)->{
			next();
		});
		

	}
	
	//셀 만들기 
	public void createCell() {
		Border border=new LineBorder(Color.BLACK, 1, true);
		
		//요일 셀 만들기
		for(int i=0;i<dayTitle.length;i++) {
			DayCell cell = new DayCell(Color.DARK_GRAY, 100, 45);
			cell.setTitle(dayTitle[i]);
			p_center.add(cell);
		}
			
		//날짜 셀 만들기
		for(int a=0;a<6;a++) { //6층
			for(int i=0;i<7;i++) { //7호수
				NumCell cell = new NumCell(this, Color.WHITE, 100, 100);
				cell.setTitle("0");
				
				//한층에 소속된 호수들을 배열에 채우기 
				numCells[a][i]=cell;
				p_center.add(cell);
			}
		}
	}
	
	//날짜 제목 출력 
	public void printTitle() {
		int year=cal.get(Calendar.YEAR); 
		int mm=cal.get(Calendar.MONTH); 

		la_title.setText(StringManager.getNumString(year));
		la_title2.setText(StringManager.getNumString(mm+1));
	}
	
	//이전 날짜 처리 
	public void prev() {
		//다음 월 처리 
		int mm=cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, mm-1); //조작
		printTitle();//제목출력
		getPlanList();
		printNum();//날짜출력
	}
	
	//다음 날짜 처리
	public void next() {
		//다음 월 처리 
		int mm=cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, mm+1); //조작
		printTitle();//제목출력
		getPlanList();
		printNum();//날짜출력
		
	}
	
	//해당 월의 시작 요일 구하기 
	public int getStartDayOfWeek() {
		Calendar c = Calendar.getInstance();
		
		int yy=cal.get(Calendar.YEAR); //현재 보고있는 연도
		int mm=cal.get(Calendar.MONTH); //현재 보고있는 월
		
		c.set(yy, mm, 1); //날짜 객체를 1일로 조작
		
		int day=c.get(Calendar.DAY_OF_WEEK); //1일의 요일 구하기
		
		//System.out.println(day);
		return day;//요일 반환
	}
	
	public int getLastDateOfMonth() {
		int yy=cal.get(Calendar.YEAR);
		int mm=cal.get(Calendar.MONTH);
		
		Calendar c = Calendar.getInstance();
		c.set(yy, mm+1, 0);
		int dd=c.get(Calendar.DATE);
		
		return dd;
	}
	
	
	//날짜 숫자 출력처리 
	public void printNum() {
		//기존에 셀에 출력된 숫자 모두 지우기 
		for(int a=0;a<numCells.length; a++) {//층수
			for(int i=0;i<numCells[a].length;i++) {
				numCells[a][i].setTitle("");
				
				//아이콘 삭제하기
				numCells[a][i].iconBox.removeAll();
				
			}
		}
		
		int startDay=getStartDayOfWeek(); //해당 월이 무슨 요일부터 시작하는지 값을 얻기
		int lastDate=getLastDateOfMonth(); //해당 월이 며칠까지 있는지 그 값을 얻기
		
		System.out.println(lastDate+"까지 입니다");
		
		//각 셀에 알맞는 숫자 채우기
		int count=0; //셀의 순번을 체크하기 위한 변수
		int num=0; //실제 날짜를 담당할 변수
		
		for(int a=0;a<numCells.length;a++) {
			for(int i=0;i<numCells[a].length;i++) {
				count++;
				if(count>=startDay && num<lastDate) {
					num++;
					
					String value="";
					
					for(int k=0;k<planList.size();k++) {
						Plan plan=planList.get(k);
						
						//연, 월, 일이 일치한다면 알맞는 출력
						int yy=cal.get(Calendar.YEAR);
						int mm=cal.get(Calendar.MONTH)+1;
						
						if(yy==plan.getYy() && mm==plan.getMm() && num==plan.getDd()) {
							value+=Integer.toString(num)+"\n 메모발견";
						}
					}	
					value+=Integer.toString(num);
					numCells[a][i].setTitle(value);
				}
			}
		}
	}

	public void login() {
		//인증이 성공되면 DTO 반환
		
		//현아씨가 자료를 공유안해서 억지로 테스트하자 
		
		Client client = new Client();
		client.setId("darae");
		client.setClient_idx(1);
		
		
		//client=clientDAO.loginCheck(dto);
	
	}
	
	//로그인한 회원이 보유한 일정 가져오기
	public void getPlanList() {
		int yy=cal.get(Calendar.YEAR);//yy
		int mm=cal.get(Calendar.MONTH);//mm
		int dd=cal.get(Calendar.DATE); //dd
		
		Plan plan = new Plan(); //empty	
		plan.setClient(mainFrame.client);
		plan.setYy(yy);
		plan.setMm(mm+1);
		plan.setDd(dd);
		
		planList=planDAO.selectAll(plan);
		System.out.println("등록된 일정의 수는 "+planList.size());
		
		
		//System.out.println(mainFrame.client);
	}

	
	
}









