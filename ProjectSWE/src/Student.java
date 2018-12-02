import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable{

	private String name;
	private String password;
	private String ID;
	private double GPA;
	private String major;
	private String email;
	private String phoneNumber;
	private ArrayList<Integer> coursesTaken;
	private ArrayList<Integer> currentCourses;
	private int creditHours;
	private int maxHours;
	//ÇáãíËæÏ ÇáãØáæÈÉ
	/*public void addStudent (int ID,int SRN) {
		numOfStudent++;
		studentTakeThisCourse.add(ID);
		if (numOfStudent == maxNumOfStudent)
			isOpen = false;
	}*/
	
	//Çáí ÊÍÊ ÇÓÊÎÏãÊåÇ ÚÔÇä ÇÖíİ ááİÇíá
	public Student (String name, String password, String ID, double GPA, String major,
			String email, String phoneNumber, ArrayList<Integer> coursesTaken, ArrayList<Integer> currentCourses ,int creditHours , int maxHours) {
		super();
		this.name=name;
		this.password=password;
		this.ID=ID;
		this.GPA=GPA;
		this.major=major;
		this.email=email;
		this.phoneNumber=phoneNumber;
		this.coursesTaken=coursesTaken;
		this.currentCourses=currentCourses;
		this.creditHours=creditHours;
		this.maxHours=maxHours;
		
		
	}
	
	//ÊÊÚÏá ÈÚÏíä
	public String getInfo() {
		return "" ; 
	}
	
}
