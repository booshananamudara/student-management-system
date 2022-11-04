import java.util.*;
class Demo{
	
	static String[][] studentDetailsArray = new String[0][6];
	static int[][] rank =new int[0][4];
	
	
	static int increment=0;			//current position of main array...
	static int rankIncrement=0;		//current position of sub array...
	
	public static void mainBar(String h){
		for(int i=0; i<82; i++){
			System.out.print("-");
		}

		System.out.println();

		//string formatting...
		String format = "|%1$55s%2$-25s|\n";
		System.out.printf(format, h, "");

		for(int i=0; i<82; i++){
			System.out.print("-");
		}
		System.out.println();   //string h is pass from all main methods for this method to show the heaind of each method....
	}
	
	public static void mainMenu(){
		String h = "WELCOME TO  GDSE MARKS MANAGEMENT SYSTEM";
		mainBar(h);
		String format = "%1$-41s%2$-40s\n";
		System.out.printf(format,"[1] Add New Student","[2] Add New Student With Marks");	
		System.out.printf(format,"[3] Add Marks","[4] Update Student Details");
		System.out.printf(format,"[5] Update Marks","[6] Delete Student");		
		System.out.printf(format,"[7] Print Student Details","[8] Print Student Ranks");		
		System.out.printf(format,"[9] Best In Programming Fundamentals","[10]Best In Ddatabase Management System");	
	}
	
	//for validate the marks input by user......
	public static boolean isValidMark(int mark){
		return (mark>=0 & mark<=100);
	}
	
	public static void addNewStudent(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "ADD NEW STUDENT";
		mainBar(h);
		
		String id=" ";
		while(true){
			studentDetailsArray=extendsArray();

			while(true){
				System.out.print("Enter Student ID : ");
				id=input.nextLine();

				if(searchElement(id)){
					System.out.println("Student Id is already exist.");
					continue;
				}
				break;
			}
				
			System.out.print("\nEnter Student Name : ");
			String name=input.nextLine();

			studentDetailsArray[increment][0]=id;
			studentDetailsArray[increment][1]=name;
			increment++;
				
			//ask from user to excute and go back to main menu...
			while(true){
				System.out.print("Student has been added successfully.Do you want to add a new student (Y/n):");
				String pass=input.nextLine();
				if(pass.equalsIgnoreCase("n")){
					return;
				}else if(!pass.equalsIgnoreCase("y")){
					System.out.println("Enter a correct option....");
					continue;
				}
				break;
			}
		}
		
	}
	
	public static void addNewStudentWithMarks(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "ADD NEW STUDENT With Marks";
		mainBar(h);
		
		while(true){
			String id=" ";
			studentDetailsArray=extendsArray();

			//check whether the id input by user is exist or not....
			while(true){
				System.out.print("Enter Student ID : ");
				id=input.nextLine();

				if(searchElement(id)){
					System.out.println("Student Id is already exist.");
					continue;
				}
				break;
			}
				
			System.out.print("\nEnter Student Name : ");
			String name=input.nextLine();
			
			String pfmarks ="";
			String dmsmarks ="";
			int pf_marks = 0;
			int dms_marks = 0;

			//take the marks for new student by user....
			while(true){
				System.out.print("\nProgramming Fundamentals Marks : ");
				pfmarks=input.nextLine();
			
				pf_marks = Integer.parseInt(pfmarks);
				if(!isValidMark(pf_marks)){
					System.out.println("Invalid marks.Please Enter Correct Marks.");
					continue;
				}
				break;
			}

			while(true){
				System.out.print("\nDatabase Management Systems Marks : ");
				dmsmarks=input.nextLine();
			
				dms_marks = Integer.parseInt(dmsmarks);
				if(!isValidMark(dms_marks)){
					System.out.println("Invalid marks.Please Enter Correct Marks.");
					continue;
				}
				break;
			}
			int total = pf_marks+dms_marks;
			double average = (double)total/2;
			
			//convert all strings for int and double for put them to sub array...
			String total_string = Integer.toString(total);
			String avg = average+"";
			
			//store data in main array...
			studentDetailsArray[increment][0]=id;
			studentDetailsArray[increment][1]=name;
			studentDetailsArray[increment][2]=pfmarks;
			studentDetailsArray[increment][3]=dmsmarks;
			studentDetailsArray[increment][4]=total_string;
			studentDetailsArray[increment][5]=avg;

			//store data in sub array...
			fillRank(increment);

			increment++;

			//excution to main menu by user order...
			while(true){
				System.out.print("Student has been added successfully.Do you want to add a new student (Y/n):");
				String pass=input.nextLine();

				if(pass.equalsIgnoreCase("n")){
					return;
				}else if(!pass.equalsIgnoreCase("y")){
				System.out.println("\nEnter a correct option....");
					continue;
				}
				break;
			}		
		}
	}
	
	public static void addMarks(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "ADD MARKS";
		mainBar(h);
		
		String id ="";
		while(true){
			while(true){					
				System.out.print("Enter Student ID : ");
				id=input.nextLine();
				if(!searchElement(id)){		
					System.out.println("invalid student id.Do you want to search again (Y/n) : ");
					String pass1 = input.nextLine();
					
					if(pass1.equalsIgnoreCase("n")){
						return;
					}else if(!pass1.equalsIgnoreCase("y")){
						System.out.println("Enter a correct option....");
						continue;
					}
					break;
				}
			
			
			
				int key = getStudentById(id);
			
				System.out.println("Student Name : "+studentDetailsArray[key][1]);
				
				if(studentDetailsArray[key][2]!=null){
					System.out.println("This student\'s marks have been already added.\nif you want to update the marks, please use [4] Update Marks option. ");
				}else{
					String pfmarks ="";
					String dmsmarks ="";
					int pf_marks=0;
					int dms_marks=0;
					
					while(true){
						System.out.print("\nProgramming Fundamentals Marks : ");
						pfmarks=input.nextLine();
			
						pf_marks = Integer.parseInt(pfmarks);
						if(!isValidMark(pf_marks)){
							System.out.println("Invalid marks.Please Enter Correct Marks.");
							continue;
						}
						break;
					}
			
					studentDetailsArray[key][2]=pfmarks;
				
					while(true){
						System.out.print("\nDatabase Management Systems Marks : ");
						dmsmarks=input.nextLine();
			
						dms_marks = Integer.parseInt(dmsmarks);
						if(!isValidMark(dms_marks)){
							System.out.println("Invalid marks.Please Enter Correct Marks.");
							continue;
						}
						break;
					}
				
					int total=pf_marks+dms_marks;
					double average=(double)total/2;
				
					String total_string = Integer.toString(total);
					String avg = average+"";
				
					studentDetailsArray[key][3]=dmsmarks;
					studentDetailsArray[key][4]=total_string;
					studentDetailsArray[key][5]=avg;
					fillRank(key);
				}
				while(true){
					System.out.print("succes.do you want another (Y/n) : ");
					String pass = input.nextLine();
					if(pass.equalsIgnoreCase("n")){
						return;
					}else if(!pass.equalsIgnoreCase("y")){
						System.out.println("\nEnter a correct option....");
						continue;
					}
					break;
				}
			}	
		}		
	}
	
	public static void updateStudentDetails(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "UPDATE STUDENT DETAILS";
		mainBar(h);
		
		String id ="";
		while(true){
			while(true){					
				System.out.print("Enter Student ID : ");
				id=input.nextLine();
				if(!searchElement(id)){		
					System.out.println("invalid student id.Do you want to search again (Y/n) : ");
					String pass1 = input.nextLine();
					
					if(pass1.equalsIgnoreCase("n")){
						return;
					}else if(!pass1.equalsIgnoreCase("y")){
						System.out.println("Enter a correct option....");
						continue;
					}
					break;
				}
			
			
				int key = getStudentById(id);
				
			
				System.out.println("Student Name : "+studentDetailsArray[key][1]);

				System.out.println("Enter the new student name : ");
				String newName = input.nextLine();
				
				studentDetailsArray[key][1]=newName;

				while(true){
					System.out.print("Student name has been updated successfully.\nDo you want to update another student details ? (Y/n) : ");
					String pass = input.nextLine();
					if(pass.equalsIgnoreCase("n")){
						return;
					}else if(!pass.equalsIgnoreCase("y")){
						System.out.println("Enter a correct option....");
						continue;
					}
					break;
				}
			}
		}

	}

	public static void updateMarks(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "UPDATE MARKS";
		mainBar(h);
		
		String id ="";
		while(true){
			while(true){					
				System.out.print("Enter Student ID : ");
				id=input.nextLine();
				if(!searchElement(id)){		
					System.out.println("invalid student id.Do you want to search again (Y/n) : ");
					String pass1 = input.nextLine();
					
					if(pass1.equalsIgnoreCase("n")){
						return;
					}else if(!pass1.equalsIgnoreCase("y")){
						System.out.println("Enter a correct option....");
						continue;
					}
					break;
				}
			
				int key = getStudentById(id);
				int rankKey = getStudentRankById(key);
				
				System.out.println(rankKey);
				
				System.out.println("Student Name : "+studentDetailsArray[key][1]);
				
				if(studentDetailsArray[key][2]==null){
					System.out.println("This student\'s marks yet to be added.\n");

				}else{
				
					System.out.println("Programming Fundamentals Marks : "+studentDetailsArray[key][2]);
					System.out.println("Database Management Systems Marks : "+studentDetailsArray[key][3]+"\n");

					String newPFmarks ="";
					String newDMSmarks ="";
					int new_pf_marks=0; 
					int new_dms_marks=0;

					while(true){
						System.out.print("Enter the new Programming Fundamentals Marks : ");
						newPFmarks =input.nextLine();
			
						new_pf_marks = Integer.parseInt(newPFmarks);
						if(!isValidMark(new_pf_marks)){
							System.out.println("Invalid marks.Please Enter Correct Marks.");
							continue;
						}
						break;
					}
								
					while(true){
						System.out.print("Enter the new Database Management System Marks :");
						newDMSmarks=input.nextLine();
			
						new_dms_marks = Integer.parseInt(newDMSmarks);
						if(!isValidMark(new_dms_marks)){
							System.out.println("Invalid marks.Please Enter Correct Marks.");
							continue;
						}
						break;
					}
	
					int newTotal = new_pf_marks+new_dms_marks;
					double newAverage = (double)newTotal/2;
					
					studentDetailsArray[key][2]=newPFmarks;
					studentDetailsArray[key][3]=newDMSmarks;
					studentDetailsArray[key][4]=Integer.toString(newTotal);
					studentDetailsArray[key][5]=Double.toString(newAverage);
					updateRank(rankKey,key);
					System.out.println("Student marks have been updated successfully.\n");
				}

				while(true){
					System.out.print("Do you want to update another student marks ? (Y/n) : ");
					String pass = input.nextLine();

					if(pass.equalsIgnoreCase("n")){
						return;
					}else if(!pass.equalsIgnoreCase("y")){
						System.out.println("Enter a correct option....");
						continue;
					}
					break;
				}
			}
		}
	}

	public static void deleteStudent(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "DELETE STUDENT";
		mainBar(h);
		
		String id ="";
		while(true){
			while(true){					
				System.out.print("Enter Student ID : ");
				id=input.nextLine();
				if(!searchElement(id)){		
					System.out.println("invalid student id.Do you want to search again (Y/n) : ");
					String pass1 = input.nextLine();
					
					if(pass1.equalsIgnoreCase("n")){
						return;
					}else if(!pass1.equalsIgnoreCase("y")){
						System.out.println("Enter a correct option....");
						continue;
					}
					break;
				}
			
				int key = getStudentById(id);
				int rankKey = 0;
				
				//first update the rank array...
				
				if(studentDetailsArray[key][2]==null){
					for(int i=0; i<rank.length; i++){
						if(rank[i][0]>key){
							int tempRank=rank[i][0];
							rank[i][0]=tempRank-1;
						}
					}
				
				}else{
					rankKey = getStudentRankById(key);
					System.out.println("rankKey : "+rankKey);
					int tempRank = rank[rankKey][0];
					System.out.println("tempRank : "+tempRank);
					
					if(rank.length>1){
						for(int j=rankKey; j<rank.length-1; j++){
							for(int i=0; i<4; i++){
								rank[j][i]=rank[j+1][i];
							}
						}
						for(int j=rankKey; j<rank.length; j++){
							rank[j][0]=(rank[j][0])-1;		
						}
					}
					
					rank=shrinkRankArray();
					rankIncrement--;
	
				}
				
				//update the main array...
				
				for(int j=key; j<studentDetailsArray.length-1; j++){
					for(int i=0; i<6; i++){
						studentDetailsArray[j][i]=studentDetailsArray[j+1][i];
					}
				}
				studentDetailsArray=shrinkArray();
				increment--;
				
				
				System.out.println("Student has been deleted successfully.");
				
				while(true){
					System.out.print("Do you want to update another student marks ? (Y/n) : ");
					String pass = input.nextLine();
					if(pass.equalsIgnoreCase("n")){
						return;
					}else if(!pass.equalsIgnoreCase("y")){
						System.out.println("Enter a correct option....");
						continue;
					}
					break;
				}
			}
		}
		
	}
	public static void printMarks(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "PRINT STUDENT DETAILS";
		mainBar(h);
		
		String id ="";
		while(true){
			while(true){					
				System.out.print("Enter Student ID : ");
				id=input.nextLine();
				if(!searchElement(id)){		
					System.out.println("invalid student id.Do you want to search again (Y/n) : ");
					String pass1 = input.nextLine();
					
					if(pass1.equalsIgnoreCase("n")){
						return;
					}else if(!pass1.equalsIgnoreCase("y")){
						System.out.println("Enter a correct option....");
						continue;
					}
					break;
				}
			
				int key = getStudentById(id);

				System.out.println("Student Name : "+studentDetailsArray[key][1]);
				
				if(studentDetailsArray[key][2]==null){
					System.out.println("This student\'s marks yet to be added.\n");

				}else{
					rankSort();
					String rankno="";
					String position="";
					int stRank=getStudentRankById(key);
					int stRankTemp=stRank;
					if(stRankTemp==(rank.length-1)){
						stRankTemp=-1;
					}
					rankno = Integer.toString(stRank+1);

					switch(stRankTemp){
						case 0 : position="(First)";
								break;
						case 1 : position="(Second)";
								break;
						case 2 : position="(Third)";
								break;
						case -1: position="(Last)";
								break;
					}
					
					String a ="+";
					String b ="|";
					String format = "%1$-1s%2$-40s%3$1s%4$30s%5$1s\n";
					System.out.format(format, a,"----------------------------------------",a,"------------------------------",a);
					System.out.printf(format,b,"PROGRAMMING FUNDAMENTALS MARKS",b,(studentDetailsArray[key][2]),b);
					System.out.printf(format,b,"DATABASE MANAGEMENT SYSTEM Marks",b,(studentDetailsArray[key][3]),b);
					System.out.printf(format,b,"TOTAL MARKS",b,(studentDetailsArray[key][4]),b);
					System.out.printf(format,b,"AVERAGE Marks",b,(studentDetailsArray[key][5]),b);
					System.out.printf(format,b,"Rank",b,rankno+position,b);
					System.out.format(format, a,"----------------------------------------",a,"------------------------------",a);	
				}	
				while(true){
					System.out.print("Do you want to search another student marks ? (Y/n) : ");
					String pass = input.nextLine();
					if(pass.equalsIgnoreCase("n")){
						return;
					}else if(!pass.equalsIgnoreCase("y")){
						System.out.println("Enter a correct option....");
						continue;
					}
					break;
				}
			}
		}
	}

	public static void printStudentRanks(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "PRINT STUDENT RANKS";
		mainBar(h);
		
		rankSort();
		int rankNo = 1;
		String a ="+";
		String b ="|";
		
		String format = "%1$-1s%2$-9s%3$1s%4$-14s%5$1s%6$-23s%7$1s%8$15s%9$1s%10$15s%11$1s\n";
		System.out.printf(format, a,"---------",a, "--------------",a, "-----------------------",a,"---------------",a,"---------------",a);
		System.out.printf(format,b,"RANK",b,"ID",b,"STUDENT NAME",b,"Total Marks",b,"Avg. marks",b);
		System.out.printf(format, a,"---------",a, "--------------",a, "-----------------------",a,"---------------",a,"---------------",a);
		
		for(int i=0; i<rank.length; i++){
			int key = rank[i][0];
			String rankN = Integer.toString(rankNo);
			System.out.printf(format,b,rankN,b,studentDetailsArray[key][0],b,studentDetailsArray[key][1],b,studentDetailsArray[key][4],b,studentDetailsArray[key][5],b);
			rankNo++;
		}
		System.out.printf(format, a,"---------",a, "--------------",a, "-----------------------",a,"---------------",a,"---------------",a+"\n");
		
		while(true){
			System.out.print("Do you want to go back to main menu ? (Y/n) : ");
			String pass = input.nextLine();
				if(!pass.equalsIgnoreCase("y")){
					System.out.println("Enter a correct option....");
					continue;
				}
				break;
		}
		
	}
	
	public static void bestInProgrammingFundamentals(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "BEST IN PROGRAMMING FUNDAMENTALS";
		mainBar(h);
		
		pfSort();
		int rankNo = 1;
		String a ="+";
		String b ="|";

		String format = "%1$-1s%2$-9s%3$1s%4$-14s%5$1s%6$15s%7$1s%8$15s%9$1s\n";
		System.out.printf(format, a,"---------",a, "--------------",a,"---------------",a,"---------------",a);
		System.out.printf(format,b,"ID",b,"STUDENT NAME",b,"PF Marks",b,"DBMS Marks",b);
		System.out.printf(format, a,"---------",a, "--------------",a,"---------------",a,"---------------",a);
		
		for(int i=0; i<rank.length; i++){
			int key = rank[i][0];
			//for(int j=0; j<1; j++){
			System.out.printf(format,b,(studentDetailsArray[key][0]),b,(studentDetailsArray[key][1]),b,(studentDetailsArray[key][2]),b,(studentDetailsArray[key][3]),b);
			
			rankNo++;
		}

		System.out.printf(format, a,"---------",a, "--------------",a,"---------------",a,"---------------",a);
		
		while(true){
			System.out.print("Do you want to go back to main menu ? (Y/n) : ");
			String pass = input.nextLine();
			
			if(!pass.equalsIgnoreCase("y")){
				System.out.print("Enter a correct option....");
				continue;
			}
			break;
		}
	}
	
	public static void bestInDdatabaseManagementSystem(){
		Scanner input=new Scanner(System.in);
		//display the main bar..
		String h = "BEST IN DATABASE MANAGEMENT SYSYTEM";
		mainBar(h);
		
		dbmsSort();
		int rankNo = 1;
		String a ="+";
		String b ="|";

		String format = "%1$-1s%2$-9s%3$1s%4$-14s%5$1s%6$15s%7$1s%8$15s%9$1s\n";
		System.out.printf(format, a,"---------",a, "--------------",a,"---------------",a,"---------------",a);
		System.out.printf(format,b,"ID",b,"STUDENT NAME",b,"DBMS Marks",b,"PF Marks",b);
		System.out.printf(format, a,"---------",a, "--------------",a,"---------------",a,"---------------",a);
		
		
		for(int i=0; i<rank.length; i++){
			int key = rank[i][0];
			System.out.printf(format,b,(studentDetailsArray[key][0]),b,(studentDetailsArray[key][1]),b,(studentDetailsArray[key][3]),b,(studentDetailsArray[key][2]),b);
			rankNo++;
		}
		
		System.out.printf(format, a,"---------",a, "--------------",a,"---------------",a,"---------------",a);
		
		while(true){
			System.out.print("Do you want to go back to main menu ? (Y/n) : ");
			String pass = input.nextLine();
			if(!pass.equalsIgnoreCase("y")){
				System.out.println("Enter a correct option....");
				continue;
			}
			break;
		}
	}
	
	//check whether there is a student id what input by user.....
	public static boolean searchElement(String key){
		for (int i = 0; i < studentDetailsArray.length; i++){
			if(key.equals(studentDetailsArray[i][0])){
				return true;
			}
		}
		return false;
	}
	
	//to extend the main array when it need to extend....
	public static String[][] extendsArray(){
		String[][] temp=new String[studentDetailsArray.length+1][6];
		for (int i = 0; i < studentDetailsArray.length; i++){
			for(int j=0; j<6; j++){
				temp[i][j]=studentDetailsArray[i][j];
			}
		}
		return temp;
	}
	
	//to extend the sub array(rank array) when it need to extend....
	public static int[][] extendsRankArray(){
		int[][] temp=new int[rank.length+1][4];
		for (int i = 0; i < rank.length; i++){
			for(int j=0; j<4; j++){
				temp[i][j]=rank[i][j];
			}
		}
		return temp;
	}
	
	//to shrink the main array when delete a student....
	public static String[][] shrinkArray(){
		String[][] temp=new String[studentDetailsArray.length-1][6];
		for(int j=0; j<studentDetailsArray.length-1; j++){
			for (int i = 0; i < 6; i++){
				temp[j][i]=studentDetailsArray[j][i];
			}
		}
		return temp;
	}
	
	//to shrink the sub array when delete a student....
	public static int[][] shrinkRankArray(){
		int[][] temp=new int[rank.length-1][4];
		for(int j=0; j<rank.length-1; j++){
			for (int i = 0; i < 4; i++){
				temp[j][i]=rank[j][i];
			}
		}
		return temp;
	}
	
	//to get the student index in main array by student id....
	public static int getStudentById(String key){
		int i=0;
		for (i = 0; i < studentDetailsArray.length; i++){
			if(key.equals(studentDetailsArray[i][0])){
				return i;
			}
		}
		return i;
	}
	
	//get the student index in sub array(rank array) by student key....
	public static int getStudentRankById(int key){
		int i=0;
		for(i=0; i<rank.length; i++){
			if(key==rank[i][0]){
				return i;
			}
		}
		return i;
	}
	
	//when user input marks for student this method helps to fulfil the sub(rank) array...
	public static void fillRank(int index){	
				rank=extendsRankArray();
				rank[rankIncrement][0]=getStudentById(studentDetailsArray[index][0]);
				rank[rankIncrement][1]=Integer.parseInt(studentDetailsArray[index][4]);
				rank[rankIncrement][2]=Integer.parseInt(studentDetailsArray[index][2]);
				rank[rankIncrement][3]=Integer.parseInt(studentDetailsArray[index][3]);
				rankArraySort();
				rankIncrement++;	
	}
	
	//to update the rank array when user update marks...
	public static void updateRank(int index, int key){
		rank[index][1]=Integer.parseInt(studentDetailsArray[key][4]);
		rank[index][2]=Integer.parseInt(studentDetailsArray[key][2]);
		rank[index][3]=Integer.parseInt(studentDetailsArray[key][3]);
		rankArraySort();
	}
	
	//to sort the key (first index) in the rank array...
	
	public static void rankArraySort(){
		//sort for ranks-------
		for(int i=0; i<rank.length-1; i++){
			for(int j=0; j<rank.length-(i+1); j++){
				if(rank[j][0]>rank[j+1][0]){
					
					int temp1 = rank[j][0];
					int temp2 = rank[j][1];
					int temp3 = rank[j][2];
					int temp4 = rank[j][3];

					rank[j][0]=rank[j+1][0];
					rank[j][1]=rank[j+1][1];
					rank[j][2]=rank[j+1][2];
					rank[j][3]=rank[j+1][3];

					rank[j+1][0]=temp1;
					rank[j+1][1]=temp2;
					rank[j+1][2]=temp3;
					rank[j+1][3]=temp4;
				}
			}
		}	
	}
	
	
	//to sort the total marks in rank array....
	public static void rankSort(){
		//sort for ranks-------
		for(int i=0; i<rank.length-1; i++){
			for(int j=0; j<rank.length-(i+1); j++){
				if(rank[j][1]<rank[j+1][1]){
					
					int temp1 = rank[j][0];
					int temp2 = rank[j][1];
					int temp3 = rank[j][2];
					int temp4 = rank[j][3];

					rank[j][0]=rank[j+1][0];
					rank[j][1]=rank[j+1][1];
					rank[j][2]=rank[j+1][2];
					rank[j][3]=rank[j+1][3];

					rank[j+1][0]=temp1;
					rank[j+1][1]=temp2;
					rank[j+1][2]=temp3;
					rank[j+1][3]=temp4;
				}
			}
		}	
	}
	
	//to sort the pf marks in rank array....
	public static void pfSort(){
		//sort for ranks-------
		
		for(int i=0; i<rank.length-1; i++){
			for(int j=0; j<rank.length-(i+1); j++){
				if(rank[j][2]<rank[j+1][2]){
					
					int temp1 = rank[j][0];
					int temp2 = rank[j][1];
					int temp3 = rank[j][2];
					int temp4 = rank[j][3];

					rank[j][0]=rank[j+1][0];
					rank[j][1]=rank[j+1][1];
					rank[j][2]=rank[j+1][2];
					rank[j][3]=rank[j+1][3];

					rank[j+1][0]=temp1;
					rank[j+1][1]=temp2;
					rank[j+1][2]=temp3;
					rank[j+1][3]=temp4;
				}
			}
		}	
	}
	
	//to sort the dbms marks in rank array....
	public static void dbmsSort(){
		for(int i=0; i<rank.length-1; i++){
			for(int j=0; j<rank.length-(i+1); j++){
				if(rank[j][3]<rank[j+1][3]){
					
					int temp1 = rank[j][0];
					int temp2 = rank[j][1];
					int temp3 = rank[j][2];
					int temp4 = rank[j][3];

					rank[j][0]=rank[j+1][0];
					rank[j][1]=rank[j+1][1];
					rank[j][2]=rank[j+1][2];
					rank[j][3]=rank[j+1][3];

					rank[j+1][0]=temp1;
					rank[j+1][1]=temp2;
					rank[j+1][2]=temp3;
					rank[j+1][3]=temp4;
				}
			}
		}	
	}

	//this is optional..this shows the main array and sub array anytime....
	public static void printMarksall(){
		Scanner input = new Scanner(System.in);
		for(int i=0; i<studentDetailsArray.length; i++){
			for(int j=0; j<6; j++){
				System.out.print(studentDetailsArray[i][j]+"\t");
			}
			System.out.println();
		}
		
		for(int i=0; i<rank.length; i++){
			for(int j=0; j<4; j++){
				System.out.print(rank[i][j]+"\t");
			}
			System.out.println();
		}
		
		while(true){
			System.out.print("Student has been added successfully.Do you want to add a new student (Y/n):");
			String pass=input.nextLine();
			if(pass.equals("n")){
				return;
			}else if(!pass.equals("y")){
				System.out.println("Enter a correct option....");
				continue;
			}
			break;
		}	
	}
	
	//to clear the concole whrn moving one slide to another.	
	public final static void clearConsole() { 
		try {
		final String os = System.getProperty("os.name"); 
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J"); 
				System.out.flush();
			}
		} catch (final Exception e) {
			e.printStackTrace();
			// Handle any exceptions.
		}
	}
	
	public static void main(String args[]){
		
		while(true){
			Scanner input=new Scanner(System.in);
			mainMenu();

			//get the option by user...
			System.out.print("\nEnter an option to continue > ");
			int option=input.nextInt();

			clearConsole();

			switch(option){
				case 1 :
					addNewStudent();
					break;
				case 2 :
					addNewStudentWithMarks();
					break;
				case 3 :
					addMarks();
					break;
				case 4 :
					updateStudentDetails();
					break;
				case 5 :
					updateMarks();
					break;
				case 6 :
					deleteStudent();
					break;
				case 7 :
					printMarks();
					
					break;
				case 8 :
					printStudentRanks();
					break;
				case 9 :
					bestInProgrammingFundamentals();
					break;
				case 10 :
					bestInDdatabaseManagementSystem();
					break;
				case 11 :
					printMarksall();
					break;
				default :
					System.out.println("Enter a correct option");		
			}
			clearConsole();
		}
	}
}
