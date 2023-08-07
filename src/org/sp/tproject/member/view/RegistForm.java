package org.sp.tproject.member.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.sp.tproject.calendar.domain.Client;
import org.sp.tproject.calendar.model.ClientDAO;

import util.DBManager;
import util.HashConverter;
import util.MailSender;

public class RegistForm extends JFrame{
	//이름, 아이디, 비밀번호, 비밀번호 확인, 닉네임, 이메일 주소, 가입하기(버튼)
	JPanel regist_p_name;
	JLabel regist_la_name;
	JTextField regist_t_name;
	
	JPanel regist_p_id;
	JLabel regist_la_id;
	JTextField regist_t_id;
	JButton regist_bt_id;	//아이디 중복확인 버튼
	
	JPanel regist_p_pass;
	JLabel regist_la_pass;
	JPasswordField regist_t_pass;
	
	JPanel regist_p_pass_check;
	JLabel regist_la_pass_check;
	JPasswordField regist_t_pass_check;
	JLabel regist_la_pass_check_info;	//빨간색으로 안내 메세지
	
	JPanel regist_p_nickname;
	JLabel regist_la_nickname;
	JTextField regist_t_nickname;
	JButton regist_bt_nickname;
	
	JPanel regist_p_email;
	JLabel regist_la_email;
	JTextField regist_t_email;
	JLabel regist_la_email_link;
	JComboBox regist_t_email_domain;
	
	Boolean regist_bt_flag=false;	//가입버튼 활성화, 비활성화 여부(처음은 비활성화
	JButton regist_bt;	//가입 버튼
	
	//DB 관련
	DBManager dbManager;
	ClientDAO clientDAO;
	HashConverter hashConverter;	//비번
	MailSender mailSender;	//메일	

	
	

	public RegistForm() {
		regist_p_name = new JPanel();
		regist_la_name = new JLabel("이름");
		regist_t_name = new JTextField();
		
		regist_p_id = new JPanel();
		regist_la_id = new JLabel("아이디");
		regist_t_id = new JTextField();
		regist_bt_id =new JButton("중복확인");
		
		regist_p_pass = new JPanel();
		regist_la_pass = new JLabel("비밀번호");
		regist_t_pass = new JPasswordField();
		
		regist_p_pass_check = new JPanel();
		regist_la_pass_check = new JLabel("비밀번호 확인");
		regist_t_pass_check = new JPasswordField();
		regist_la_pass_check_info = new JLabel("");
		
		regist_p_nickname = new JPanel();
		regist_la_nickname = new JLabel("닉네임");
		regist_t_nickname = new JTextField();
		regist_bt_nickname = new JButton("중복확인");
		
		regist_la_email = new JLabel();
		
		regist_p_email = new JPanel();
		regist_t_email = new JTextField("이메일을 입력하세요");
		regist_la_email_link = new JLabel("@");
		regist_t_email_domain = new JComboBox();
		
		//DB 관련
		dbManager = new DBManager();
		clientDAO = new ClientDAO(dbManager);
		//비밀번호 hash 필요
		hashConverter = new HashConverter();
		mailSender = new MailSender();
		
		
		//이메일 도메인 선택 박스
		regist_t_email_domain.addItem("선택하세요");
		regist_t_email_domain.addItem("google.com");
		regist_t_email_domain.addItem("naver.com");
		regist_t_email_domain.addItem("daum.net");
		
		regist_bt = new JButton("가입하기");
		//폰트
		Font regist_la_font = new Font("goyang", Font.PLAIN, 20);
		Font regist_bt_font = new Font("goyang", Font.BOLD, 20);
		Font regist_info_font = new Font("goyang", Font.BOLD, 18);
		regist_la_name.setFont(regist_la_font);
		regist_t_name.setFont(regist_la_font);
		regist_la_id.setFont(regist_la_font);
		regist_t_id.setFont(regist_la_font);
		regist_la_pass.setFont(regist_la_font);
		regist_t_pass.setFont(regist_la_font);
		regist_la_pass_check.setFont(regist_la_font);
		regist_t_pass_check.setFont(regist_la_font);
		regist_la_pass_check_info.setFont(regist_info_font);
		regist_la_pass_check_info.setForeground(Color.red);
		regist_la_nickname.setFont(regist_la_font);
		regist_t_nickname.setFont(regist_la_font);
		regist_t_email.setFont(regist_la_font);
		regist_la_email_link.setFont(regist_la_font);
		
		regist_t_email_domain.setFont(regist_la_font);
		
		regist_bt.setFont(regist_bt_font);
		
		//디자인
		setLayout(new FlowLayout());
		Dimension regist_panel_d = new Dimension(450,80);
		Dimension regist_label_d = new Dimension(150,50);
		Dimension regist_text_d = new Dimension(200,50);
		Dimension regist_info_d = new Dimension(200,30);
		regist_p_name.setPreferredSize(regist_panel_d);
		regist_p_name.setLayout(new FlowLayout(FlowLayout.LEFT));
		regist_p_name.setAlignmentX(LEFT_ALIGNMENT);
		regist_la_name.setPreferredSize(regist_label_d);
		regist_t_name.setPreferredSize(regist_text_d);
		
		regist_p_id.setPreferredSize(regist_panel_d);
		regist_p_id.setLayout(new FlowLayout(FlowLayout.LEFT));
		regist_la_id.setPreferredSize(regist_label_d);
		regist_t_id.setPreferredSize(regist_text_d);
		
		regist_p_pass.setPreferredSize(regist_panel_d);
		regist_p_pass.setLayout(new FlowLayout(FlowLayout.LEFT));
		regist_la_pass.setPreferredSize(regist_label_d);
		regist_t_pass.setPreferredSize(regist_text_d);
		
		regist_p_pass_check.setPreferredSize(regist_panel_d);
		regist_p_pass_check.setLayout(new FlowLayout(FlowLayout.LEFT));
		regist_la_pass_check.setPreferredSize(regist_label_d);
		regist_t_pass_check.setPreferredSize(regist_text_d);
		regist_la_pass_check_info.setPreferredSize(regist_info_d);
		
		regist_p_nickname.setPreferredSize(regist_panel_d);
		regist_p_nickname.setLayout(new FlowLayout(FlowLayout.LEFT));
		regist_la_nickname.setPreferredSize(regist_label_d);
		regist_t_nickname.setPreferredSize(regist_text_d);
		
		regist_p_email.setPreferredSize(regist_panel_d);
		regist_p_email.setLayout(new FlowLayout(FlowLayout.LEFT));
		regist_t_email.setPreferredSize(regist_text_d);
		regist_la_email_link.setPreferredSize(new Dimension(30,50));
		regist_t_email_domain.setPreferredSize(regist_text_d);
		
		regist_bt.setPreferredSize(regist_label_d);
		
		//조립
		add(regist_p_name);
		regist_p_name.add(regist_la_name);
		regist_p_name.add(regist_t_name);
		add(regist_p_id);
		regist_p_id.add(regist_la_id);
		regist_p_id.add(regist_t_id);
		regist_p_id.add(regist_bt_id);
		add(regist_p_pass);
		regist_p_pass.add(regist_la_pass);
		regist_p_pass.add(regist_t_pass);
		add(regist_p_pass_check);
		regist_p_pass_check.add(regist_la_pass_check);
		regist_p_pass_check.add(regist_t_pass_check);
		regist_p_pass_check.add(regist_la_pass_check_info);
		add(regist_p_nickname);
		regist_p_nickname.add(regist_la_nickname);
		regist_p_nickname.add(regist_t_nickname);
		regist_p_nickname.add(regist_bt_nickname);
		
		add(regist_p_email);
		regist_p_email.add(regist_t_email);
		regist_p_email.add(regist_la_email_link);
		regist_p_email.add(regist_t_email_domain);
		

		add(regist_bt);
		regist_bt.setEnabled(regist_bt_flag);	//처음에는 버튼 비활성화
		
		
		//전체 프레임 사이즈
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		

			
		regist_t_pass_check.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
//				int key = e.getKeyCode();
//				if(key == KeyEvent.VK_ENTER) {
//					
//				}
				passCheck();
			}
		});
//		//이름 칸에 마우스 올리면 텍스트 필드 초기화
//		regist_t_name.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//			}
//		});
		//이름 칸에 작성 글자 수가 일정 수준 넘으면 가입 버튼 활성화
		regist_t_name.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				registBtEnable();
				int key = e.getKeyCode();
				//만약 이름 칸을 수정해서 조건을 만족시키지 못하면 가입 버튼 비활성화
				if(key == KeyEvent.VK_BACK_SPACE) {
					registBtDisable();
				}
			}
		});
		//아이디 칸에 작성 글자 수가 일정 수준 넘으면 가입 버튼 활성화
		regist_t_id.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				registBtEnable();
				int key = e.getKeyCode();
				//만약 아이디 칸을 수정해서 조건을 만족시키지 못하면 가입 버튼 비활성화
				if(key == KeyEvent.VK_BACK_SPACE) {
					registBtDisable();
					System.out.println(regist_t_id.getText().length());
				}
				
				
			}
		});
		//아이디 중복 체크 버튼을 눌렀을 때..
		regist_bt_id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean idcheck =clientDAO.idDuplicateCheck(regist_t_id.getText());
				//idcehck 가 true 라면.. 
				//아이디가 중복된다면
				if(idcheck) {
					JOptionPane.showMessageDialog(RegistForm.this, "이미 사용 중인 아이디 입니다.");
					regist_t_id.setText("");
				}else {
					JOptionPane.showMessageDialog(RegistForm.this, "사용할 수 있는 아이디 입니다.");
				}
			}
		});
		//이메일 칸에 마우스 올리면 텍스트 필드 초기화
		regist_t_email.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				regist_t_email.setText("");
			}
		});
		//패스워드 칸에 작성 글자 수가 일정 수준 넘으면 가입 버튼 활성화
		regist_t_pass.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				registBtEnable();
				int key = e.getKeyCode();
				//만약 패스워드 칸을 수정해서 조건을 만족시키지 못하면 가입 버튼 비활성화
				if(key == KeyEvent.VK_BACK_SPACE) {
					registBtDisable();
					System.out.println(regist_t_id.getText().length());
				}

			}
		});
		//패스워드 체크 칸에 작성 글자 수가 일정 수준 넘으면 가입 버튼 활성화
		regist_t_pass_check.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				registBtEnable();
			}
		});
		
		//닉네임 칸에 작성 글자 수가 일정 수준 넘으면 가입 버튼 활성화
		regist_t_nickname.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				registBtEnable();
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_BACK_SPACE) {
					registBtDisable();
				}
			}
		});
		//닉네임 중복 체크 버튼을 눌렀을 때...
		regist_bt_nickname.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				boolean nicknamecheck = clientDAO.nicknameDuplicateCheck(regist_t_nickname.getText());
				//nicknamecheck 가 true 라면...
				//닉네임이 중복된다면
				if(nicknamecheck) {
					JOptionPane.showMessageDialog(RegistForm.this, "이미 사용 중인 닉네임 입니다.");
					regist_t_nickname.setText("");
				}else {
					JOptionPane.showMessageDialog(RegistForm.this, "사용할 수 있는 닉네임 입니다.");
					
				}
				
			}
		});
		
		//이메일 칸에 마우스 올리면 텍스트 필드 초기화
		regist_t_email.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//System.out.println(regist_t_email.getText());
				//실패.. 확인 필요
				if(regist_t_email.getText() == "이메일을 입력하세요") {
					regist_t_email.setText("");
					System.out.println("초기 이메일 입력");
				}
			}
		});
		//이메일 칸에 작성 글자 수가 일정 수준 넘으면 가입 버튼 활성화
		regist_t_email.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				registBtEnable();
				int key = e.getKeyCode();
				//만약 이메일 칸을 수정해서 조건을 만족시키지 못하면 가입 버튼 비활성화
				if(key == KeyEvent.VK_BACK_SPACE) {
					registBtDisable();
					//System.out.println(regist_t_id.getText().length());
				}

			}
		});
		//이메일 도메인 선택하면 가입 버튼 활성화
//		regist_t_email_domain.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				registBtEnable();
//				System.out.println("이메일 도메인 선택했어요");
//
//			}
//		});
		regist_t_email_domain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registBtEnable();
				
			}
		});
		
		
		regist_bt.addActionListener((e)->{
			regist();
		});
	}
	
	//비밀번호 일치 확인
	public boolean passCheck() {
		//사용자가 처음 입력한 비밀번호와 비밀번호 확인에 누름 비밀번호가 일치하는지 확인
		String pass = new String(regist_t_pass.getPassword());
		String pass_check = new String(regist_t_pass_check.getPassword());
		System.out.println(pass);
		System.out.println(pass_check);
		//비밀번호가 일치한다면..
		boolean ox = (pass == pass_check);
		//비밀번호가 일치한다면 true 반환
		System.out.println(ox);
		if(pass.equals(pass_check)) {	
			System.out.println("비밀번호 일치함");
			regist_la_pass_check_info.setText("비밀번호가 일치합니다.");
		}
		
		return ox;
		
	}
	//등록 버튼 활성화
	public void registBtEnable() {
		//이름, 아이디, 비밀번호, 비밀번호 확인, 닉네임, 이메일
		
		//이름 텍스트필드 글자수
		int name_text = regist_t_name.getText().length();	
		//아이디  텍스트필드 글자수
		int id_text = regist_t_id.getText().length();
		//패스워드 텍스트 필드 글자수
		int pass_text = new String(regist_t_pass.getPassword()).length();
		//닉네임 텍스트필드 글자수
		int nickname_text = regist_t_nickname.getText().length();
		//이메일 텍스트필드 글자수
		int email_text = regist_t_email.getText().length();
		//이메일 도메인 선택 여부
		//System.out.println(regist_t_email_domain.getSelectedItem());
		int email_domain_index = regist_t_email_domain.getSelectedIndex();
		//domain 의 selected 값을 비교해서.. 
//		String etext =  (String)regist_t_email_domain.getSelectedItem();
//		boolean email_domain_check = false;
//		if(etext == "naver.com" || etext == "google.com" || etext == "daum.net") {
//			email_domain_check = true;
//		}
		//비밀번호가 일치해야 등록 버튼 활성화
		boolean passcheck =(regist_la_pass_check_info.getText()).equals("비밀번호가 일치합니다.");
		if((name_text>2) && (id_text >3)&&(pass_text >3) && passcheck && (nickname_text >3) && (email_text > 3) && (email_domain_index>0)) {
			
			regist_bt_flag = true;
			regist_bt.setEnabled(regist_bt_flag);	//등록 버튼 활성화
		}

	}
	//등록 버튼 비활성화
	public void registBtDisable() {
		//이름, 아이디, 비밀번호, 비밀번호 확인, 닉네임, 이메일
		
		//이름 텍스트필드 글자수
		int name_text = regist_t_name.getText().length();	
		//아이디  텍스트필드 글자수
		int id_text = regist_t_id.getText().length();
		//패스워드 텍스트 필드 글자수
		int pass_text = new String(regist_t_pass.getPassword()).length();
		//닉네임 텍스트필드 글자수
		int nickname_text = regist_t_nickname.getText().length();
		//이메일 텍스트필드 글자수
		int email_text = regist_t_email.getText().length();
		
		
		if((name_text <= 1) || (id_text <= 3) ||(pass_text >3)|| (nickname_text <= 3) ||  (email_text <= 3)) {
				
			regist_bt.setEnabled(false);	//등록 버튼 비활성화
		}
	}
	public void regist() {
		//db에 가입한 사용자 정보 넣기
		//사용자가 입력한 입력폼의 내용을 1개의 DTO 에 담아서 insert 메서드 호출
		Client client = new Client();	//empty
		client.setName(regist_t_name.getText());
		client.setId(regist_t_id.getText());
		//비밀번호 대입
		client.setPass(hashConverter.convertToHash(new String(regist_t_pass.getPassword())));
		client.setNickname(regist_t_nickname.getText());
		String fullEmail = regist_t_email.getText();
		fullEmail += "@";
		fullEmail += (String)regist_t_email_domain.getSelectedItem();
		client.setEmail(fullEmail);
		System.out.println(fullEmail);
		
		int result = clientDAO.insert(client);	//insert 호출
		if(result > 0) {
			
			//이메일 추가
			boolean flag= mailSender.send(fullEmail , "가입축하", " 가입을 진심으로 축하드려요");
			if(flag) {
				JOptionPane.showMessageDialog(this, "가입완료");
			}

			System.out.println("가입 성공");
			
		}
		
	}
	

	public static void main(String[] args) {
		new RegistForm();
	}
}
