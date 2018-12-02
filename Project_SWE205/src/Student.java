
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

	private String name;
	private String password;
	private int ID;
	private double GPA;
	private String major;
	private String email;
	private String phoneNumber;
	private ArrayList<Integer> coursesTaken;
	private ArrayList<Integer> currentCourses;
	private int creditHours;
	private int maxHours;
	
	
	
	public Student[] getStudent() throws ClassNotFoundException {
		String fileName = "dataBase_Students.bin";
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
			ArrayList<Student> aa;
			aa = (ArrayList<Student>) is.readObject();
			Student[] list = new Student[aa.size()];
			for (int i = 0; i < aa.size(); i++) {
				list[i] = aa.get(i);
			}

			is.close();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Student[] list = null;
		return list;
	}
	
	public Course[] getCourse() throws ClassNotFoundException {
		String fileName = "dataBase_Courses.bin";
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
			ArrayList<Course> aa;
			aa = (ArrayList<Course>) is.readObject();
			Course[] list = new Course[aa.size()];
			for (int i = 0; i < aa.size(); i++) {
				list[i] = aa.get(i);
			}

			is.close();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Course[] list = null;
		return list;
	}
	public int addCourse(int ID,int CRN) throws ClassNotFoundException {
		Student[] listStudent = getStudent();
		Course[] listCourse = getCourse();

		int indexOfStudent = 0;
		int indexOfCourse = 0;

		for (int i =0 ; i< listCourse.length ; i++) {
			if (listCourse[i].getCRN() == CRN) {
				indexOfCourse = i;
				break;
			}
		}
		
		for (int i =0 ; i< listStudent.length ; i++) {
			if (listStudent[i].ID == ID) {
				indexOfStudent = i;
				break;
			}
		}
		
		if(checkCredit(listStudent[indexOfStudent], listCourse[indexOfCourse])) 
			return 101; // maximum credit mistake num
		
		else if (checkState(listStudent[indexOfStudent], listCourse[indexOfCourse]))
			return 102; // section close
		
		else if (checkPreRequst(listStudent[indexOfStudent], listCourse[indexOfCourse]))
			return 103; // did not take all the pre requst
		
		else if (checkCoursesTaken(listStudent[indexOfStudent], listCourse[indexOfCourse]))
			return 104; // course was taken befor
		
		else if (checkTime(listStudent[indexOfStudent], listCourse[indexOfCourse]))
			return 105; // time confilct
		
		return CRN;
		
		
	}
	
	
	public boolean checkCredit(Student student, Course course) {
		if (student.creditHours + course.getCredit() > student.maxHours)
			return true;
		return false;
	}
	
	public boolean checkState(Student student, Course course) {
		if (!(course.getState()))
			return true;
		return false;
	}
	
	public boolean checkPreRequst(Student student, Course course) {
		ArrayList<Integer> preRequst = new ArrayList<Integer>();
		preRequst = course.getPreRequest();
		boolean found = false;
		for ( int i = 0 ; i < preRequst.size() ; i++) {
			for ( int j =0 ; j < student.coursesTaken.size() ; j++) {
				if (preRequst.get(i) == student.coursesTaken.get(j)) {
					found = true;
					break; 
				}
			}
			if(!found)
				return true;
			
		}
		return false;
	}
	
	
	public boolean checkCoursesTaken(Student student, Course course) {
		for ( int i = 0 ; i < coursesTaken.size() ; i++) 
			if (coursesTaken.get(i) == course.getCRN()) 
				return false;
		
		return true;
	}
	
	public boolean checkTime(Student student, Course course) {
		///************************
		return true;
	}
	// الميثود المطلوبة
	/*
	 * public void addStudent (int ID,int SRN) { numOfStudent++;
	 * studentTakeThisCourse.add(ID); if (numOfStudent == maxNumOfStudent) isOpen =
	 * false; }
	 */

	// الي تحت استخدمتها عشان اضيف للفايل
	public Student(String name, String password, int ID, double GPA, String major, String email, String phoneNumber,
			ArrayList<Integer> coursesTaken, ArrayList<Integer> currentCourses, int creditHours, int maxHours) {
		super();
		this.name = name;
		this.password = password;
		this.ID = ID;
		this.GPA = GPA;
		this.major = major;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.coursesTaken = coursesTaken;
		this.currentCourses = currentCourses;
		this.creditHours = creditHours;
		this.maxHours = maxHours;

	}

	/*
	 * try { ObjectInputStream is = new ObjectInputStream(new
	 * FileInputStream(fileName)); ArrayList<Student> aa ; aa = (ArrayList<Student>)
	 * is.readObject(); Student[] xx = new Student[aa.size()]; for(int i =0 ; i<
	 * aa.size(); i++) { xx[i] = aa.get(i); // System.out.println(xx[i].getInfo());
	 * } int id = 2016111110; for (int i =0 ; i < xx.length ; i++) { if (xx[i].) }
	 * is.close();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 */

	// تتعدل بعدين
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getGPA() {
		return GPA;
	}

	public void setGPA(double gPA) {
		GPA = gPA;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<Integer> getCoursesTaken() {
		return coursesTaken;
	}

	public void setCoursesTaken(ArrayList<Integer> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}

	public ArrayList<Integer> getCurrentCourses() {
		return currentCourses;
	}

	public void setCurrentCourses(ArrayList<Integer> currentCourses) {
		this.currentCourses = currentCourses;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	public int getMaxHours() {
		return maxHours;
	}

	public void setMaxHours(int maxHours) {
		this.maxHours = maxHours;
	}
	
	
	public String getInfo() {
		return "";
	}

	public int getID() {
		return this.ID;
	}
}
