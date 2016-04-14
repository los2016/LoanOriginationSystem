package com.myorg.losservices.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CreateQuestionDOM {

	final static String questionnairePath = "";
	
	public static Document getQuestionsDOM(final String category) throws SAXException,
			ParserConfigurationException, IOException, URISyntaxException {
		Document dom = null;
		File questionFile = null;

		questionFile = new File(questionnairePath + category + "-questionnaire.xml");
		System.out.println("Question File Absolute Path " + questionFile.getAbsolutePath());

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		try {
			dom = db.parse(questionFile);
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("Error : Question File Not Found " + fileNotFound);
		}
		dom.getDocumentElement().normalize();
		return dom;
	}

}
