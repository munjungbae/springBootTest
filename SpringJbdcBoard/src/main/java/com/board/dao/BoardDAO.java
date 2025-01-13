package com.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.board.domain.Board;

@Repository
public class BoardDAO {

	// JDBC Template 이 이미 생성되어 있기 때문에 의존성주입 요청이 필요함
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static String insert = "INSERT INTO JDBCBOARD(BOARD_NO, TITLE, CONTENT, WRITER) VALUES(JDBCBOARD_SEQ.NEXTVAL, ?, ?, ?)";
	public static String list = "SELECT * FROM JDBCBOARD WHERE BOARD_NO > 0 ORDER BY BOARD_NO DESC, REG_DATE DESC";
	public static String selectOne = "SELECT board_no, title, content, writer, reg_date FROM jdbcBoard WHERE board_no = ?";
	public static String delete = "delete from jdbcBoard where board_no = ?";
	public static String update = "UPDATE jdbcBoard SET title =?, content =? WHERE board_no = ?";

	// curd 관련된 함수 처리
	public void create(Board board) throws Exception {
		jdbcTemplate.update(insert, board.getTitle(), board.getContent(), board.getWriter());
	}

	public List<Board> list() throws Exception {
		List<Board> result = jdbcTemplate.query(list, new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setWriter(rs.getString("WRITER"));
				board.setRegDate(rs.getDate("REG_DATE"));
				return board;
			}
		});
		return result;
	}

	public Board read(Board board) throws Exception {
		List<Board> result = jdbcTemplate.query(selectOne, new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setWriter(rs.getString("WRITER"));
				board.setRegDate(rs.getDate("REG_DATE"));
				return board;
			}
		}, board.getBoardNo());
		return result.isEmpty() ? null : result.get(0);
	}

	public boolean delete(Board board) throws Exception {

		int count = jdbcTemplate.update(delete, board.getBoardNo());
		return (count == 0) ? false : true;
	}

	public int modify(Board board) throws Exception {
		return jdbcTemplate.update(update, board.getTitle(), board.getContent(), board.getBoardNo());
	}

}
