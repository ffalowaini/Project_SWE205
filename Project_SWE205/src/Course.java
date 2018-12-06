
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{

	private int CRN;
	private String name;
	private int credit;
	private String day;
	private String instructor;
	private int maxNumOfStudent;
	private int numOfStudent;
	private int sectionNum;
	private ArrayList<Integer> studentTakeThisCourse;
	private ArrayList<Integer> preRequest;
/**/private String time;  
/**/private boolean state;  
	//private Exam[] exams;
	
	public void addStudent (int ID) {
		numOfStudent++;
		studentTakeThisCourse.add(ID);
		if (numOfStudent == maxNumOfStudent)
			state = false;
	}
	
	
	public void deleteStudent (int ID) {
		numOfStudent--;
		studentTakeThisCourse.remove(ID);
		if (numOfStudent < maxNumOfStudent)
			state = true;
	}
	
	public Course(int CRN, String name, int credit, String day, String instructor, int maxNumOfStudent,
			int numOfStudent, int sectionNum, ArrayList<Integer> studentTakeThisCourse, ArrayList<Integer> preRequest, String time,
			boolean state) {
		this.CRN = CRN;
		this.name = name;
		this.credit = credit;
		this.day = day;
		this.instructor = instructor;
		this.maxNumOfStudent = maxNumOfStudent;
		this.numOfStudent = numOfStudent;
		this.sectionNum = sectionNum;
		this.studentTakeThisCourse = studentTakeThisCourse;
		this.preRequest = preRequest;
		this.time = time;
		this.state = state;
	}
	
	
	public int getCRN() {
		return CRN;
	}

	public void setCRN(int cRN) {
		CRN = cRN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getMaxNumOfStudent() {
		return maxNumOfStudent;
	}

	public void setMaxNumOfStudent(int maxNumOfStudent) {
		this.maxNumOfStudent = maxNumOfStudent;
	}

	public int getNumOfStudent() {
		return numOfStudent;
	}

	public void setNumOfStudent(int numOfStudent) {
		this.numOfStudent = numOfStudent;
	}

	public int getSectionNum() {
		return sectionNum;
	}

	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}

	public ArrayList<Integer> getStudentTakeThisCourse() {
		return studentTakeThisCourse;
	}

	public void setStudentTakeThisCourse(ArrayList<Integer> studentTakeThisCourse) {
		this.studentTakeThisCourse = studentTakeThisCourse;
	}

	public ArrayList<Integer> getPreRequest() {
		return preRequest;
	}

	public void setPreRequest(ArrayList<Integer> preRequest) {
		this.preRequest = preRequest;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	public String getInfo() {
		return CRN + " " + name + " " + sectionNum ; 
	}
	
}
