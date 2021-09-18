package com.lockers.lockedme;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LockedMeMain {
	public static void main(String[] args) throws IOException {
		LockedMeMain objLocked = new LockedMeMain();

		WelcomeScreen();			//Method to display the Welcome Screen
	}
	
	protected static void WelcomeScreen() throws IOException {
		
		int nNoOfFiles = 0;
		int nYorNo = 0;
		try (Scanner sc = new Scanner(System.in)) {
			// TODO Auto-generated method stub
			//1. Display Application Name and Developer Details
			System.out.println("******************************************************************************");
			System.out.println("*                                LockedMe.com                                *");
			System.out.println("*                                                                            *");
			System.out.println("*      Copyright @ Company Lockers Pvt. Ltd. 2021 - All Rights Reserved      *");
			System.out.println("*                                                                            *");
			System.out.println("*                   Developed by Mr. Joesam John Zachariah                   *");
			System.out.println("*                                                                            *");
			System.out.println("******************************************************************************");
			System.out.println("\n");
			
			//2. Details of the User Interface
			System.out.println("Available Operations: ");
			System.out.println("## Display File Names in the Root Directory, Add/Delete/Search Files in the Root Directory ##");
			System.out.println("## Navigate to Main Context and Exit the Application ##");
			System.out.println("\n");
			
			//3. Features to accept User Input
			do{
				System.out.println("Select from the below option to perform the operations");
				System.out.println("1. Display File Names in the Root Directory");
				System.out.println("2. To Perform Add/Delete/Search Operations in the Root Directory");
				System.out.println("3. Exit the Application");
				int nFeatureSelected = sc.nextInt();
				if(nFeatureSelected == 1) {
					//Call Method to Display the File Names in the Root Directory
					DisplayFileNames();
					System.out.println("Do you want to continue: (Y:1/N:0)");
					nYorNo = sc.nextInt();
					if(nYorNo == 0) break;
					else if(nYorNo == 1) continue;
				}
				else if(nFeatureSelected == 2) {
					PerformOperation();
					System.out.println("Do you want to continue: (Y:1/N:0)");
					nYorNo = sc.nextInt();
					if(nYorNo == 0) break;
					else if(nYorNo == 1) continue;
				}
				else {
					sc.close();
					System.out.println("Exiting from the Application.....");
					System.exit(0);
				}
				sc.close();
			}while(nYorNo != 0);
		}
	}

	private static void PerformOperation() throws IOException {
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Select from the below option to perform the File Operations");
		System.out.println("1: Add File to the Root Directory");
		System.out.println("2: Delete File from the Root Directory");
		System.out.println("3: Search File in the Root Directory");
		System.out.println("4: Go to the previous menu");
		System.out.println("5: Exit the Application");
		int nOpnSelected = sc1.nextInt();
		if(nOpnSelected == 1) {
			AddFile();
		}
		else if(nOpnSelected == 2) {
			DeleteFile();
		}
		else if(nOpnSelected == 3) {
			SearchFile();
		}
		else if(nOpnSelected == 4) {
		}
		else if (nOpnSelected == 5) {
			System.out.println("Exiting from the Application.....");
			System.exit(0);				
		}
	}

	protected static void SearchFile() throws IOException {
		
		Scanner sc2 = new Scanner(System.in);
		String sFileName, sDirectoryName;
		sDirectoryName = "C:\\Lockers\\";
		
		System.out.println("Enter the File Name to be searched");
		sFileName = sc2.next();
		
		sFileName = sDirectoryName + sFileName;
		
		File file = new File(sFileName);
		
		if(file.exists() && file.getCanonicalPath().equals(sFileName)) {
			System.out.println("File #"+file.getName()+"# is found");
		}
		else {
			System.out.println("File Searched Not Found");
		}
	}

	private static void DeleteFile() throws IOException {
		Scanner sc2 = new Scanner(System.in);
		String sFileName, sDirectoryName;
		sDirectoryName = "C:\\Lockers\\";
		
		System.out.println("Enter the File Name to be deleted");
		sFileName = sc2.next();
		
		sFileName = sDirectoryName + sFileName;
		
		File file = new File(sFileName);
		
		if(file.exists() && file.getCanonicalPath().equals(sFileName)) {
			file.delete();
			System.out.println("File Deleted Successfully");
		}
		else {
			System.out.println("File Not Found");
		}
	}

	private static void AddFile() throws IOException {
		Scanner sc2 = new Scanner(System.in);
		String sFileName, sDirectoryName;
		sDirectoryName = "C:\\Lockers\\";
		
		System.out.println("Enter the File Name to be created");
		sFileName = sc2.next();
		
		sFileName = sDirectoryName + sFileName;
		File file = new File(sFileName);
		
		if(file.createNewFile()) {
			System.out.println("File Created Successfully");
		}
		else {
			System.out.println("File Already Exists");
		}
	}

	private static void DisplayFileNames() {
		int nCount = 0;
		File folder = new File("C:\\Lockers\\");
		
		File[] listofFiles = folder.listFiles();
		
		for(File file : listofFiles) {
			if(file.isFile()) {
				nCount++;
			}
		}
		
		String[] sArray = new String[nCount];
		int i = 0;
		
		for(File file : listofFiles) {
			if(file.isFile()) {
				sArray[i] = file.getName();
				i++;
			}
		}
				
		mSort(sArray, 0, sArray.length-1);

		System.out.println("Sorted List of Files: ");
		mPrintArray(sArray);
	}
	
	//Searching Algorithm Implementation
	
	private static void mSort(String sArray[], int nLeft, int nRight) {
		if(nLeft < nRight) {
			//Find the middle point
			int nMid = (nLeft + nRight) / 2;
			
			//Sort first and second halves
			mSort(sArray, nLeft, nMid);
			mSort(sArray, nMid+1, nRight);
			
			//Merge the sorted halves
			mMerge(sArray,nLeft,nMid,nRight);
		}
	}

	private static void mMerge(String sArray[], int nLeft, int nMid, int nRight) {
		//Find sizes of two sub arrays to be merged
		int nSArr1 = nMid - nLeft + 1;
		int nSArr2 = nRight - nMid;
		
		//Create Temporary Arrays
		String sTLeft[] = new String [nSArr1];
		String sTRight[] = new String [nSArr2];
		
		//Copy data to temporary arrays
		for(int i = 0; i < nSArr1; ++i)
			sTLeft[i] = sArray[nLeft+i];
		for(int j = 0; j < nSArr2; ++j)
			sTRight[j] = sArray[nMid+1+j];
		
		//Merge the temporary arrays
		
		//Initialize the index of the first and second sub arrays
		int i = 0, j = 0;
		
		//Initialize the index of merged sub array
		int k = nLeft;
		
		while(i < nSArr1 && j < nSArr2) {
			if(sTLeft[i].compareTo(sTRight[j]) <= 0) {
				sArray[k] = sTLeft[i];
				i++;
			}
			else {
				sArray[k] = sTRight[j];
				j++;
			}
			k++;
		}
		
		//Copy remaining elements of nTLeft[] if any
		while(i < nSArr1) {
			sArray[k] = sTLeft[i];
			i++;
			k++;
		}
		
		//Copy remaining elements of nTRight[] if any
		while(j < nSArr2) {
			sArray[k] = sTRight[j];
			j++;
			k++;
		}
	}

	private static void mPrintArray(String sArray[]) {
		int nArrLength = sArray.length;
		for (int i = 0; i < nArrLength; ++i) {
			System.out.println(sArray[i]);
		}
	}
}
