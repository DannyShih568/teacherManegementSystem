package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import dao.TeacherDao;
import model.Teacher;
import model.PageBean;
import util.DbUtil;
import util.JsonUtil;
import util.ResponseUtil;

@WebServlet("/TeacherListServlet")
public class TeacherListServlet extends HttpServlet{

	DbUtil dbUtil=new DbUtil();
	TeacherDao TeacherDao=new TeacherDao();
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String teacherName=request.getParameter("tName");
		if(teacherName==null){
			teacherName="";
		}
		
		Teacher teacher=new Teacher();
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(TeacherDao.teacherList(con, pageBean, teacher));
			int total=TeacherDao.teacherCount(con);
			result.put("rows", jsonArray);
			result.put("total", total);
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
