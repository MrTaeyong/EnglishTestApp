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
		String enter = new String("<br>");
		
		result = result.concat("<b>"+super.question+"</b>" + enter + enter);
		
		Iterator<String> is = super.partialString.keySet().iterator();
		while(is.hasNext()) {
			String temp = is.next();
			if(temp.length() > 1) {
				if('A' <= temp.charAt(1) && temp.charAt(1) <= 'Z')
					result = result.concat(temp + super.partialString.get(temp) + enter);
				else
					result = result.concat(super.partialString.get(temp) + enter + enter);
			} else {
				result = result.concat(temp + super.partialString.get(temp));
			}
		}
		return result;
	}
	
	@Override
	public String getResult(int selectNumber) {
		// TODO Auto-generated method stub
		String result = super.partialString.get("(0)");
		selectNumber++;
		result = result + "<br>" +
				super.partialString.get(super.examples.get(Integer.toString(selectNumber)).get(0))
				+ "<br>" +super.partialString.get(super.examples.get(Integer.toString(selectNumber)).get(1))
				+ "<br>" +super.partialString.get(super.examples.get(Integer.toString(selectNumber)).get(2));
		return result;
	}
}
