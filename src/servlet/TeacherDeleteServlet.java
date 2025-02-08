package servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import dao.TeacherDao;
import model.Teacher;
import model.PageBean;
import util.DbUtil;
import util.JsonUtil;
import util.ResponseUtil;


public class TeacherDeleteServlet extends HttpServlet{

	DbUtil dbUtil=new DbUtil();
	TeacherDao teacherDao=new TeacherDao();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String delIds=request.getParameter("delIds");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int delNums=teacherDao.teacherDelete(con, delIds);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "É¾³ýÊ§°Ü");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
