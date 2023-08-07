package org.sp.tproject.calendar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.StringManager;

//날짜를 표현할 박스 정의
public class NumCell extends Cell{
	
	DiaryPage diaryPage;
	JPanel iconBox; //아이콘들이 배치될 패널
	
	public NumCell(DiaryPage diaryPage, Color color, int width, int height) {
		super(color, width, height);
		
		this.diaryPage = diaryPage;
		iconBox = new JPanel();
		iconBox.setBackground(Color.YELLOW);
		
		add(iconBox);
		
		//라벨의 텍스트 크기 조정 
		la_title.setFont(new Font("돋움", Font.BOLD, 15));
		la_title.setPreferredSize(new Dimension(100,95));
		la_title.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		la_title.setHorizontalAlignment(JLabel.LEFT);
		la_title.setVerticalAlignment(JLabel.TOP);
		
		
		//마우스 이벤트 연결 
		this.addMouseListener(new MouseAdapter() {
			//클릭 
			public void mouseClicked(MouseEvent e) {
				int yy=diaryPage.cal.get(Calendar.YEAR);
				int mm=diaryPage.cal.get(Calendar.MONTH);
				int n=Integer.parseInt(la_title.getText());
				
				diaryPage.popup.dd=n;			
				diaryPage.popup.showPop(NumCell.this, yy+"-"+StringManager.getNumString(mm+1)+"-"+StringManager.getNumString(n));  
			}
		});	
	}
	
}







