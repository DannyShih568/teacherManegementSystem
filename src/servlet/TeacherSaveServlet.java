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
import util.StringUtil;


@WebServlet("/TeacherSaveServlet")
public class TeacherSaveServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	DbUtil dbUtil=new DbUtil();
	TeacherDao teacherDao=new TeacherDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String tName=request.getParameter("tName");
		String tSex=request.getParameter("tSex");
		String tPhone=request.getParameter("tPhone");
		String tSchool=request.getParameter("tSchool");
		String tid=request.getParameter("tID");
		Teacher teacher=new Teacher(tName,tSex,tPhone,tSchool);
		if(StringUtil.isNotEmpty(tid)){
			teacher.settID(Integer.parseInt(tid));
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(tid)){
				saveNums=teacherDao.teacherModify(con, teacher);
			}else{
				saveNums=teacherDao.teacherAdd(con, teacher);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				//result.put("success", "true");
				result.put("errorMsg", "±£¥Ê ß∞‹");
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
