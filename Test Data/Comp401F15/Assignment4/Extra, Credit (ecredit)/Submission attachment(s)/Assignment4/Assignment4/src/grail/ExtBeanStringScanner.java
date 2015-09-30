package grail;
//this is a expansion version for assignment3
import mp.EndToken;
import mp.Number;
import mp.Minus;
import mp.Plus;
import mp.ProcNumberValue;
import mp.QuotedString;
import mp.RawInput;
import mp.StartToken;
import mp.Word;
import mp.ProcWordValue;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ExtBeanStringScanner"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"scannedString"})
@EditablePropertyNames({"scannedString"})

//public class ExtBeanStringScanner implements ScannerInterface{//because I have modified the scannerInterface 
public class ExtBeanStringScanner{
	private String scannedString;
	
	public void setScannedString(String str)
	{
		scannedString=str;
		scanString(str);
	}
	public String getScannedString(){
		return scannedString;
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
					 RawInput qs=new QuotedString(word);
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
				 RawInput st=new StartToken(Util.char2String(c));
				 System.out.println(st);
				 System.out.println(st.getInput());
			 }
			 else if (c=='}'&&quota==0)
			 {
				 RawInput et=new EndToken(Util.char2String(c));
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
					 RawInput num=new Number(word);
					 System.out.println(num);
					 System.out.println(num.getInput());
					 ProcNumberValue pnv=new Number(word);
					 System.out.println(pnv.getValue());
				
					 flag=0;
					 word="";
					 //word.delete(0, word.length());
				 }
				 else if (flag==2)
				 {
					//do new operation as required in Assignment3
					 RawInput ww=new Word(word);
					 System.out.println(ww);
					 System.out.println(ww.getInput());
					 ProcWordValue pwv=new Word(word);
					 System.out.println(pwv.getValue());
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
					 RawInput num=new Number(word);
					 System.out.println(num);
					 System.out.println(num.getInput());
					 ProcNumberValue pnv=new Number(word);
					 System.out.println(pnv.getValue());
				
					 //int num=Integer.parseInt(word.toString());
					 //System.out.println("number: "+num);
					 //System.out.println("sign: "+c);
					if (c=='+')
					{
					
						RawInput pl=new Plus(Util.char2String(c));
						System.out.println(pl);
						System.out.println(pl.getInput());
						
					}else
					{
						RawInput pl=new Minus(Util.char2String(c));
						System.out.println(pl);
						System.out.println(pl.getInput());
					}
					flag=0;
					word="";
					 //word.delete(0, word.length());
				 }
				 else if (flag==2)
				 {
					 RawInput ww=new Word(word);
					 System.out.println(ww);
					 System.out.println(ww.getInput());
					 ProcWordValue pwv=new Word(word);
					 System.out.println(pwv.getValue());
					 
					 if (c=='+')
					{
						RawInput pl=new Plus(Util.char2String(c));
						System.out.println(pl);
						System.out.println(pl.getInput());
						
					}else
					{
						RawInput pl=new Minus(Util.char2String(c));
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
				 RawInput num=new Number(word);
				 System.out.println(num);
				 System.out.println(num.getInput());
				 ProcNumberValue pnv=new Number(word);
				 System.out.println(pnv.getValue());

				 flag=0;
				 word="";
				 //word.delete(0, word.length());
			 }
			 else if (flag==2)
			 {
				 RawInput ww=new Word(word);
				 System.out.println(ww);
				 System.out.println(ww.getInput());
				 ProcWordValue pwv=new Word(word);
				 System.out.println(pwv.getValue());
				 flag=0;
				 word="";
				 //word.delete(0, word.length());
			 }
		}
	}

}
