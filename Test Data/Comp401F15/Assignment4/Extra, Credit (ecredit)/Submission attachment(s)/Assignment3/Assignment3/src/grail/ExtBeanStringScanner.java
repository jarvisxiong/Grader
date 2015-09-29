package grail;
//this is a expansion version for assignment3
import mp.*;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ScannerBean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"ScannedString"})
@EditablePropertyNames({"ScannedString"})

public class ExtBeanStringScanner implements ScannerInterface{
	private String ScannedString;
	
	public void setScannedString(String str)
	{
		ScannedString=str;
		scanString(str);
	}
	public String getScannedString(){
		return ScannedString;
	}
	
	public void scanString(String line)
	{
		
		char c; 
		int quota=0;// to remember the number of quotas
		int flag=0;//word:1, number:2
		//StringBuffer word=new StringBuffer();
		String word="";
		 for (int i=0;i<line.length();i++)
		 {
			 c=line.charAt(i);
			 if (c=='"')
			 {
				 quota++;
				 if(quota==2)//output word
				 {
					 //do new operation as required in Assignment3
					 QuotedString qs=new QuotedString(word);
					 System.out.println(qs);
					 System.out.println(qs.getInput());
					 word="";
					 //word.delete(0,word.length());
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
				 word+=c;
				 //word.append(c);
			 }
			 else if (Util.isLetter(c))
			 {
				 flag=2;
				 word+=c;
				 //word.append(c); 
			 }
			 else if (c=='{'&&quota==0)
			 {
				 StartToken st=new StartToken(String.valueOf(c));
				 System.out.println(st);
				 System.out.println(st.getInput());
			 }
			 else if (c=='}'&&quota==0)
			 {
				 EndToken et=new EndToken(String.valueOf(c));
				 System.out.println(et);
				 System.out.println(et.getInput());
			 }
			 else if(c==' ')
			 {
				 if (quota==1)
				 {
					 //word.append(c);
					 word+=c;
					 continue;
				 }
				 if (flag==1){
					//do new operation as required in Assignment3
					 mp.Number num=new mp.Number(word);
					 System.out.println(num);
					 System.out.println(num.getInput());
					 System.out.println(num.getValue());
				
					 flag=0;
					 word="";
					 //word.delete(0, word.length());
				 }
				 else if (flag==2)
				 {
					//do new operation as required in Assignment3
					 mp.Word ww=new mp.Word(word);
					 System.out.println(ww);
					 System.out.println(ww.getInput());
					 System.out.println(ww.getValue());
					 flag=0;
					 word="";
					 //word.delete(0, word.length());
				 }else//continuous space after word 
				 {
					 continue;
				 }
			 }
			 else if (c=='+'||c=='-')//we should also ouput "+" or "-"
			 {
				 if (quota==1)
				 {
					 word+=c;
					 //word.append(c);
					 continue;
				 }
				 if (flag==1){
					//do new operation as required in Assignment3
					 mp.Number num=new mp.Number(word);
					 System.out.println(num);
					 System.out.println(num.getInput());
					 System.out.println(num.getValue());
				
					 //int num=Integer.parseInt(word.toString());
					 //System.out.println("number: "+num);
					 //System.out.println("sign: "+c);
					if (c=='+')
					{
					
						Plus pl=new Plus(String.valueOf(c));
						System.out.println(pl);
						System.out.println(pl.getInput());
						
					}else
					{
						Minus pl=new Minus(String.valueOf(c));
						System.out.println(pl);
						System.out.println(pl.getInput());
					}
					flag=0;
					word="";
					 //word.delete(0, word.length());
				 }
				 else if (flag==2)
				 {
					 mp.Word ww=new mp.Word(word);
					 System.out.println(ww);
					 System.out.println(ww.getInput());
					 System.out.println(ww.getValue());
					 
					 if (c=='+')
					{
						Plus pl=new Plus(String.valueOf(c));
						System.out.println(pl);
						System.out.println(pl.getInput());
						
					}else
					{
						Minus pl=new Minus(String.valueOf(c));
						System.out.println(pl);
						System.out.println(pl.getInput());
					}
					 flag=0;
					 word="";
					 //word.delete(0, word.length());
				 }else//continuous space after word 
				 {
					 continue;
				 }
			 }
			 else//other character, for example, symbols
			 {
				 word+=c;
				//word.append(c);
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
				 mp.Number num=new mp.Number(word);
				 System.out.println(num);
				 System.out.println(num.getInput());
				 System.out.println(num.getValue());

				 flag=0;
				 word="";
				 //word.delete(0, word.length());
			 }
			 else if (flag==2)
			 {
				 mp.Word ww=new mp.Word(word);
				 System.out.println(ww);
				 System.out.println(ww.getInput());
				 System.out.println(ww.getValue());

				 flag=0;
				 word="";
				 //word.delete(0, word.length());
			 }
		}
	}

}
