package com.myorg.losservices.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.myorg.losmodel.model.Answer;
import com.myorg.losmodel.model.Question;

public class LoadQuestion {
	
	Document dom;
	private ArrayList<Question> questionList = new ArrayList<Question>();
	
	public LoadQuestion(final String category) 
		throws SAXException,ParserConfigurationException,IOException, URISyntaxException{
		dom = CreateQuestionDOM.getDOM(category);
	}
	
	public void populateQuestionList(final String category) { 
		String options[] = new String[4];
		String question = null;
	    String answerType = null;
	    String required = null;
	    //TO DO no of question loop
		for (int i=0; i<=3; i++) {
			NodeList qList = dom.getElementsByTagName("question");
			NodeList childList = qList.item(i).getChildNodes();
	    
			int counter=0;
	    
			for (int j = 0; j < childList.getLength(); j++) {
				Node childNode = childList.item(j);
				if ("answer".equals(childNode.getNodeName()))
				{
					options[counter] = childList.item(j).getTextContent();
					counter++;
				}
				else if("questionnaire".equals(childNode.getNodeName()))
				{
					question = childList.item(j).getTextContent();
				}
				else if("answerType".equals(childNode.getNodeName()))
				{
					answerType = childList.item(j).getTextContent();
				}
				else if("required".equals(childNode.getNodeName()))
				{
					required = childList.item(j).getTextContent();
				}
            
			}
			
			Question ques = new Question();
			ques.setQnsId(category+i); //category+questionNb will be questionID
			ques.setQnsDecs(question);
			
			List<Answer> answerList = new ArrayList<Answer>();
			for(String ans:options)
			{
				Answer ans = new Answer();
				ans.setAnsId(); //TODO set answer id and populate other fields answerType and required flag
				ans.setAnsDesc(ans);    
				ans.setAnsValue(ans);
				answerList.add(ans);
			}
			ques.setAnswerList(answerList);
			questionList.add(ques);
		}	
	}
	
	public ArrayList<Question> getQuestionList(){
		return this.questionList;
	}
}
