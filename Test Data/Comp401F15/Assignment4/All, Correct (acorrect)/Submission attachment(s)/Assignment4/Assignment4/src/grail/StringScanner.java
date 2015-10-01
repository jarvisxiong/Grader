package grail;
//this is for assignment2
public class StringScanner {

	public static void myScanner(String line)
	{
		
		char c; 
		int quota=0;// to remember the number of quotas
		int flag=0;//word:1, number:2
		StringBuffer word=new StringBuffer();
		
		String numberPreToken="number: ";
		String wordPreToken="word: ";
		String signPreToken="sign: ";
		 for (int i=0;i<line.length();i++)
		 {
			 c=line.charAt(i);
			 if (c=='"')
			 {
				 quota++;
				 if(quota==2)//output word
				 {
					 System.out.println("quoted string: "+word);
					 word.delete(0,word.length());
					 flag=0;
					 quota=0;
				 }else if (quota==1)
				 {
					//donothing, not allow word or number to output
				 }else
				 {
					 continue;
				 }
			 }
			 else if (Character.isDigit(c))
			 {
				 flag=1;
				 word.append(c);
			 }
			 else if (Character.isLetter(c))
			 {
				 flag=2;
				 word.append(c); 
			 }
			 else if(c==' ')
			 {
				 if (quota==1)
				 {
					 word.append(c);
					 continue;
				 }
				 if (flag==1){
					 int num=Integer.parseInt(word.toString());
					 System.out.println(numberPreToken+num);
					 flag=0;
					 word.delete(0, word.length());
				 }
				 else if (flag==2)
				 {
					 System.out.println(wordPreToken+word);
					 flag=0;
					 word.delete(0, word.length());
				 }else//continuous space after word 
				 {
					 continue;
				 }
			 }
			 else if (c=='+'||c=='-')//we should also ouput "+" or "-"
			 {
				 if (quota==1)
				 {
					 word.append(c);
					 continue;
				 }
				 if (flag==1){
					 int num=Integer.parseInt(word.toString());
					 System.out.println(numberPreToken+num);
					 System.out.println(signPreToken+c);
					 flag=0;
					 word.delete(0, word.length());
				 }
				 else if (flag==2)
				 {
					 System.out.println(wordPreToken+word);
					 System.out.println(signPreToken+c);
					 flag=0;
					 word.delete(0, word.length());
				 }else//continuous space after word 
				 {
					 continue;
				 }
			 }
			 else//other character, for example, symbols
			 {
				word.append(c);
			 }
		 }
		if (quota==1)
		{
			System.out.println("closed quota is missing.....");
			System.out.println(word);
		}
		else//output the last word
		{
			 if (flag==1){
				 int num=Integer.parseInt(word.toString());
				 System.out.println(numberPreToken+num);
				 flag=0;
				 word.delete(0, word.length());
			 }
			 else if (flag==2)
			 {
				 System.out.println(wordPreToken+word);
				 flag=0;
				 word.delete(0, word.length());
			 }
		}
	}
}
