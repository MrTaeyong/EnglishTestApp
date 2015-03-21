package DataType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class SortProb extends Problem {
	public SortProb() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		String result = new String();
		String enter = new String("\r\n");
		
		result = result.concat(super.question + enter + enter);
		
		Iterator<String> is = super.partialString.keySet().iterator();
		while(is.hasNext()) {
			String temp = is.next();
			if(temp.length() > 1) {
				if('A' <= temp.charAt(1) && temp.charAt(1) <= 'Z')
					result = result.concat(temp + super.partialString.get(temp));
				else
					result = result.concat(super.partialString.get(temp));
			} else {
				result = result.concat(temp + super.partialString.get(temp));
			}
		}
		LinkedHashMap<String, String> aa = new LinkedHashMap<String, String>();
		aa.put("a", "ssw1");
		aa.put("c", "ssw3");
		aa.put("b", "ssw2");
		//System.out.println(aa.toString());
		Iterator<String> is1 = aa.keySet().iterator();
		while(is1.hasNext()) {
			System.out.println(is1.next());
		}
		return result;
	}
	
	@Override
	public String getResult(int selectNumber) {
		// TODO Auto-generated method stub
		String result = super.partialString.get("(0)");
		selectNumber++;
		result = result + super.partialString.get(super.examples.get(Integer.toString(selectNumber)).get(0))
				+super.partialString.get(super.examples.get(Integer.toString(selectNumber)).get(1))
				+super.partialString.get(super.examples.get(Integer.toString(selectNumber)).get(2));
		return result;
	}
}
