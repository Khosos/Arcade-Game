import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class HighscoresList 
{
	public HighscoreRecord[] getRecordList() {
		return recordList;
	}

	private int size, maxSize; 
	private HighscoreRecord recordList[]; 

	public HighscoresList() 
	{
		this.size = 0; 
		this.maxSize = 20; 
		recordList = new HighscoreRecord[maxSize]; 
	}

	public HighscoresList(int size) 
	{
		this.size = size; 
		this.maxSize = 20; 
		recordList = new HighscoreRecord[maxSize]; 
	}

	public boolean insert (HighscoreRecord record)// this mehtod an account record into the account record list
	{
		if (checkDuplicate(record))
		{ 
			if (size < maxSize)// checks if there is space , if there is the record is placed
			{
				size++;
				recordList [size - 1] = record;
				return true;
			}
		} 
		return false;
	}

	//Deleting a record from the list
	public boolean delete (String userName)
	{ 
		//Delete method which uses the binary search
		int where = 0; 
		where = binarySearch(userName); 

		//Index of what to delete is given by search method
		if (where >= 0)
		{ 
			//Remove the method from the list, and decrease the size of the list
			recordList [where] = recordList [size - 1]; 
			size--; 
			return true; 
		}
		return false; 
	}

	//Sorting for strings
	public void insertSort ()// sorting method
	{
		for (int top = 1 ; top < size ; top++)
		{
			HighscoreRecord item = recordList [top];
			int i = top;
			while (i > 0 && (item.getUserName().compareToIgnoreCase (recordList [i-1].getUserName()) < 0))
			{
				recordList [i] = recordList [i - 1];
				i--;
			}
			recordList [i] = item;
		}
	}

	public boolean checkDuplicate (HighscoreRecord input)
	{ 
		for (int i = 0; i < size; i++)
		{ 
			if (recordList[i].getUserName().equals(input.getUserName()))
			{ 
				return false; 
			}
		}
		return true; 
	}

	public boolean checkExisiting (String name)
	{ 
		for (int i = 0; i < size; i++)
		{ 
			if (recordList[i].getUserName().equals(name))
			{ 
				return true; 
			}
		}
		return false; 
	}

	//Sorting for strings
	public void insertSort2 (int game)// sorting method
	{
		//Gets snake score
		if (game == 1)
		{ 
			for (int top = 1 ; top < size ; top++)
			{
				HighscoreRecord item = recordList [top];
				int i = top;
				while (i > 0 && (item.getFlappyScore() > recordList [i-1].getFlappyScore()))
				{
					recordList [i] = recordList [i - 1];
					i--;
				}
				recordList [i] = item;
			}
		}

		//Gets flappy score
		else if (game == 2)
		{ 
			for (int top = 1 ; top < size ; top++)
			{
				HighscoreRecord item = recordList [top];
				int i = top;
				while (i > 0 && (item.getSnakeScore() > recordList [i-1].getSnakeScore()))
				{
					recordList [i] = recordList [i - 1];
					i--;
				}
				recordList [i] = item;
			}
		}
	} 

	public int getPin(int loc)
	{ 
		return recordList[loc].getLoginKey(); 
	}

	public String toString()
	{ 
		String output = ""; 
		for (int i = 0; i < size; i++)
		{ 
			output += recordList[i].toString() + "\n"; 
		}
		return output; 
	}
	public int binarySearch (String searchKey)
	{
		insertSort ();// sorts first 

		int low = 0;
		int high = size - 1;   // set bottom and top of array
		int middle;
		while (low <= high)
		{
			middle = (high + low) / 2; //divide array in two
			if (searchKey.equalsIgnoreCase (recordList[middle].getUserName()))
			{
				return middle;
			}
			else if (searchKey.compareToIgnoreCase (recordList[middle].getUserName()) < 0)
			{
				high = middle - 1;
			}
			else
			{
				low = middle + 1;
			}
		}
		return -1;
	}

	//If the user wants to save the data
	public boolean writeToFile(String fileName)
	{ 
		try
		{
			//Declare a print writer to write to the new file
			PrintWriter writer = new PrintWriter(fileName);

			writer.println(size);

			//For the length of the list
			for (int i = 0; i < size; i++)
			{ 
				writer.println(recordList[i].toString()); //Write a record
			}
			writer.close(); //Close writer
			return true; 
		} 
		//Catch exception
		catch (IOException e1) 
		{
			return false; 
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public HighscoreRecord getRecordList(int i) 
	{
		return recordList[i];
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public void setRecordList(HighscoreRecord[] recordList) 
	{
		this.recordList = recordList;
	}
	
	public String [] fileLoader(String fileName){

		String aRecord[];   // declare String array to return

		try{
			FileReader fr = new FileReader (fileName); // opening the file to read
			BufferedReader inputFile = new BufferedReader (fr);

			int size = Integer.parseInt (inputFile.readLine());// read size

			aRecord = new String[size];  // create String array
			// loop to read file strings into the Array     
			for (int i = 0; i < size; i++){

				// String to read the record line and load array 
				String lineInfo = inputFile.readLine(); 
				aRecord [i] = lineInfo; 
			}
			inputFile.close(); // close the file
		}
		catch(Exception e)
		{
			aRecord = new String [0];  // Set array to zero length if error
		}
		return aRecord;  // return the array
	} // loadFile

	//Self testing
	public static void main(String[] args) 
	{
		//Creating two string record
		String record = "Shahzada,1212,100,5,10"; 
		String record2 = "Shahzada,1999,1000,150,30"; 

		//Creating list object
		HighscoresList list = new HighscoresList(); 

		//Creating record, processing the record and inserting it into the list
		HighscoreRecord scoreInfo = new HighscoreRecord(); 
		scoreInfo.processRecord(record);
		list.insert(scoreInfo); 

		//Creating record, processing the record and inserting it into the list
		HighscoreRecord scoreInfo2 = new HighscoreRecord(); 
		scoreInfo2.processRecord(record2);
		list.insert(scoreInfo2); 

		//testing the sort
		list.insertSort();
		System.out.println(list.toString()); 
		System.out.println(); 

		//Testing flappy
		list.insertSort2(2);
		System.out.println(list.toString()); 


		if (list.delete("Shahzada Gulfam")) 
		{ 
			System.out.println("SUCCESS");
		}
		else 
		{ 
			System.out.println("UNABLE TO FIND RECORD");
		}

		System.out.println(list.toString()); 


	}

}
