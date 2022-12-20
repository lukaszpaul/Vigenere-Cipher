import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class VigenereCypher {
	
	
	public static char plain[] = { 'a','b','c','d', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z' };
	
	

	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner scnr = new Scanner(System.in); //new scanner for text input
		
		
			System.out.println("What would you like to encrypt? "); //prompt
			String toEncrypt = scnr.nextLine();
			System.out.println("What is the Key?");
			String key = scnr.nextLine();
			char[][] table = generateTable();
			
			String repeated = generateKey(toEncrypt,key);
			System.out.println("\n"+ toEncrypt+ "\n"+ repeated);
			
			System.out.println("\n" + " The Encrypted Text IS: " + Encrypt(toEncrypt, repeated, table));
			System.out.println("\n" +"What would you like to decrypt? ");
			String toDecrypt = scnr.nextLine();
			String ciphertext = Decrypt(toDecrypt, repeated, table);
			System.out.println(ciphertext);



		
		
			System.out.println("\n" +"Hello would you like to run letter frequency?");
			String answer = scnr.nextLine();
			answer = answer.toLowerCase();
			if(answer.equals("yes"))
				Attack(table, key);
		
			Guesser();	
			
	}
	
	
	
	public static String generateKey(String plain, String key)
	{
	    int x = plain.length();
	    for (int i = 0; ; i++)
	    {
	        if (x == i)
	            i = 0;
	        if (key.length() == plain.length())
	            break;
	        key+=(key.charAt(i));
	    }
	    return key;
	}

	
	
	private static char[][] generateTable() {
		char[][] table = new char[plain.length][plain.length];
		int lettr;
		for(int i = 0; i < plain.length; i++){
			for(int j = 0; j < plain.length; j++){
				lettr = i+j;
				if(lettr >= plain.length){
					lettr = lettr - plain.length;
				}
				table[i][j] = plain[lettr];
			}
		}
		return table;
	}
	

	
	
	
	
//Encrypts User Input of Plaintext
	public static String Encrypt(String plaintext, String repeated, char[][] table)
	{
		plaintext = plaintext.toLowerCase(); // changes plaintext to lowercase 
		plaintext = plaintext.replaceAll("\s",""); //removes all spaces from plaintext
		plaintext = plaintext.replaceAll("[0-9]","");
		plaintext = plaintext.replaceAll("[^a-zA-Z0-9]", "");
		ArrayList<Character> Encrypted = new ArrayList<Character>();
		
		for(int i = 0; i < plaintext.length(); i++)
		{
			int r=0;
			for(int row = 0; row<26; row++)
			{
				if(table[0][row] == plaintext.charAt(i))
				{
					r = row;
				}
			}
			int c = 0;
			for(int column = 0; column<26; column++)
			{
				if(table[column][0] == repeated.charAt(i))
				{
					c = column;
				}
			}
			
			Encrypted.add(table[r][c]);
			
			
			
		}
		StringBuilder result = new StringBuilder(Encrypted.size());
		for (Character c : Encrypted) {
		  result.append(c);
		}
		String encrypted = result.toString();		
		return encrypted;
		
	}
	
	
	
	
	
	//Decrypts user input of ciphertext 
	public static String Decrypt(String encrypted, String repeated, char[][]table)
	{
		ArrayList<Character> Decrypted = new ArrayList<Character>();
		int row=0;
		int col=0;
		
		for(int i =0; i< encrypted.length(); i++)
		{
			for(int x =0; x<26;x++)
			{
				if(table[0][x] == repeated.charAt(i))
				{
					row = x;
					
					for(int v =0; v<26;v++)
					{
						if (table[v][x] == encrypted.charAt(i))
								{
									col = v;
								}
					}
				}
				}
			
			Decrypted.add(table[0][col]);
		}
		
		StringBuilder result = new StringBuilder(Decrypted.size());
		for (Character c : Decrypted) {
		  result.append(c);
		}
		String decrypted = result.toString();		
		return decrypted;
		
		
	}
	
	
	
	public static void Attack( char[][] table,String key) throws FileNotFoundException
	{
		File file = new File("/Users/lukepaul/Desktop/enwik9.txt"); 

		Scanner scan = new Scanner(file);
		
		String fileContent= "";
		while(scan.hasNextLine())
		{
			fileContent = fileContent.concat(scan.nextLine() + "\n");
		}
		
		String repeated = generateKey(fileContent,key);
		fileContent= Encrypt(fileContent, repeated, table);
		char[] fileChar = fileContent.toCharArray();
		double[] lcount = new double[26];
		
		for(char x : fileChar)
		{
			for(int i = 0; i < plain.length; i++)
			{
				if(plain[i] == x)
					lcount[i]++;
			}
		}
		
		for(int i =0; i<26;i++)
		{
			System.out.println(plain[i] + " " + (lcount[i]*100)/fileContent.length() + "%");
		}
		
	}
	
	
	
	public static void Guesser() 
	{
		Scanner scnr1 = new Scanner(System.in);

		System.out.println("What code would you like to attempt to decrypt?");
		String attempt = scnr1.nextLine();
		
		int choice = 0;
		while(choice != 2)
		{	
		System.out.println("What is the letter in the Encryption you want to change?");
		char change = scnr1.next().charAt(0);
		System.out.println("What letter from the Frequency Chart do you want to use to replace that letter?");
		String hold = scnr1.next();
		hold = hold.toUpperCase();
		char toChange = hold.charAt(0);
		
		attempt = attempt.replace(change,toChange);
		
		System.out.println(attempt);
		System.out.println("To Try Again Enter 1, to Quit Enter 2");
		 choice = scnr1.nextInt();
	} 
		
		
	}

		
		
	}
