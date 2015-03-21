package DataType;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

abstract public class Problem {
	protected String question; // 문제
//	protected List<PartialString> partialString = new ArrayList<PartialString>(); // 부분 문장
//	protected List<String> examples = new ArrayList<String>(); // 보기
	protected int result;
	//--------------------------------
	protected Map<String, String> partialString = new TreeMap<String, String>(); // 부분 문장
	protected Map<String, List<String>> examples = new TreeMap<String, List<String>>(); // 보기
	
	//--------------------------------//
	
	abstract public boolean check(); // 정답 체크 
	abstract public String getProblem(); // 해당 문제 문자열을 반환하는 메소드
	abstract public String getResult(int selectNumber);	//결과 출력할 문자열
	
	public String getExamples(int loc)
	{
		String result = new String();
		List<String> temp = this.examples.get(Integer.toString(loc+1));
		
		for(int i = 0 ; i < temp.size() ; i++)
			if(i == temp.size()-1)
				result = result.concat(temp.get(i));
			else
				result = result.concat(temp.get(i)+"-");

		return result;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setPartialString(String label, String partialString) {
		this.partialString.put(label, partialString);
	}
	
	public void setPartialString(Map<String, String> partialString) {
		this.partialString = partialString;
	}
	
	public void setExamples(String seqMark, List<String> ex) {
		this.examples.put(seqMark, ex);
	}
	
	public void setExamples(Map<String, List<String>> examples) {
		this.examples = examples;
	}
}
