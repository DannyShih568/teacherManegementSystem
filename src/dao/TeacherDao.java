package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.PageBean;
import model.Teacher;
import util.StringUtil;

public class TeacherDao {

	
	public ResultSet teacherList(Connection con,PageBean pageBean, Teacher teacher)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_teacher");
		if(StringUtil.isNotEmpty(Teacher.gettName())){
			sb.append(" and tName like '%"+Teacher.gettName()+"%'");
		}
		
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int teacherCount(Connection con)throws Exception{
		String sql="select count(*) as total from t_teacher";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	

	public int teacherDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_teacher where tid "+delIds+"";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	public int teacherAdd(Connection con,Teacher teacher)throws Exception{
		String sql="insert into t_teacher values(null,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, Teacher.gettName());
		pstmt.setString(2, teacher.gettSex());
		pstmt.setString(3, teacher.gettPhone());
		pstmt.setString(4, teacher.gettSchool());
		return pstmt.executeUpdate();
	}
	
	public int teacherModify(Connection con,Teacher teacher)throws Exception{
		String sql="update t_teacher set tName=?,tSex=?,tPhone=?,tSchool=? where tid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, Teacher.gettName());
		pstmt.setString(2, teacher.gettSex());
		pstmt.setString(3, teacher.gettPhone());
		pstmt.setString(4, teacher.gettSchool());
		pstmt.setInt(5, teacher.gettID());
		return pstmt.executeUpdate();
	}
	
}
