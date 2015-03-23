import java.util.Scanner;
/******************************************************************
 * Program or Assignment #: Assignment 4
 *
 * Programmer: Navaneet
 *
 * Due Date: N/A
 *
 * COMP110-002, Spring 2014       Instructor: Prof. Jay Aikat
 *
 * Pledge: I have neither given nor received unauthorized aid
 *         on this program. 
 *
 * Description: Stores and runs computations on Gerbil Lab data
 * 
 * Input: A string
 *
 * Output: prompts and averages or search result
 *
 ******************************************************************/


public class main {

	static Gerbil[] allGerbils;
	static String[][] foods;

	public static void main(String[] args) {

		String function;

		do {
			
			function = "";
			Scanner key = new Scanner(System.in);
			
			System.out.println("How many food items do the gerbils eat?");
			String number = key.nextLine();
			int numberFood = Integer.parseInt(number);

			//2-d array with name as the first column and maximum food consumed as the second column
			foods = new String[numberFood][2];

			for (int i = 0; i < foods.length; i++)
			{
				System.out.println("Name of food item " + (i+1));
				foods[i][0] = key.nextLine();
				System.out.println("Maximum consumed per gerbil: ");
				foods[i][1] = key.nextLine();
			}

			System.out.println("How many gerbils are in the lab?");
			String num = key.nextLine();
			int numGerbils = Integer.parseInt(num);

			allGerbils = new Gerbil[numGerbils];

			//Set the properties of every Gerbil
			for (int i = 0; i < numGerbils; i++)
			{
				allGerbils[i] = new Gerbil();
				
				//Check for duplicate lab ids
				boolean duplicate = true;
				
				while (duplicate)
				{
					System.out.println("Gerbil " + (i+1) + "'s lab id:");
					String newId = key.nextLine();
					
					boolean duplicateId = false;
					for (int j = 0; j < i; j++)
					{
						//If the lab id entered equals an existing lab id
						if (newId.equalsIgnoreCase(allGerbils[j].getId()))
						{
							duplicateId = true;
							break;
						}
					}
					
					//If it is not a duplicate, set the id
					if (duplicateId == false)
					{
						allGerbils[i].setId(newId);
						duplicate = false;
					}
					
					else
						System.out.println("The ID entered already exists. Please try again.");
				}
				
				
				System.out.println("What name did the undergrads give to " + allGerbils[i].getId() + "?");
				allGerbils[i].setName(key.nextLine());

				//Set the number of foods that exist
				allGerbils[i].setSize(foods.length);
				
				//Take an amount for every food
				for (int j = 0; j < foods.length; j++)
				{
					//Check to make sure that the amount entered is less than the maximum amount allowed
					boolean lessThan = false;
					
					while (!lessThan)
					{
						System.out.println(allGerbils[i].getId() + " eats how many " + foods[j][0] + "s per day?");
						int numFood = Integer.parseInt(key.nextLine());
						
						//If the amount entered is less than the maximum amount allowed then set it.
						if (numFood <= Integer.parseInt(foods[j][1]))
						{
							lessThan = true;
							allGerbils[i].setNumbers(j, numFood);
						}
						
						//Else print an error message and ask the user to try again.
						else
						{
							System.out.println("The amount entered exceeds the maximum amount allowed for that food. Please try again.");
						}
					}
					
				}
				
				
				//Check to make sure that the user entered true or false as an input
				boolean bite = false;
				
				while (!bite)
				{
					System.out.println("Does " + allGerbils[i].getId() + " bite?");
					String biteValue = key.nextLine();
					
					//If it equals true or false, set it as the value.
					if (biteValue.equalsIgnoreCase("true") || biteValue.equalsIgnoreCase("false"))
					{
						allGerbils[i].setBite(Boolean.parseBoolean(biteValue));
						bite = true;
					}
					
					//Else print an error message and ask the user to try again.
					else
					{
						System.out.println("You did not enter a valid input. Please enter true or false");
					}
				}
				
				
				//Check to make sure that the user entered true or false as an input
				boolean escape = false;
				
				while (!escape)
				{
					System.out.println("Does " + allGerbils[i].getId() + " try to escape?");
					String escapeValue = key.nextLine();
					
					//If the user entered true or false, set the value.
					if (escapeValue.equalsIgnoreCase("true") || escapeValue.equalsIgnoreCase("false"))
					{
						allGerbils[i].setEscape(Boolean.parseBoolean(escapeValue));
						escape = true;
					}
					
					//Else print an error message and ask the user to try again.
					else
					{
						System.out.println("You did not enter a valid input. Please enter true or false");
					}
				}
			}

			//Prompt the user repeatedly until they enter 'quit' or 'restart'
			while (!function.equals("quit") && !function.equals("restart"))
			{
				System.out.println("What information would you like to know?");
				function = key.nextLine();

				if (function.equals("average"))
				{
					System.out.println(averageFood());
				}

				else if (function.equals("search"))
				{
					System.out.println("lab id to search for:");
					Gerbil result = searchForGerbil(key.nextLine());

					//Result equals null when the lab id was not found.
					if (result == null)
					{
						System.out.println("ERROR: this gerbil does not exist");
					}

					//If the lab id is found, concatenate the properties of the lab id and print it
					else
					{
					String searchResult = "";

					searchResult += result.getName() + " ("
							+ result.getEscape() + ", " + result.getBite()
							+ "), Food:";

					for (int k = 0; k < foods.length; k++)
					{
						searchResult += " " + foods[k][0] + " - " + result.getFoodNumbers()[k]
								+ "/" + foods[k][1];
					}

					System.out.println(searchResult);
					}
				}

				//If the command entered is not valid, print an error message and ask the user to try again.
				else if (!function.equals("quit") && !function.equals("restart"))
				{
					System.out.println("ERROR. That is not a valid command. Try again.");
				}

			}

		}
		while (function.equals("restart"));
		//Repeat the process if the user entered 'restart' and quit if the user entered 'quit'.
	}

	public static String averageFood()
	{
		String finalAverage = "";

		int totalSum = 0;

		//Take the sum of the maximum amounts of all the foods
		for (int i = 0; i < foods.length; i++)
		{
			totalSum += Integer.parseInt(foods[i][1]);
		}

		//Alphabetically sort all of the Gerbils by lab id
		for (int i = 0; i < allGerbils.length; i++)
		{
			for (int j = i; j < allGerbils.length; j++)
			{
				//Take the lab id of smaller length and use that in the inner for loop.
				int length;
				if (allGerbils[j].getId().length() <= allGerbils[i].getId().length())
				{
					length = allGerbils[j].getId().length();
				}
				
				else
				{
					length = allGerbils[i].getId().length();
				}
				
				//Compare every character of the two lab ids and swap when necessary.
				for (int k = 0; k < length; k++)
				{
					if (allGerbils[i].getId().charAt(k) > allGerbils[j].getId().charAt(k))
					{
						Gerbil temp = allGerbils[i];
						allGerbils[i] = allGerbils[j];
						allGerbils[j] = temp;
						break;
					}
					
					else if (allGerbils[i].getId().charAt(k) < allGerbils[j].getId().charAt(k))
						break;
					
					//If there has been no swap and every character has been the same thusfar, then
					//keep the lab id with fewer characters at the lower index in the array.
					//For example, if ID1 = 'hi' and ID2 = 'hii', then ID1 comes before ID2.
					else if (k == length -1)
					{
						if (allGerbils[i].getId().length() < allGerbils[j].getId().length())
						{
							break;
						}
						
						else
						{
							Gerbil temp = allGerbils[i];
							allGerbils[i] = allGerbils[j];
							allGerbils[j] = temp;
							break;
						}
					}
					
				}
			}
		}
		
		//Concatenate every Gerbil's ID, name, and average and return the result.
		for (int i = 0; i < allGerbils.length; i++)
		{
			int gerbilSum = 0;
			
			for (int j = 0; j < allGerbils[i].getFoodNumbers().length; j++)
			{
				gerbilSum += allGerbils[i].getFoodNumbers()[j];
			}
			
			int average = (int) Math.round(((double)gerbilSum / (double)totalSum) * 100);
			finalAverage += allGerbils[i].getId() + " (" + allGerbils[i].getName()
					+ ") " + average + "%\n";

		}

		return finalAverage.trim();
	}

	public static Gerbil searchForGerbil (String idName)
	{	
		//Go through every Gerbil in the array to check if the searched id exists.
		for (int i = 0; i < allGerbils.length; i++)
		{
			if (idName.equals(allGerbils[i].getId()))
			{
				return allGerbils[i];
			}
		}
		
		//Return null if the id is not found.
		return null;
	}
}
