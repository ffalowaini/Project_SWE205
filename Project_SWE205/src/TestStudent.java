
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TestStudent {
	public static void main(String[] args) throws ClassNotFoundException {
		ArrayList<Student> dataBase_Students = new ArrayList<Student>(1000);
		Student[] list = new Student[30];

		ArrayList<Integer> coursesTaken = new ArrayList<Integer>();
		ArrayList<Integer> coursesTaken2016111110 = new ArrayList<Integer>();
		coursesTaken2016111110.add(20757); // ICS 102
		coursesTaken2016111110.add(20460); // ENGL 101
		coursesTaken2016111110.add(20807); // PE 101
		coursesTaken2016111110.add(20777); // ICS 201
		coursesTaken2016111110.add(20002); // MATH 101

		ArrayList<Integer> currentCourses = new ArrayList<Integer>();
		ArrayList<Integer> currentCourses2016111110 = new ArrayList<Integer>();
		currentCourses2016111110.add(20009);// MATH 102
		currentCourses2016111110.add(20706);// IAS 101
		currentCourses2016111110.add(20711);// IAS 111

		list[0] = new Student("Rashed", "12345", 2017333330, 3.9, "COE", "2017333330@gmail.com", "0553333345",
				coursesTaken, currentCourses, 15, 19);
		list[1] = new Student("Salman", "12345", 2016666660, 3.9, "COE", "2016666660@gmail.com", "0554006345",
				coursesTaken, currentCourses, 15, 19);
		list[2] = new Student("Ammar", "12345", 2016555550, 2.4, "SWE", "2016555550@gmail.com", "0554116345",
				coursesTaken, currentCourses, 12, 19);
		list[3] = new Student("Ali", "12345", 2016444440, 3.6, "ICS", "2016444440@gmail.com", "0554226345",
				coursesTaken, currentCourses, 13, 19);
		list[4] = new Student("Rakan", "12345", 2016333330, 2.5, "SWE", "2016333330@gmail.com", "0584336345",
				coursesTaken, currentCourses, 15, 19);
		list[5] = new Student("Mohammed", "12345", 2016222220, 1.7, "COE", "2016222220@gmail.com", "0524446345",
				coursesTaken, currentCourses, 15, 19);
		list[6] = new Student("Abdullah", "12345", 2016111110, 0.3, "SWE", "2016111110@gmail.com", "0534556345",
				coursesTaken2016111110, currentCourses, 15, 19);
		list[7] = new Student("Faisal", "12345", 2016777770, 2.1, "ICS", "2016777770@gmail.com", "0584666345",
				coursesTaken, currentCourses, 16, 19);
		list[8] = new Student("Ahmed", "12345", 2016888880, 2.3, "SWE", "2016888880@gmail.com", "054776345",
				coursesTaken, currentCourses, 15, 19);
		list[9] = new Student("Khalid", "12345", 2016999990, 2.7, "COE", "2016999990@gmail.com", "0554886345",
				coursesTaken, currentCourses, 17, 19);
		list[10] = new Student("Waleed", "12345", 2015666660, 1.5, "SWE", "2015666660@gmail.com", "0554551234",
				coursesTaken, currentCourses, 17, 19);
		list[11] = new Student("Nawaf", "12345", 2015555550, 1.9, "ICS", "2015555550@gmail.com", "0554555678",
				coursesTaken, currentCourses, 15, 19);
		list[12] = new Student("Fahad", "12345", 2015444440, 1.2, "SWE", "2015444440@gmail.com", "0554559101",
				coursesTaken, currentCourses, 19, 19);
		list[13] = new Student("Sami", "12345", 2015333330, 3.1, "COE", "2015333330@gmail.com", "057547345",
				coursesTaken, currentCourses, 18, 19);
		list[14] = new Student("Muhannad", "12345", 2015222220, 2.9, "SWE", "2015222220@gmail.com", "0586956345",
				coursesTaken, currentCourses, 12, 19);
		list[15] = new Student("Osama", "12345", 2015111110, 2.8, "ICS", "2015111110@gmail.com", "0513455634",
				coursesTaken, currentCourses, 13, 19);
		list[16] = new Student("Anas", "12345", 2015777770, 2.0, "SWE", "2015777770@gmail.com", "0527566345",
				coursesTaken, currentCourses, 12, 19);
		list[17] = new Student("Hassan", "12345", 2015888880, 3.4, "COE", "2015888880@gmail.com", "0558976430",
				coursesTaken, currentCourses, 14, 19);
		list[18] = new Student("Hamza", "12345", 2015999990, 3.5, "SWE", "2015999990@gmail.com", "0504996345",
				coursesTaken, currentCourses, 14, 19);
		list[19] = new Student("Ibrahim", "12345", 2017666660, 0.9, "COE", "2017666660@gmail.com", "0553443457",
				coursesTaken, currentCourses, 19, 19);
		list[20] = new Student("Ziyad", "12345", 2017555550, 3.7, "ICS", "2017555550@gmail.com", "0554585240",
				coursesTaken, currentCourses, 18, 19);

		for (int i = 0; i < list.length; i++) {
			dataBase_Students.add(list[i]);
		}

		String fileName = "dataBase_Students.bin";
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject(dataBase_Students);
			os.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		System.out.println("Done writing");

		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
			ArrayList<Student> aa;
			aa = (ArrayList<Student>) is.readObject();
			Student[] xx = new Student[aa.size()];
			for (int i = 0; i < aa.size(); i++) {
				xx[i] = aa.get(i);
				// System.out.println(xx[i].getInfo());
			}

			for (int i = 0; i < xx.length; i++) {

			}
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}