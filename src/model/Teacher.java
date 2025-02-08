package model;

public class Teacher {

	private int tID;
	private static String tName;
	private String tSex;
	private String tPhone;
	private String tSchool;
	
	public Teacher() {
		super();
	}
	
	
	public Teacher(String tName,String tSex,String tPhone,String tSchool) {
		
		super();
		Teacher.tName = tName;
		this.tSex = tSex;
		this.tPhone = tPhone;
		this.tSchool = tSchool;
		
	}
	
	
	public int gettID() {
		return tID;
	}
	public void settID(int tID) {
		this.tID = tID;
	}
	public static String gettName() {
		return tName;
	}
	public void settName(String tName) {
		Teacher.tName = tName;
	}
	public String gettSex() {
		return tSex;
	}
	public void settSex(String tSex) {
		this.tSex = tSex;
	}
	public String gettPhone() {
		return tPhone;
	}
	public void settPhone(String tPhone) {
		this.tPhone = tPhone;
	}
	public String gettSchool() {
		return tSchool;
	}
	public void settSchool(String tSchool) {
		this.tSchool = tSchool;
	}
	
}
