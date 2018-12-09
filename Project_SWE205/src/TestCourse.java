

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


// This class we use it just to save the Courses data to file
public class TestCourse {
	public static void main(String[] args) throws ClassNotFoundException {
		
		ArrayList<Course> dataBase_Courses = new ArrayList<Course>();
		Course[] list = new Course[29];

		ArrayList<Integer> preRequest = new ArrayList<Integer>(10);
		ArrayList<Integer> studentTakeThisCourse = new ArrayList<Integer>();
		ArrayList<Integer> preRequestMATH201 = new ArrayList<Integer>();
		preRequestMATH201.add(20009);
		ArrayList<Integer> studentTakeThisCourseMATH102 = new ArrayList<Integer>();
		studentTakeThisCourseMATH102.add(2016111110);
		ArrayList<Integer> studentTakeThisCourseIAS101 = new ArrayList<Integer>();
		studentTakeThisCourseIAS101.add(2016111110);
		ArrayList<Integer> studentTakeThisCourseIAS111 = new ArrayList<Integer>();
		studentTakeThisCourseIAS111.add(2016111110);
		
		
		list[0] = new  Course(20757, "ICS102" , 3 , "UT" , "TBA" , 25 , 0 , 01 , studentTakeThisCourse , preRequest , "08:00-08:50", true);
		list[1] = new  Course(20758, "ICS102" , 3 , "UT" , "TBA" , 25 , 25, 02 , studentTakeThisCourse , preRequest , "08:00-08:50", false);
		list[2] = new  Course(20776, "ICS201" , 3 , "UTR", "FAISAL ALVI" , 25 , 0 , 01 , studentTakeThisCourse , preRequest , "08:00-08:50", true);
		list[3] = new  Course(20777, "ICS201" , 3 , "UTR", "FAISAL ALVI" , 25 , 22, 02 , studentTakeThisCourse , preRequest ,"08:00-08:50", true);
		list[4] = new  Course(20780, "ICS202" , 4 , "UTR", "FAISAL Abdullah ALVI " , 25 , 5 , 01 , studentTakeThisCourse , preRequest ,"08:00-08:50", true); // take
		list[5] = new  Course(20781, "ICS202" , 4 , "UTR", "AHMAD IRFAN", 25 , 0 , 02 , studentTakeThisCourse , preRequest , "11:00-11:50",true);
		list[6] = new  Course(24040, "ICS233" , 4 , "UTR", "MOHAMMAD SAID" , 25 , 0 , 01 , studentTakeThisCourse , preRequest ,"08:00-08:50", true );
		list[7] = new  Course(24041, "ICS233" , 4 , "UTR", "MOHAMMAD SAID" , 25 , 0 , 02 , studentTakeThisCourse , preRequest ,"08:00-08:50", true);
		list[8] = new  Course(20784, "ICS253" , 3 , "UTR", "HUSNI AL-MUHTASEB" , 25 , 0 , 01 , studentTakeThisCourse , preRequest ,"13:00-13:50", true);
		list[9] = new  Course(23358, "ICS253" , 3 , "UTR", "HUSNI AL-MUHTASEB" , 25 , 0 , 02 , studentTakeThisCourse , preRequest , "14:00-14:50",true);
		list[10] = new Course(20786, "ICS254" , 3 , "UTR", "WASFI AL-KHATIB" , 25 , 0 , 01 , studentTakeThisCourse , preRequest , "08:00-08:50",true);
		list[11] = new Course(23360, "ICS254" , 3 , "UTR", "WASFI AL-KHATIB" , 25 , 25 , 02 , studentTakeThisCourse , preRequest ,"08:00-08:50", false);
		list[12] = new Course(20878, "SWE205" , 3 , "UTR", "HAMOOD AL-JAMAAN" , 25 , 0 , 01 , studentTakeThisCourse , preRequest ,"08:00-08:50", true);//time conflict
		list[13] = new Course(23369, "SWE205" , 3 , "UTR", "JAMELEDDINE HASSINE" , 25 , 0 , 02 , studentTakeThisCourse , preRequest , "09:00-09:50",true); //
		list[14] = new Course(20797, "SWE215" , 3 , "MW" , "JAMELEDDINE HASSINE" , 25 , 0 , 01 , studentTakeThisCourse , preRequest , "08:00-08:50",true);
		list[15] = new Course(21778, "SWE215" , 3 , "MW" , "JAMELEDDINE HASSINE" , 25 , 25 , 02 , studentTakeThisCourse , preRequest , "08:00-08:50",false);
		list[16] = new Course(21700, "SWE312" , 3 , "UTR", "AHMAD IRFAN" , 25 , 0 , 01 , studentTakeThisCourse , preRequest , "08:00-08:50",true);
		list[17] = new Course(21357, "SWE316" , 3 , "UTR", "SAJJAD MAHMOOD" , 25 , 0 , 01 , studentTakeThisCourse , preRequest , "08:00-08:50",true);
		list[18] = new Course(20706, "IAS101" , 2 , "MW" , "KHALID AL JURAYAN" , 30 , 0 , 01 , studentTakeThisCourseIAS101 , preRequest ,"10:00-10:50", true);
		list[19] = new Course(20711, "IAS111" , 2 , "MW" , "SAEED AL-GHAHTANI" , 30 , 0 , 01 , studentTakeThisCourseIAS111 , preRequest , "11:00-11:50",true);
		list[20] = new Course(20724, "IAS201" , 2 , "UT" , "ABDULLAH AL-ASSAF" , 30 , 0 , 01 , studentTakeThisCourse , preRequest ,"08:00-08:50", true);
		list[21] = new Course(20460, "ENGL101", 3 , "MW" , "PEADAR CALLAGHAN" , 20 , 0 , 01 , studentTakeThisCourse , preRequest , "08:00-08:50",true);
		list[22] = new Course(21298, "ENGL102", 3 , "UTR", "JEFFREY MEHRING" , 20 , 0 , 01 , studentTakeThisCourse , preRequest , "10:00-10:50",true);
		list[23] = new Course(21431, "ENGL214", 3 , "UTR", "MALCOLM BANCROFT" , 20 , 0 , 01 , studentTakeThisCourse , preRequest , "08:00-08:50",true);
		list[24] = new Course(20807, "PE101"  , 1 , "UT" , "HABEEB AL-RABAN" , 30 , 30 , 01 , studentTakeThisCourse , preRequest ,"08:00-08:50", false);
		list[25] = new Course(20807, "COE202"  , 3 , "UTR" , "MOHAMED ELRABAA" , 30 , 0 , 02 , studentTakeThisCourse , preRequest ,"08:00-08:50", true);
		list[26] = new Course(20002, "MATH101"  , 4 , "UTR" , "MUHAMMAD RIAZ" , 30 , 0 , 01 , studentTakeThisCourse , preRequest ,"08:00-08:50", true);
		list[27] = new Course(20009, "MATH102"  , 4 , "UTR" , "SAEED AL-GARNI" , 30 , 0 , 01 , studentTakeThisCourseMATH102 , preRequest ,"08:00-08:50", true);
		list[28] = new Course(20035, "MATH201"  , 3 , "UTR" , "MOHAMMAD KAFINI" , 30 , 0 , 01 , studentTakeThisCourse , preRequestMATH201 ,"08:00-08:50", true);
		

		for (int i = 0 ; i < list.length ; i++) {
			dataBase_Courses.add(list[i]);
		}
		
		String fileName = "dataBase_Courses.bin";
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName)); 
			os.writeObject(dataBase_Courses);
			os.close();
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		}
		

		
		
		


	}
}

