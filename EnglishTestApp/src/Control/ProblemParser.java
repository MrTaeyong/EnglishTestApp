package Control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import DataType.InsertionProb;
import DataType.Problem;
import DataType.RelationlessProb;
import DataType.SortProb;

public class ProblemParser {
	private final int NONE_STATUS = 52;
	private final int START_STATUS = 53;
	private final int END_STATUS = 54;
	private final int STRING_BUILDER_SIZE = 1000;
	private int labelNumber = 0;
	
	public List<Problem> parse() throws Exception {
		try {
			return parse(new File("tutorial.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Problem> parse(File probFile) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(probFile));
		List<Problem> result = new ArrayList<Problem>();
		StringBuilder buf = new StringBuilder(STRING_BUILDER_SIZE);
		int readedChar = 0;
		int taggingFlag = NONE_STATUS;
		
		// 문제, 지문, 보기 임시 변수
		String question = null;
		Map<String, String> partialString = new TreeMap<String, String>();
		Map<String, List<String>> examples = new TreeMap<String, List<String>>();
		int correctAnswer = 0;
		
		try {
			while((readedChar = reader.read()) != -1) {				
				if( readedChar == '<' ) {
					taggingFlag = START_STATUS; // 이제부터 태그 읽기 시작
				}
				else if( readedChar == '>' ) {
					String temp = buf.substring(buf.length() - 2, buf.length());
					
					if( temp.equals("/Q") ) { // 문제
						buf.delete(buf.length() - 3, buf.length()); // </Q 문자열을 지움
						question = buf.toString();
					}
					else if( temp.equals("/S") ) { // 지문
						buf.delete(buf.length() - 3, buf.length()); // </S 문자열을 지움
						partialString.putAll(processSentence(buf.toString()));
						
					}
					else if( temp.equals("/N") ) { // 보기
						buf.delete(buf.length() - 3, buf.length()); // </N 문자열을 지움
						String ex = buf.toString().toUpperCase();
						examples.put(ex.split("\\.")[0], splitLabelOfExample(ex));
					}
					else if( temp.equals("/C") ) { // 정답
						buf.delete(buf.length() - 3, buf.length()); // </C 문자열을 지움
						correctAnswer = Integer.parseInt(buf.toString());
					}
					else if( temp.equals("/E") ) { // 한 문제 끝
						result.add(generateProblemInstance(question, partialString, examples, correctAnswer));
						question = null;
						partialString = new TreeMap<String, String>();
						examples = new TreeMap<String, List<String>>();
						correctAnswer = 0;
						labelNumber = 0;
					}
					
					taggingFlag = END_STATUS; // 태그 읽기 끝난 상태
					buf.delete(0, buf.length()); // 버퍼 비움
					continue;
				}
				
				if( readedChar == ' ' && taggingFlag == START_STATUS )
					continue; // 태그에 있는 공백은 무시
				
				buf.append((char) readedChar);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * 문제에서 문제유형별 특정 문자열을 검출하여 문제유형에 맞는 객체를 생성
	 * @param question
	 * @param partialString
	 * @param examples
	 * @return
	 */
	private Problem generateProblemInstance(String question, Map<String, String> partialString, Map<String, List<String>> examples, int correctAnswer) {
		Problem result = null;
		if( question.contains("들어") && question.contains("적절") )
			result = new InsertionProb();
		else if( question.contains("흐름") && question.contains("관계없는") || question.contains("관계 없는") )
			result = new RelationlessProb();
		else if( question.contains("순서") && question.contains("적절") )
			result = new SortProb();
		else
			return null;
		
		result.setQuestion(question);
		result.setPartialString(partialString);
		result.setExamples(examples);
		result.setCorrectAnswer(correctAnswer);
		
		return result;
	}
	
	/**
	 * 지문을 부분 문자열로 나누고 나눠진 각 문자열에 레이블을 부여함
	 * @param sentence
	 * @return
	 */
	private Map<String, String> processSentence(String sentence) {
		Map<String, String> result = new TreeMap<String, String>();
		StringBuilder buf = new StringBuilder(STRING_BUILDER_SIZE);
		StringBuilder labelBuf = new StringBuilder(10);
		boolean labeling = false;
		char temp = 0;
		
		for( int i = 0; i < sentence.length(); i++ ) {
			temp = sentence.charAt(i);
			if( temp == '(' || (temp >= 9461 && temp <= 9470) ) { // 9461 ~ 9470은 원 숫자 처리..
				if( buf.length() > 0 ) { // 새로운 레이블이 나왔을 때 버퍼가 차있으면 앞의 부분문자열 처리
					if( labelBuf.length() > 0 )
						result.put(labelBuf.toString(), buf.toString().trim());
					else
						result.put("(" + String.valueOf(labelNumber++) + ")", buf.toString().trim());
					
					labelBuf.delete(0, labelBuf.length());
					buf.delete(0, buf.length());
				}
				labeling = true; // 레이블 일기 시작
			}
			else if( temp == ')' ) {
				labelBuf.append(temp);
				labelBuf.replace(0, labelBuf.length(), labelBuf.toString().replaceAll(" ", "").toUpperCase());
				labeling = false; // 레이블 읽기 종료
				continue;
			}
			
			if( labeling ) { // 레이블을 읽는 중이면 문자를 레이블 버퍼에 추가
				if( temp == ' ')
					continue;
				labelBuf.append(temp);
				if( temp >= 9461 && temp <= 9470 )
					labeling = false; // 원 숫자는 ')' 문자가 나오지 않으므로 여기서 종료 처리
			}
			else // 레이블이 아닌 지문일 때 문자를 일반 버퍼에 추가
				buf.append(temp);
		}
		
		// 모든 처리 이후에 버퍼에 남은 문자열 처리
		if( labelBuf.length() > 0 ) {
			result.put(labelBuf.toString(), buf.toString());
		}
		else if( buf.length() > 0 ) {
			result.put("(" + String.valueOf(labelNumber++) + ")", buf.toString());
		}
		
		return result;
	}
	
	/**
	 * 보기에서 숫자와 문자열을 나눔.
	 * 글의 순서를 맞추는 문제유형을 위해 숫자 이후에 나오는 레이블을 연결리스트로 처리.
	 * @param example
	 * @return
	 */
	private List<String> splitLabelOfExample(String example) {
		String[] token = example.split("\\.");
		List<String> result = new LinkedList<String>();
		
		if( token.length == 2 )
			example = token[1];
		else if( token.length != 1)
			return null;
		
		token = example.toUpperCase().split("-");
		
		for( int i = 0; i < token.length; i++)
			result.add(token[i].trim());
		
		return result; 
	}
	
//	public static void main(String[] args) throws Exception {
//		new ProblemParser().parse();
//	}
}
