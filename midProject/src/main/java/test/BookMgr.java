package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import likefav.LikeFavDTO;
import myReply.ReplyBean;

public class bookMgr {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds;

	public bookMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결 실패 : " + e.getMessage());
		}
	}
	//리뷰쓴 책 가져오기
	public BookDTO getBook(int no){
		BookDTO dto = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from book where bnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BookDTO();
				dto.setBnum(rs.getInt("bnum"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setThumb_nail(rs.getString("thumb_nail"));
				dto.setPyear(rs.getInt("pyear"));
			}
		} catch (Exception e) {
			System.out.println("getBook err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	
	//찜한 특정 도서 (likeFav.jsp)
	public BookDTO getfavBook(String id){
		BookDTO dto = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT b.thumb_nail,b.bnum,b.title FROM like_fav lf JOIN reply r ON lf.like_reply_no = r.reply_no"
					+ " JOIN book b ON r.reply_book_no = b.bnum WHERE lf.fav_user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BookDTO();
				dto.setBnum(rs.getInt("bnum"));
				dto.setTitle(rs.getString("title"));
				dto.setThumb_nail(rs.getString("Thumb_nail"));
			}
		} catch (Exception e) {
			System.out.println("getfavBook err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	
	
}
