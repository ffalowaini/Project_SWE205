

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    Student() {

    }
    // in this method we read the student object from the file and then we add it to student list
    Student[] getStudent() throws ClassNotFoundException {
        String fileName = "dataBase_Students.bin";
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            ArrayList<Student> studentObject;
            studentObject = (ArrayList<Student>) is.readObject();
            Student[] list = new Student[studentObject.size()];
            for (int i = 0; i < studentObject.size(); i++) {
                list[i] = studentObject.get(i);
            }

            is.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
// we update the file which contains the information of students
    private void writeStudent(Student[] listStudent) {
        String fileName = "dataBase_Students.bin";
        ArrayList<Student> dataBase_Student = new ArrayList<>(Arrays.asList(listStudent));
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(dataBase_Student);
            os.close();
        } catch (IOException ignored) {

        }
    }

    // in this method we read the course object from the file and then we add it to course list

    Course[] getCourse() throws ClassNotFoundException {
        String fileName = "dataBase_Courses.bin";
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            ArrayList<Course> courseObject;
            courseObject = (ArrayList<Course>) is.readObject();
            Course[] list = new Course[courseObject.size()];
            for (int i = 0; i < courseObject.size(); i++) {
                list[i] = courseObject.get(i);
            }

            is.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Course[] list = null;
        return list;
    }

 // we update the file which contains the information of Courses

    private void writeCourse(Course[] listCourse) {
        String fileName = "dataBase_Courses.bin";
        ArrayList<Course> dataBase_Course = new ArrayList<>(Arrays.asList(listCourse));
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(dataBase_Course);
            os.close();
        } catch (IOException ignored) {

        }
    }

// we check if the student is allowed to add the chosen course.
    
    public int addCourse(int ID, int CRN) throws ClassNotFoundException {
        System.out.println(CRN);
        Student[] listStudent = getStudent();
        Course[] listCourse = getCourse();
        boolean found = false;
        int indexOfStudent = 0;
        int indexOfCourse = 0;

        for (int i = 0; i < listCourse.length; i++) {
            if (listCourse[i].getCRN() == CRN) {
                indexOfCourse = i;
                found = true;
                break;
            }
        }
        if (!found)
            return 108; // invalid CRN

        for (int i = 0; i < listStudent.length; i++) {
            if (listStudent[i].ID == ID) {
                indexOfStudent = i;
                break;
            }
        }

        if (checkCredit(listStudent[indexOfStudent], listCourse[indexOfCourse]))
            return 101; // maximum credit

        else if (checkState(listStudent[indexOfStudent], listCourse[indexOfCourse]))
            return 102; // section closed

        else if (checkPreRequst(listStudent[indexOfStudent], listCourse[indexOfCourse]))
            return 103; // did not take all the prerequisite

        else if (checkCoursesTaken(listStudent[indexOfStudent], listCourse[indexOfCourse]))
            return 104; // course was taken before

        else if (checkTime(listStudent[indexOfStudent], listCourse[indexOfCourse], listCourse)) {
            return 105; // time conflict
        }
        else if (checkCurrentCourse(listStudent[indexOfStudent], listCourse[indexOfCourse])) {
            return 107; // courses was added
        }
        else {
                listCourse[indexOfCourse].addStudent(ID);
                listStudent[indexOfStudent].currentCourses.add(CRN);
                listStudent[indexOfStudent].creditHours += listCourse[indexOfCourse].getCredit();
                writeStudent(listStudent);
                writeCourse(listCourse);
                return 106;
            }
        }




// the course will be deleted from the student by using this method 
    public boolean deleteCourse(int ID, int CRN) throws ClassNotFoundException {
        Student[] listStudent = getStudent();
        Course[] listCourse = getCourse();

        int indexOfStudent = 0;
        int indexOfCourse = 0;
        boolean isDeleted = false;
        for (int i = 0; i < listCourse.length; i++) {
            if (listCourse[i].getCRN() == CRN) {
                indexOfCourse = i;
                break;
            }
        }

        for (int i = 0; i < listStudent.length; i++) {
            if (listStudent[i].ID == ID) {
                indexOfStudent = i;
                break;
            }
        }

        for (int i = 0; i < listStudent[indexOfStudent].currentCourses.size(); i++) {
            if (CRN == listStudent[indexOfStudent].currentCourses.get(i))
                isDeleted = true;
        }

        if (isDeleted) {
            listCourse[indexOfCourse].deleteStudent(ID);
            for (int i =0 ; i < listStudent[indexOfStudent].currentCourses.size(); i++)
                if (CRN == listStudent[indexOfStudent].currentCourses.get(i))
                     listStudent[indexOfStudent].currentCourses.remove(i);
            listStudent[indexOfStudent].creditHours -= listCourse[indexOfCourse].getCredit();
            writeStudent(listStudent);
            writeCourse(listCourse);
            return true;
        } else
            return false;
    }


    public void searchCourse(int ID, int CRN) throws ClassNotFoundException {

    }


    public void approvalCourse(String massege, int CRN) throws ClassNotFoundException {

    }

    //this method to check if the student reach the maximum hours or not
    public boolean checkCredit(Student student, Course course) {
        if (student.creditHours + course.getCredit() > student.maxHours)
            return true;
        return false;
    }
    
    
    //this method to check for the course is it open or close 
    public boolean checkState(Student student, Course course) {
        if (!(course.getState()))
            return true;
        return false;
    }
    
    //this method to check the all prerequisite courses for a certain course .. if the student took it or not
    public boolean checkPreRequst(Student student, Course course) {
        ArrayList<Integer> preRequst = new ArrayList<Integer>();
        preRequst = course.getPreRequest();
        boolean found = false;
        for (int i = 0; i < preRequst.size(); i++) {
            for (int j = 0; j < student.coursesTaken.size(); j++) {
                if (preRequst.get(i) == student.coursesTaken.get(j)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return true;

        }
        return false;
    }

    //this method to check all the courses taken by a student
    public boolean checkCoursesTaken(Student student, Course course) {
        for (int i = 0; i < student.coursesTaken.size(); i++)
            if (student.coursesTaken.get(i) == course.getCRN()){
                System.out.println(student.coursesTaken.get(i));
                System.out.println(course.getCRN());
                return true;
    }
        return false;
    }
    
    //this method to check the courses taken by a student in the current term
    public boolean checkCurrentCourse(Student student, Course course) {
        for (int i = 0; i < student.currentCourses.size(); i++)
            if (student.currentCourses.get(i) == course.getCRN())
                return true;

        return false;
    }
    
    //this method to check the time conflict
    public boolean checkTime(Student student, Course course, Course[] coursesArray) {
        String time = course.getTime();
        String day = course.getDay();

        for (int i = 0; i < student.getCurrentCourses().size(); i++) {

            int indexInCourseArray = 0;
            for (int z = 0; z < coursesArray.length; z++) {
                if (student.getCurrentCourses().get(i) == coursesArray[z].getCRN()) {
                    indexInCourseArray = i;
                    break;
                }


                if (day.equals(coursesArray[indexInCourseArray].getDay()))
                    if (time.equals(coursesArray[indexInCourseArray].getTime()))
                        return true;

            }
        }


        return false;
    }


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
