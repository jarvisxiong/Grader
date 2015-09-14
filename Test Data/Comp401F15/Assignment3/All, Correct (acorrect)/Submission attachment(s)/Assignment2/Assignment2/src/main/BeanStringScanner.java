package main;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"ScannerBean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"scannedString"})
@EditablePropertyNames({"scannedString"})

public class BeanStringScanner {
	private String scannedString;
	
	public void setScannedString(String str)
	{
		scannedString=str;
		scanString(str);
	}
	public String getScannedString(){
		return scannedString;
	}
	
	private void scanString(String line)
	{
		
		char c; 
		int quota=0;// to remember the number of quotas
		int flag=0;//word:1, number:2
		StringBuffer word=new StringBuffer();
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
					 System.out.println("number: "+num);
					 flag=0;
					 word.delete(0, word.length());
				 }
				 else if (flag==2)
				 {
					 System.out.println("word: "+word);
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
					 System.out.println("number: "+num);
					 System.out.println("sign: "+c);
					 flag=0;
					 word.delete(0, word.length());
				 }
				 else if (flag==2)
				 {
					 System.out.println("word: "+word);
					 System.out.println("sign: "+c);
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
				 System.out.println("number: "+num);
				 flag=0;
				 word.delete(0, word.length());
			 }
			 else if (flag==2)
			 {
				 System.out.println("word: "+word);
				 flag=0;
				 word.delete(0, word.length());
			 }
		}
	}

}
