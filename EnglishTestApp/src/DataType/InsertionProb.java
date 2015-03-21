package DataType;

import java.util.Iterator;

public class InsertionProb extends Problem {
	public InsertionProb() {
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
		return result;
	}
	
	public String getResult(int selectNumber) {
		// TODO Auto-generated method stub
	
		String exam,result = ""
				;
		Iterator it = super.partialString.keySet().iterator();
		exam = super.partialString.get("(0)");
		
		selectNumber++;	
		it.next();	
		while(it.hasNext()) {
			String key = it.next().toString();
			if(key.equals(super.examples.get(String.valueOf(selectNumber)).get(0)))
			{
				if(result.length() != 0)
					result = result + "<br>" + "<font color = \"red\">" + "<B>" +exam +"</B>" + "</font>";
				else
					result =  "<br>" + "<font color = \"red\">" + "<B>" +exam +"</B>" + "</font>";
			}
			result = result + "<br>" + super.partialString.get(key);
		}		
		return result;
	}

}
