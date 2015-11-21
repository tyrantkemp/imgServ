package xz.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import xz.com.bean.Documents;
import xz.com.bean.TopAndDown;
import xz.com.utils.SpringUtils;

@Repository
public class ImgInfoDao {

	private JdbcTemplate jdbct = (JdbcTemplate) SpringUtils.getBean("jdbcTemplate");

	public List<Documents> getAllImgInfoList() {
		String sql = "select info from img_info";
		List<String> jsonlist= (List<String>) jdbct.queryForList(sql,   
                new Object[]{},  
                String.class);  
		List<Documents> doclist = new ArrayList();
		for (String jsonstr : jsonlist) {
			System.out.println(jsonstr);
			doclist.add(JSON.parseObject(jsonstr,Documents.class));
		}
		return doclist;
		
/*		List<Documents> doclist = new ArrayList();
		@SuppressWarnings("unchecked")
		List<String> programList = jdbct.query(sql,
			     new RowMapper() {
			     public Object mapRow(ResultSet rs, int num)
			       throws SQLException {
			         return JSON.toJSONString(new Documents());
			     }
			    });
			  if(programList!=null &&programList.size()>0){
				  for (String jsonstr : programList) {
					doclist.add(JSON.parseObject(jsonstr,Documents.class));
					
				}
				  
			    return doclist;
			  }else {
			   return null;
			  }
		*/
	
	}

	public Documents getImgInfoByImgID(String imgId) {
		String sql = "select info from img_info where imgid="+"'"+imgId+"';";
		@SuppressWarnings("unchecked")
		List<Documents> programList = jdbct.query(sql,
			     new RowMapper() {
			     public Object mapRow(ResultSet rs, int num)
			       throws SQLException {
			         return new Documents();
			     }
			    });
			  if(programList!=null &&programList.size()>0){
			  return programList.get(0);
			  }else {
			   return null;
			  }

	}

	
	@Transactional
	public void addImgInfo(final Documents doc) throws JsonProcessingException{
	//	Assert.isNull(doc,"´ý²åÈëimginfoÎª¿Õ");
		String sql = "insert into img_info values (?,?,?)";
		// ObjectMapper om = new ObjectMapper();  
	    // final String json = om.writeValueAsString(doc);
		final String infostr = JSON.toJSONString(doc);
		System.out.println("jsonstr:"+infostr);
		jdbct.update(sql, new PreparedStatementSetter(){
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, doc.getImgId());
				ps.setString(2, infostr);
				ps.setString(3, doc.getImageUrl());
			}	
			});
	
	}
}
