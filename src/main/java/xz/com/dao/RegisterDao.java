package xz.com.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xz.com.bean.TopAndDown;
import xz.com.bean.UserInfo;
import xz.com.utils.SpringUtils;
@Repository
public class RegisterDao {

	private JdbcTemplate jdbct=(JdbcTemplate)SpringUtils.getBean("jdbcTemplate");
	
	
	public boolean isUserRepeat(String username){
		String sql="select count(*)  from  user_info where user_info.username="+"'"+username+"';";
		int result =jdbct.queryForInt(sql);
		System.out.println("repeat number:"+result);
		return (result==0)?false:true;
	}
	
	
	public boolean verifyUserInfo(String username,String password){
		String sql = "select password from user_info where user_info.username=?";
		//, username, java.lang.String.class
		String pwd= jdbct.queryForObject("select password from user_info where user_info.username=?",new Object[]{username},java.lang.String.class);
		
		//
		return password.compareTo(pwd)==0?true:false;

		
	}
	
	@Transactional
	public void insertUserInfo(final UserInfo info){
		String sql="insert into user_info values (?,?,?)";
				  int count = jdbct.update(sql, new PreparedStatementSetter() {  
					public void setValues(java.sql.PreparedStatement pstmt) throws SQLException {
						  pstmt.setObject(1, info.getMail());  	
						  pstmt.setObject(2, info.getUsername());  						
						  pstmt.setObject(3, info.getPassword());  						

					}});  
				  
	}
	
}
