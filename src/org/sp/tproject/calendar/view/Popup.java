package org.sp.tproject.calendar.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.sp.tproject.calendar.domain.Icon;
import org.sp.tproject.calendar.domain.Plan;

import util.RoundedButton;

//날짜 셀을 클릭하면, 상세 내용을 입력받을 수 있는 팝업창
public class Popup extends JFrame implements ActionListener{
	DiaryPage diaryPage; //각종 DAO를 참조하기 위해
	
	JLabel la_header; //날짜 제목 
	JLabel border;
	
	JLabel title2;
	JLabel memo;
	JTextField t_title;
	JTextArea area;
	//JPanel p_icon; //아이콘이 배치될 패널
	
	
	//RoundedButton next;
	//RoundedButton prev;
	
	JComboBox<ImageIcon> comboBox;
	JComboBox<ImageIcon> comboBox2;
	
	List<Icon> iconList1;
	List<Icon> iconList2;
	
	
	String[] mark= {"img/naviIcon/calendar_bookmark.png","img/naviIcon/calendar_question.png","img/naviIcon/calendar_notes.png","img/naviIcon/calendar_clip.png","img/naviIcon/calendar_heart.png","img/naviIcon/calendar_star.png"};
	String[] mood= {"img/naviIcon/calendar_happy.png","img/naviIcon/calendar_dead.png","img/naviIcon/calendar_fine.png","img/naviIcon/calendar_wow.png","img/naviIcon/calendar_sad.png","img/naviIcon/calendar_happy2.png"};
	
	RoundedButton bt;
	
	JLabel la_selected; //유저가 선택한 라벨
	JLabel mark_text;
	JLabel mood_text;
	
	NumCell numCell; //저장 버튼 누를때, 어떤 셀을 대상으로 아이콘을 반영할지를 알기위함
	
	int index; 
	int dd; //해당 셀을 클릭할 때 날짜를 전달받기 위한 멤버변수 
	
	String filename; //유저가 선택한 아이콘
	
	
	public Popup(DiaryPage diaryPage) {
		this.diaryPage=diaryPage;
			
		la_header = new JLabel("날짜 나옴");
		border = new JLabel();
		mark_text = new JLabel("mark");
		mood_text = new JLabel("mood");
		
		title2 = new JLabel("title");
		memo = new JLabel("memo");
		
		t_title = new JTextField();
		area = new JTextArea();
		bt = new RoundedButton("Save");
		
		//prev = new RoundedButton("<");
		//next = new RoundedButton(">");
		
		
		//스타일 
		la_header.setFont(new Font("yanol(reg)", Font.BOLD, 28));
		title2.setFont(new Font("yanol(reg)", Font.BOLD, 15));
		memo.setFont(new Font("yanol(reg)", Font.BOLD, 15));
		
		border.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		border.setPreferredSize(new Dimension(300, 1));
		title2.setPreferredSize(new Dimension(300, 25));
		memo.setPreferredSize(new Dimension(300, 25));
		t_title.setPreferredSize(new Dimension(300, 25));
		area.setPreferredSize(new Dimension(300, 200));
		
		setBackground(Color.WHITE);
		la_header.setBackground(Color.WHITE);
		
		comboBox = new JComboBox<ImageIcon>(createIcon());
		comboBox2 = new JComboBox<ImageIcon>(createIcon2());
		
		comboBox.addActionListener(this);
		comboBox2.addActionListener(this);
		
		setLayout(new FlowLayout());
		
		add(la_header);
		add(border);
		add(title2);
		add(t_title, BorderLayout.CENTER);
		//add(prev);
		add(memo);
		add(area, BorderLayout.CENTER);
		//add(next);
		
		
		
		add(mark_text);
		add(comboBox);
		add(mood_text);
		add(comboBox2);
		createIcon();
		add(bt);
		
		setBounds(400, 300, 350, 450);
		setVisible(true);
		setLocationRelativeTo(null);

		/*
		bt.addActionListener((e)->{
			prev();
		});	
		
		bt.addActionListener((e)->{
			next();
		});	
		*/
		
		bt.addActionListener((e)->{
			save();
		});	
		
		
		t_title.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println(t_title.getText().length());
				
				if(t_title.getText().length() >=10) {
					String msg= t_title.getText().substring(0, 10);
					t_title.setText(msg);
				}
			}
		});
		
	}
	
	//데이터베이스에 일정 등록
	public void save() {
		Plan plan = new Plan();
		
		int yy=diaryPage.cal.get(Calendar.YEAR);
		int mm=diaryPage.cal.get(Calendar.MONTH);
		int dd= diaryPage.cal.get(Calendar.DATE);
		
		String diary_title=t_title.getText();
		String diary_content=area.getText();
		
		plan.setYy(yy);
		plan.setMm(mm+1);
		plan.setDd(dd);
		plan.setDiary_title(diary_title);
		plan.setDiary_content(diary_content);
		plan.setFilename(filename);
		
		plan.setClient(diaryPage.mainFrame.client);
		
		
		int result = diaryPage.planDAO.insert(plan);
		
		if(result > 0) {

			JOptionPane.showMessageDialog(this, "등록 성공!");
			this.setVisible(false); //현재창 닫기
			diaryPage.getPlanList(); //db 불러오기
			diaryPage.printNum();
		}				
	}
	
	
	/*
	public void save() {
		URL url=ClassLoader.getSystemResource(mark[index]);
		try {
			BufferedImage buffImg = ImageIO.read(url);
			Image image=buffImg;
			image=image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			JLabel la_icon=new JLabel(new ImageIcon(image));
			numCell.iconBox.add(la_icon);
			numCell.updateUI(); //컴포넌트 새로고침
			t_title.setText("");
			
			//this.setVisible(false);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	
	//아이콘 생성 
	public ImageIcon[] createIcon() {
		iconList1 = new ArrayList<Icon>();
		
		ImageIcon[] icons=new ImageIcon[mark.length];
		
		for(int i=0;i<icons.length;i++) {
			URL url=ClassLoader.getSystemResource(mark[i]);
			try {
				BufferedImage buffImg=ImageIO.read(url);
				icons[i] = new ImageIcon(buffImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				
				Icon icon = new Icon();
				icon.setFilename(mark[i]);
				iconList1.add(icon);
				
			} catch (IOException e) { 
				e.printStackTrace();
			}			
		}
		return icons;
		
	}
	
	
	//아이콘 생성 
	public ImageIcon[] createIcon2() {
		iconList2 = new ArrayList<Icon>();
		
		ImageIcon[] icons=new ImageIcon[mood.length];
		
		for(int i=0;i<icons.length;i++) {
			URL url=ClassLoader.getSystemResource(mood[i]);
			try {
				BufferedImage buffImg=ImageIO.read(url);
				icons[i] = new ImageIcon(buffImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				
				Icon icon = new Icon();
				icon.setFilename(mood[i]);
				iconList2.add(icon);
				
			} catch (IOException e) { 
				e.printStackTrace();
			}			
		}
		return icons;
		
	}
	
	
	/*
	public void createIconback() {
		
		for(int i=0;i<mark.length;i++) {
			URL url=ClassLoader.getSystemResource(mark[i]);
			try {
				BufferedImage buffImg=ImageIO.read(url);
				Image image=buffImg;
				image=image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				
				JLabel la=new JLabel(new ImageIcon(image));
				la_icon.add(la); //리스트에 생성된 라벨 넣기 
				p_icon.add(la);
				
				la.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						la_selected=(JLabel)e.getSource();	
						index=la_icon.indexOf(la_selected); 
						System.out.println("선택된 라벨은 "+index+"번째 입니다");
					}
				});
				
			} catch (IOException e) { 
				e.printStackTrace();
			}			
		}
	}
	*/
	
	public void showPop(NumCell numCell, String header) {
		this.setVisible(true);
		la_header.setText(header);
		
		//셀에 아이콘 적용하기 
		this.numCell = numCell;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		JComboBox<ImageIcon> box=(JComboBox)e.getSource();
		
		if(box.equals(comboBox)) { //좌측이라면 
			
			//선택된 항목 가져오기
			int index = box.getSelectedIndex();
			Icon icon=iconList1.get(index);
			System.out.println(icon.getFilename());
			filename = icon.getFilename();
			
		}else if(box.equals(comboBox2)) { //우측이라면
			
			int index = box.getSelectedIndex();
			Icon icon=iconList2.get(index);
			System.out.println(icon.getFilename());
			filename = icon.getFilename();
		}
		
		
	}
	
}


















