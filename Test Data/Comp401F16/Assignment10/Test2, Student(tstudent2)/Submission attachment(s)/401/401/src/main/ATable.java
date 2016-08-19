package main;

import mp.Avatar;
import util.annotations.Tags;

@Tags({"Table"})
public class ATable implements Table {
	private String[] key;
	private Object[] val;
	private String y = "sure";
	
	public ATable(){
		key = new String[1];
		val = new Object[1];
		
	}
	
	public void put(String key, Object val){
		if(key == null || val == null){
			// Do nothing
		}else{
		String[] k = new String[this.key.length + 1];
		Object[] v = new Object[this.key.length + 1];
		for(int i = 0; i < this.key.length; i++){
			k[i] = this.key[i];
			v[i] = this.val[i];
			}
		k[this.key.length] = key;
		v[this.key.length] = val;
		
		this.key = k;
		this.val = v;
		}
	}
	
	public Object get(String output){
		int n = 0;
		for(int i = 0; i < this.key.length; i++){
			if(output.equalsIgnoreCase(this.key[i])){
				n = i;
			}else{
				y = Integer.toString(n);
			}
		}
		if(y != "sure" && n == 0){
			return null;
		}else{
			return val[n];
		}
	}

}
