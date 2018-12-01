
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{

	private int SRN;
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
/**/private boolean isOpen;  
	//private Exam[] exams;
	
	public void addStudent (int ID,int SRN) {
		numOfStudent++;
		studentTakeThisCourse.add(ID);
		if (numOfStudent == maxNumOfStudent)
			isOpen = false;
	}
	
	public Course(int SRN, String name, int credit, String day, String instructor, int maxNumOfStudent,
			int numOfStudent, int sectionNum, ArrayList<Integer> studentTakeThisCourse, ArrayList<Integer> preRequest, String time,
			boolean isOpen) {
		this.SRN = SRN;
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
		this.isOpen = isOpen;
	}
	
	public String getInfo() {
		return SRN + " " + name + " " + sectionNum ; 
	}
	
}
