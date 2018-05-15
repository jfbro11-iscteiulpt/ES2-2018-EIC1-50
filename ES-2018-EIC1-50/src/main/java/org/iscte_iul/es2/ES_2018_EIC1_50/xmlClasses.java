package org.iscte_iul.es2.ES_2018_EIC1_50;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
	         Element description = xmlDoc.createElement("Description");
	         Element titulo = xmlDoc.createElement("Title");
	         Element timeWillingToWait = xmlDoc.createElement("TimeWillingToWait");
	         Element variables = xmlDoc.createElement("DecisionVariables");
	        
	         xmlDoc.appendChild(rootElement);
	         rootElement.appendChild(mainElement);
	         mainElement.appendChild(titulo);
	         mainElement.appendChild(description);
	         mainElement.appendChild(timeWillingToWait);
	         mainElement.appendChild(variables);
	         
	         titulo.setTextContent(gui.nomeproblema.getText());
	         description.setTextContent(gui.descricao.getText());
	         timeWillingToWait.setTextContent(String.valueOf(gui.comboBox.getSelectedItem()));
	         
	         Element groupName = xmlDoc.createElement("GroupName");
	         Element variableType = xmlDoc.createElement("VariableType");
	         
	         variables.appendChild(groupName);
	         variables.appendChild(variableType);
	         
	         groupName.setTextContent(gui.txtGroupName.getText());
	         variableType.setTextContent(String.valueOf(gui.typebox.getSelectedItem()));
	         
	         int ammount = Integer.valueOf(gui.txtTypeTheAmmount.getText());
	         Element variable[] = new Element[ammount]; 
	         for(int i = 0; i<ammount; i++){
	        	 variable[i] = xmlDoc.createElement("Variable" + i);
	        	 variables.appendChild(variable[i]);
	        	 Element nome = xmlDoc.createElement("Name");
	        	 variable[i].appendChild(nome);
	        	 nome.setTextContent(gui.txtVariableName[i].getText());
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
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); 
				DocumentBuilder docBuilder;
				try {
					docBuilder = docBuilderFactory.newDocumentBuilder();
					Document document = docBuilder.parse(file);
					NodeList problemList = document.getElementsByTagName("Problem");
					for(int i=0,size= problemList.getLength(); i<size; i++) {
						Node problem = problemList.item(i);
						NodeList attributesList = problem.getChildNodes();
						for(int j = 0; j < attributesList.getLength(); j++){
							Node attribute = attributesList.item(j);
							if(attribute.getNodeType() == Node.ELEMENT_NODE){
								Element attribute2 = (Element) attribute;
								if(attribute2.getTagName() == "Title"){
									gui.nomeproblema.setText(attribute2.getTextContent());	
								}
								if(attribute2.getTagName() == "Description"){
									gui.descricao.setText(attribute2.getTextContent());
								}
								if(attribute2.getTagName() == "TimeWillingToWait"){
									gui.comboBox.setSelectedItem(attribute2.getTextContent());
								}
								if(attribute2.getTagName() == "DecisionVariables"){
									NodeList variablesList = attribute.getChildNodes();
									int aux = 0;
									for(int p = 0; p < variablesList.getLength(); p++){
										Node variable = variablesList.item(p);
										if(variable.getNodeType() == Node.ELEMENT_NODE){
											Element variable2 = (Element) variable;
											if(variable2.getTagName().equals("Variable" + aux)){
												aux++;
											}
										}
									}
									gui.txtTypeTheAmmount.setText(String.valueOf(aux));
									gui.btnNewButton.doClick();
									int aux2 = 0;
									for(int x = 0; x < variablesList.getLength(); x++){
										Node variable = variablesList.item(x);
										if(variable.getNodeType() == Node.ELEMENT_NODE){
											Element variable2 = (Element) variable;
											if(variable2.getTagName() == "GroupName"){
												gui.txtGroupName.setText(variable2.getTextContent());
											}
											if(variable2.getTagName() == "VariableType"){
												gui.typebox.setSelectedItem(variable2.getTextContent());
											}
											if(variable2.getTagName().equals("Variable" + aux2)){
												NodeList nameList = variable.getChildNodes();
												for(int n = 0; n < nameList.getLength(); n++){
													Node name = nameList.item(n);
													if(name.getNodeType() == Node.ELEMENT_NODE){
														Element name2 = (Element) name;
														if(name2.getTagName() == "Name"){
															gui.txtVariableName[aux2].setText(name2.getTextContent());
															aux2++;
														}
													}
												}
											}
										}
									}
								}
							}
						}
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
				
//			        	gui.nomeproblema.setText(nodeList.item(x).getAttributes().getNamedItem("Title").getNodeValue());
//			            gui.descricao.setText(nodeList.item(x).getAttributes().getNamedItem("Description").getNodeValue());
//			            gui.comboBox.setSelectedItem(nodeList.item(x).getAttributes().getNamedItem("TimeWillingToWait").getNodeValue());
			 
			        
//			      NodeList nodeList2 = document.getElementsByTagName("DecisionVariables");
//			      NodeList nodeListvariable = document.getElementsByTagName("Variable");
//			      int aux = 0;
//			      try{
//			      while(nodeList2.item(0).getAttributes().getNamedItem("VariableName" + aux).getNodeValue() != null){
//			    	  aux++;
//			      }
//			      } catch (NullPointerException e) {}
//			      gui.txtTypeTheAmmount.setText(String.valueOf(aux));
//			      gui.btnNewButton.doClick();
//			      for(int x=0,size= nodeList2.getLength(); x<size; x++) {
//			    	 gui.txtGroupName.setText(nodeList2.item(x).getAttributes().getNamedItem("GroupName").getNodeValue());
//			         gui.typebox.setSelectedItem(nodeList2.item(x).getAttributes().getNamedItem("VariableType").getNodeValue());
//			      }
//			      
//			      for(int i = 0; i<aux; i++){
//			    	  gui.txtVariableName[i].setText(nodeList2.item(0).getAttributes().getNamedItem("VariableName" + i).getNodeValue());
//			    	 // gui.jmetaltype[i].setSelectedItem(nodeList2.item(0).getAttributes().getNamedItem("VariableType" + i).getNodeValue());
//			      }
//			      
//			      
//			} catch (ParserConfigurationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SAXException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		

}
