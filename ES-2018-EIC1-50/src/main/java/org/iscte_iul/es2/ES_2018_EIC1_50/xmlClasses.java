package org.iscte_iul.es2.ES_2018_EIC1_50;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class xmlClasses {
	
	public static Document createXML(GUI gui) throws ParserConfigurationException{
			 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	         Document xmlDoc = docBuilder.newDocument();
	         
	         Element rootElement = xmlDoc.createElement("Problems");
	         Element mainElement = xmlDoc.createElement("Problem");
	        
	         rootElement.appendChild(mainElement);
	         xmlDoc.appendChild(rootElement);
	         
	         mainElement.setAttribute("Title", gui.nomeproblema.getText());
	         mainElement.setAttribute("Description", gui.descricao.getText());
	         System.out.println(String.valueOf(gui.comboBox.getSelectedItem()));
	         mainElement.setAttribute("TimeWillingToWait", String.valueOf(gui.comboBox.getSelectedItem()));
	         
	         Element variables = xmlDoc.createElement("DecisionVariables");
	         variables.setAttribute("GroupName", gui.txtGroupName.getText());
	         mainElement.appendChild(variables);
	         int ammount = Integer.valueOf(gui.txtTypeTheAmmount.getText());
	         for(int i = 0; i<ammount; i++){
	        	 variables.setAttribute("VariableName" + i, gui.txtVariableName[i].getText());
	        	 variables.setAttribute("VariableType" + i, String.valueOf(gui.jmetaltype[i].getSelectedItem()));
	         }
	         
	         return xmlDoc;
	}
		
		public static void saveXML(Document document, File file){
			try {
			OutputFormat outFormat = new OutputFormat(document);
	         outFormat.setIndenting(true);
	         
	         File xmlFile = new File(file + "problems.xml");
	         FileOutputStream outStream = new FileOutputStream(xmlFile);
	         
	         XMLSerializer serializer = new XMLSerializer(outStream, outFormat);
				serializer.serialize(document);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void saveXMLhere(Document document){
			try {
			OutputFormat outFormat = new OutputFormat(document);
	         outFormat.setIndenting(true);
	         
	         File xmlFile = new File("problems.xml");
	         FileOutputStream outStream = new FileOutputStream(xmlFile);
	         
	         XMLSerializer serializer = new XMLSerializer(outStream, outFormat);
				serializer.serialize(document);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void openXML(File file, GUI gui){
			 try {
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); 
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				Document document = docBuilder.parse(file);
				
				  NodeList nodeList = document.getElementsByTagName("Problem");
			        for(int x=0,size= nodeList.getLength(); x<size; x++) {
			        	gui.nomeproblema.setText(nodeList.item(x).getAttributes().getNamedItem("Title").getNodeValue());
			            gui.descricao.setText(nodeList.item(x).getAttributes().getNamedItem("Description").getNodeValue());
			            gui.comboBox.setSelectedItem(nodeList.item(x).getAttributes().getNamedItem("TimeWillingToWait").getNodeValue());
			        }
			        
			      NodeList nodeList2 = document.getElementsByTagName("DecisionVariables");
			      int aux = 0;
			      try{
			      while(nodeList2.item(0).getAttributes().getNamedItem("VariableName" + aux).getNodeValue() != null){
			    	  aux++;
			      }
			      } catch (NullPointerException e) {}
			      gui.txtTypeTheAmmount.setText(String.valueOf(aux));
			      gui.btnNewButton.doClick();
			      for(int x=0,size= nodeList2.getLength(); x<size; x++) {
			    	 gui.txtGroupName.setText(nodeList2.item(x).getAttributes().getNamedItem("GroupName").getNodeValue());
			      }
			      for(int i = 0; i<aux; i++){
			    	  gui.txtVariableName[i].setText(nodeList2.item(0).getAttributes().getNamedItem("VariableName" + i).getNodeValue());
			    	  gui.jmetaltype[i].setSelectedItem(nodeList2.item(0).getAttributes().getNamedItem("VariableType" + i).getNodeValue());
			      }
			      
			      
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

}
