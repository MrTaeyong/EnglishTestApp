package DataType;

import java.util.Iterator;

public class RelationlessProb extends Problem {
	public RelationlessProb() {
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
				if(('A' <= temp.charAt(1) && temp.charAt(1) <= 'Z') || 
						('a' <= temp.charAt(1) && temp.charAt(1) <= 'z'))
					result = result.concat(" " + temp + super.partialString.get(temp));
				else
					result = result.concat(super.partialString.get(temp));
			} else {
				result = result.concat(" " + temp + super.partialString.get(temp));
			}
		}
		return result;
	}
	
	@Override
	public String getResult(int selectNumber) {
		// TODO Auto-generated method stub
		selectNumber++;
		String exam = super.examples.get(Integer.toString(selectNumber)).get(0);
		String result = "";
		Iterator it = super.partialString.keySet().iterator();	
		while(it.hasNext()) {
			String key = it.next().toString();
			if(!key.equals(exam))
			{
				result = result + "<br>"+ super.partialString.get(key);

			}
			
		}	
		
		return result;
	}

}
