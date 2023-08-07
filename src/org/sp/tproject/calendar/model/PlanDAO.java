package org.sp.tproject.calendar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.sp.tproject.calendar.domain.Plan;
import org.sp.tproject.main.view.MainFrame;

import util.DBManager;

public class PlanDAO {
	DBManager dbManager;
	MainFrame mainFrame;
	
	
	public PlanDAO(DBManager dbManager) {
		this.dbManager=dbManager;
	}
	
	//일정 등록하기
	public int insert(Plan plan) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null; //insert에 의해 발생되는 pk값을 가져오기 위해
		int result=0;
		
		con=dbManager.connect();
		String sql="insert into plan(plan_idx, yy, mm, dd, diary_title, diary_content, filename, client_idx)";
		sql+=" values(seq_plan.nextval, ?,?,?,?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, plan.getYy());
			pstmt.setInt(2, plan.getMm());
			pstmt.setInt(3, plan.getDd());
			pstmt.setString(4, plan.getDiary_title());
			pstmt.setString(5, plan.getDiary_content());
			pstmt.setString(6, plan.getFilename());
			
			pstmt.setInt(7, plan.getClient().getClient_idx());
			
			result=pstmt.executeUpdate(); //DML 실행

			//insert가 완료되면 planDTO 안의 plan_idx값을
			// 방금 들어간 시퀀스 값으로 대입!
			
			//현재 나에 의해 즉, 내가 발생시킨 최신 시퀀스만을 얻어오기
			sql="select seq_plan.currval as plan_idx from dual";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			//rs가 보유한 최신 시퀀스 값을 꺼내기
			if(rs.next()) {//존재한다면
				
				//plan_idx가 0이었던 상태에서 드디어 시퀀스 값으로 대체되는 시점
				plan.setPlan_idx(rs.getInt("plan_idx"));
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return result;
	}
	
	//달력의 날짜와 일치하는 일정 가져오기
	public List selectAll(Plan plan) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list=null;
		
		con = dbManager.connect();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from plan where yy=? and mm=? and dd=? and diary_title=? and diary_content=? and filename=? and client_idx=?");

		System.out.println(sb.toString());
		
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, plan.getYy());
			pstmt.setInt(2, plan.getMm());
			pstmt.setString(0, null);
			pstmt.setInt(1, plan.getClient().getClient_idx());
			
			rs=pstmt.executeQuery();
			/*while() {
				
			}*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return list;
	}
}








