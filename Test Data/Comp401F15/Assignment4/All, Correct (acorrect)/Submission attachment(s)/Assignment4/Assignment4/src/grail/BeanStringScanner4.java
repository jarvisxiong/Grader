package grail;
//this is for assignment4


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

import mp.Move;
import mp.Say;
import mp.RotateLeftArm;
import mp.RotateRightArm;
import mp.Repeat;
import mp.Define;
import mp.Call;
import mp.Thread;
import mp.Wait;
import mp.ProceedAll;
import mp.Sleep;
import mp.Undo;
import mp.Redo;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ScannerBean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"scannedString","compactTokenArray","errLog"})
@EditablePropertyNames({"scannedString"})

public class BeanStringScanner4 implements ScannerInterface{
	private String scannedString;
	private RawInput[] largeTokenArray;//not a property
	private RawInput[] compactTokenArray;//read-only property
	private final int maxNUM=100;
	String errLog="";
	private int cnt;
	public BeanStringScanner4()
	{
		//create large array
		//largeTokenArray=new RawInput[maxNUM];//but you have to instantiate it for every specific token 
	}
	public void setScannedString(String str)
	{
		errLog="";
		scannedString=str;
		largeTokenArray=new RawInput[str.length()];//but you have to instantiate it for every specific token 
		
		scanString(str);
		
		compactTokenArray=new RawInput[cnt];//instantiate for each specific token
		for (int i=0;i<cnt;i++)
		{
			compactTokenArray[i]=largeTokenArray[i];
		}
		//create compact array
		
	}
	
	public String getScannedString(){
		return scannedString;
	}
	public RawInput[] getCompactTokenArray()
	{
		return compactTokenArray;
	}
	public String getErrLog()
	{
		return errLog;
	}
	
	public void scanString(String line)
	{
		
		char c; 
		int quota=0;// to remember the number of quotas
		int flag=0;//word:1, number:2
		//StringBuffer word=new StringBuffer();
		String word="";
		cnt=0;//to count number of real tokens, it is a global variable
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
					 //System.out.println(qs);
					 //System.out.println(qs.getInput());
					 largeTokenArray[cnt++]=qs;
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
				 largeTokenArray[cnt++]=st;

				 //System.out.println(st);
				 //System.out.println(st.getInput());
			 }
			 else if (c=='}'&&quota==0)
			 {
				 RawInput et=new EndToken(Util.char2String(c));
				 largeTokenArray[cnt++]=et;

				 //System.out.println(et);
				 //System.out.println(et.getInput());
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
					 largeTokenArray[cnt++]=num;
					 //System.out.println(num);
					 //System.out.println(num.getInput());
					 //ProcNumberValue pnv=new Number(word);
					 //System.out.println(pnv.getValue());
				
					 flag=0;
					 word="";
					 //word.delete(0, word.length());
				 }
				 else if (flag==2)//input a word
				 {
					//do new operation as required in Assignment3
					 //RawInput ww=new Word(word);
					 RawInput ww=subWordConverter(word);
					 largeTokenArray[cnt++]=ww;
					 //System.out.println(ww);
					 //System.out.println(ww.getInput());
					 //ProcWordValue pwv=new Word(word);
					 //System.out.println(pwv.getValue());
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
					 largeTokenArray[cnt++]=num;
					 /*
					 System.out.println(num);
					 System.out.println(num.getInput());
					 ProcNumberValue pnv=new Number(word);
					 System.out.println(pnv.getValue());
				*/
					 //int num=Integer.parseInt(word.toString());
					 //System.out.println("number: "+num);
					 //System.out.println("sign: "+c);
					 /*
					if (c=='+')
					{
					
						RawInput pl=new Plus(Util.char2String(c));
						largeTokenArray[cnt++]=pl;
						//System.out.println(pl);
						//System.out.println(pl.getInput());
						
					}else
					{
						RawInput pl=new Minus(Util.char2String(c));
						 largeTokenArray[cnt++]=pl;
						//System.out.println(pl);
						//System.out.println(pl.getInput());
					}*/
					flag=0;
					word="";
					 //word.delete(0, word.length());
				 }
				 else if (flag==2)//input is a Word
				 {
					 //RawInput ww=new Word(word);
					 //System.out.println(ww);
					 //System.out.println(ww.getInput());
					 //ProcWordValue pwv=new Word(word);
					 //System.out.println(pwv.getValue());
					 RawInput ww=subWordConverter(word);
					 largeTokenArray[cnt++]=ww;
					 /*
					 if (c=='+')
					{
						RawInput pl=new Plus(Util.char2String(c));
						 largeTokenArray[cnt++]=pl;
						//System.out.println(pl);
						//System.out.println(pl.getInput());
						
					}else
					{
						RawInput pl=new Minus(Util.char2String(c));
						 largeTokenArray[cnt++]=pl;
						//System.out.println(pl);
						//System.out.println(pl.getInput());
					}*/
					 flag=0;
					 word="";
					 //word.delete(0, word.length());
				 }else//continuous space after word 
				 {
					 continue;
				 }
				 if (c=='+')
				 {
					RawInput pl=new Plus(Util.char2String(c));
				    largeTokenArray[cnt++]=pl;
					//System.out.println(pl);
					//System.out.println(pl.getInput());
						
				}else
				{
					RawInput pl=new Minus(Util.char2String(c));
					 largeTokenArray[cnt++]=pl;
					//System.out.println(pl);
					//System.out.println(pl.getInput());
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
			//System.out.println("closed quota is missing.....");
			errLog+="closed quota is missing...and the string is "+word+"\n";
			//System.out.println(word);
		}
		else//output the last word
		{
			 if (flag==1){
				 RawInput num=new Number(word);
				 //System.out.println(num);
				 //System.out.println(num.getInput());
				 //ProcNumberValue pnv=new Number(word);
				 //System.out.println(pnv.getValue());
				 largeTokenArray[cnt++]=num;

				 flag=0;
				 word="";
				 //word.delete(0, word.length());
			 }
			 else if (flag==2)//input is a Word
			 {
				 //RawInput ww=new Word(word);
				 //System.out.println(ww);
				 //System.out.println(ww.getInput());
				 //ProcWordValue pwv=new Word(word);
				 //System.out.println(pwv.getValue());
				 
				 RawInput ww=subWordConverter(word);
				 largeTokenArray[cnt++]=ww;
				 flag=0;
				 word="";
				 //word.delete(0, word.length());
			 }
		}
	}
	
	//this method returns the specific word object: move->Move...
	private RawInput subWordConverter(String word)
	{
		RawInput ww=null;
		String temp=word.toLowerCase();
		switch (temp){
		case "move":
			ww=new Move(word);
			break;
		case "say":
			ww=new Say(word);
			break;
		case "rotateleftarm":
			ww=new RotateLeftArm(word);
			break;
		case "rotaterightarm":
			ww=new RotateRightArm(word);
			break;
		case "repeat":
			ww=new Repeat(word);
			break;
		case "define":
			ww=new Define(word);
			break;
		case "call":
			ww=new Call(word);
			break;
		case "thread":
			ww=new Thread(word);
			break;
		case "wait":
			ww=new Wait(word);
			break;
		case "proceedall":
			ww=new ProceedAll(word);
			break;
		case "sleep":
			ww=new Sleep(word);
			break;
		case "undo":
			ww=new Undo(word);
			break;
		case "redo":
			ww=new Redo(word);
			break;
		default:
			ww= new Word(word);
			break;
		}
		return ww;
	}

}
