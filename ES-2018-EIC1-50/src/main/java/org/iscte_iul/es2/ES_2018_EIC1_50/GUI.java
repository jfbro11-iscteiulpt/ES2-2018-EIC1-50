package org.iscte_iul.es2.ES_2018_EIC1_50;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import java.awt.Color;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.util.Date;
import java.util.Properties;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.io.FileOutputStream;

public class GUI extends JFrame {

	private JLayeredPane contentPane;
	protected volatile JTextField nomeproblema;
	protected volatile JTextField descricao;
	private volatile JTextField mail;
	protected JTextField txtTypeTheAmmount;
	protected JTextField txtGroupName;
	protected JComboBox comboBox = new JComboBox();
	protected JTextField txtVariableName[];
	protected JComboBox jmetaltype[];
	protected JButton btnNewButton;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public GUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/images/background.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 505);
		contentPane = new JLayeredPane();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setForeground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTypeTheAmmount = new JTextField();
		txtTypeTheAmmount.setBounds(318, 311, 73, 26);
		contentPane.add(txtTypeTheAmmount);
		txtTypeTheAmmount.setColumns(10);
		
		final JScrollPane scrollpanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpanel.setBounds(448, 92, 291, 336);
		scrollpanel.setOpaque(false);
		scrollpanel.setBorder(null);
		contentPane.add(scrollpanel);
		
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		scrollpanel.setViewportView(panel_1);
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		
		
		txtGroupName = new JTextField();
		txtGroupName.setText("Group name");
		txtGroupName.setBounds(70, 6, 130, 23);
		panel_1.add(txtGroupName);
		txtGroupName.setColumns(10);	
		
		scrollpanel.setVisible(false);
		scrollpanel.getViewport().setOpaque(false);
		
		btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTypeTheAmmount.setEditable(false);
				int ammount = Integer.valueOf(txtTypeTheAmmount.getText());
				txtVariableName = new JTextField[ammount];
				jmetaltype = new JComboBox[ammount];
				int aux = 0;
				for(int i = 0; i<ammount; i++){
					txtVariableName[i] = new JTextField();
					txtVariableName[i].setText("Variable name");
					txtVariableName[i].setBounds(6, 41 + aux, 106, 26);
					
					jmetaltype[i] = new JComboBox();
					jmetaltype[i].setModel(new DefaultComboBoxModel(new String[] {"inteiro", "binário", "decimal"}));
					jmetaltype[i].setToolTipText("");
					jmetaltype[i].setBounds(115, 41 + aux, 80, 27);
					
					aux = aux +30;
					panel_1.add(txtVariableName[i]);
					panel_1.add(jmetaltype[i]);
					txtVariableName[i].setColumns(10);
				}
				panel_1.setPreferredSize(new Dimension(290, 40 + aux));
				scrollpanel.setVisible(true);
			}
		});
		btnNewButton.setBounds(385, 314, 46, 23);
		contentPane.add(btnNewButton);
		
		
		JLabel lblAmmountOfProblem = new JLabel("Ammount of problem decision variables:");
		lblAmmountOfProblem.setForeground(Color.WHITE);
		lblAmmountOfProblem.setBounds(56, 316, 260, 16);
		contentPane.add(lblAmmountOfProblem);

		JLabel lblNewLabel = new JLabel("Problem: ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(47, 116, 88, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Time willing to wait:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(56, 270, 135, 16);
		contentPane.add(lblNewLabel_1);

		nomeproblema = new JTextField();
		nomeproblema.setHorizontalAlignment(SwingConstants.CENTER);
		nomeproblema.setBounds(137, 114, 216, 29);
		nomeproblema.setBackground(new Color(255, 255, 255));
		nomeproblema.setText("Type the title of your problem");
		contentPane.add(nomeproblema);
		nomeproblema.setColumns(10);

		descricao = new JTextField();
		descricao.setHorizontalAlignment(SwingConstants.LEFT);
		descricao.setBackground(new Color(255, 255, 255));
		descricao.setText("  Write a small description");
		descricao.setBounds(137, 143, 294, 66);
		contentPane.add(descricao);
		descricao.setColumns(10);

		mail = new JTextField();
		mail.setText("  Type your e-mail");
		mail.setHorizontalAlignment(SwingConstants.LEFT);
		mail.setColumns(10);
		mail.setBackground(new Color(255, 255, 255));
		mail.setBounds(137, 210, 216, 26);
		contentPane.add(mail);

		JButton submit = new JButton("Submit");
		submit.setBounds(591, 440, 117, 29);
		contentPane.add(submit);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "15", "20", "30", "45", "60"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(203, 266, 63, 27);
		contentPane.add(comboBox);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBorderPainted(false);
		menuBar_1.setBackground(Color.WHITE);
		menuBar_1.setBounds(0, 0, 745, 22);
		contentPane.add(menuBar_1);

		JMenu file = new JMenu("File");
		menuBar_1.add(file);

		// CODIGO REFERENTE AO OPEN
		final JMenuItem open = new JMenuItem("Open...");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
				}
			}
		);
		file.add(open);

		final JMenuItem saveas = new JMenuItem("Save as...");
		saveas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		file.add(saveas);

		JMenu help = new JMenu("Help");
		menuBar_1.add(help);

		JMenuItem faq = new JMenuItem("FAQ");
		faq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Faq s = new Faq();
				s.Faq();
			}
		});
		help.add(faq);

		final JMenuItem emailhelp = new JMenuItem("E-mail for help...");
		help.add(emailhelp);

		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon(GUI.class.getResource("/images/background.png")));
		background.setBounds(0, -50, 990, 620);
		contentPane.add(background);

		final JPanel panel = new JPanel(new BorderLayout(5, 5));
		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("E-Mail", SwingConstants.RIGHT));
		label.add(new JLabel("Password", SwingConstants.RIGHT));
		label.add(new JLabel("Write your problem", SwingConstants.RIGHT));

		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		final JTextField username = new JTextField();
		controls.add(username);
		final JPasswordField password = new JPasswordField();
		controls.add(password);
		final JTextField description = new JTextField();
		controls.add(description);
		panel.add(controls, BorderLayout.CENTER);
		
		
		//CODIGO REFERENTE AO ENVIO DO MAIL DE HELP
		emailhelp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
				int OKclick = JOptionPane.showOptionDialog(emailhelp, panel, "Authentication", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
				while (true) {
					if (OKclick == 0) {
						String userName = username.getText();
						String passWord = password.getText();
						String descriptiontext = description.getText();
						sendSupportMail(userName, passWord, descriptiontext);
						break;
					}
				}
			}
		});

		// CÓDIGO REFERENTE AO ENVIO DE MAIL QUANDO SUBMIT
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMailAfterOptimizing();
			}
		});
	}

		// CODIGO REFERENTE AO SAVE AS
	public void save() {
		String sb = "TEST CONTENT"; // meter aqui a info dos campos
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("/home/me/Documents"));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			CreateXML(chooser.getSelectedFile());
		}
	}
	public void open(){
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			System.out.println(file.getName());
			xmlClasses xmlclasses = new xmlClasses();
			xmlclasses.openXML(file, this);
	}
	}

		//CÓDIGO REFERENTE AO ENVIO DE MENSAGEM AO SUPORTE
	public void sendSupportMail(String userName, String passWord, String descriptiontext) {
		try {
			String host = "smtp.gmail.com";
			String user = userName;
			String pass = passWord;
			String to = "jfbromao97@gmail.com";
			String from = userName;
			String subject = "Help with software.";
			String messageText = descriptiontext;
			boolean sessionDebug = false;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("sent!");
			transport.close();
			JOptionPane.showMessageDialog(null, "Message sent successfuly!", "Success", JOptionPane.NO_OPTION);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "User or password is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

		//CÓDIGO REFERENTE AO SUMBIT
	public void sendSubmitMail(String userName, String passWord, String descriptiontext) {
		try {
			String host = "smtp.gmail.com";
			String user = userName;
			String pass = passWord;
			String to = "jpmrd1@iscte-iul.com";
			String from = userName;
			String subject = "TITULO"; //METER AQUI O TITULO COMO ESTA PEDIDO NO PROJETO
			String messageText = descriptiontext; //SAME OF ABOVE
			boolean sessionDebug = false;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("message send successfully");
			

		} catch (

		Exception ex) {
			System.out.println(ex);
		}
	}
	
	//codigo referente ao save de um xml
	
	public void CreateXML(File file){
		try {
			xmlClasses xmlclasses = new xmlClasses();
			Document document= xmlClasses.createXML(this);
			xmlClasses.saveXML(document, file);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void CreateXMLhere(){
		try {
			xmlClasses xmlclasses = new xmlClasses();
			Document document= xmlClasses.createXML(this);
			xmlClasses.saveXMLhere(document);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getTitle(){
		return nomeproblema.getText();
	}
	


	public void sendMailAfterOptimizing() {
		try {
			String host = "smtp.gmail.com";
			String user = mail.getText();
			String pass = "8errofatal";
			String to = "nuclearrrrr@gmail.com";
			String from = mail.getText();
			String time = LocalDateTime.now().toString();
			String subject = "Otimização em curso: " + nomeproblema.getText() + " " + time; // METER AQUI O TITULO COMO
																							// ESTA PEDIDO NO PROJETO
			String messageText = "Muito obrigado por usar esta plataforma de otimização. Será informado por email sobre o progresso do processo de otimização, quando o processo de otimização tiver atingido 25%, 50%,75% do total do (número de avaliações ou) tempo estimado, e também quando o processo estiver terminado, com sucesso ou devido à ocorrência de erros.";
			boolean sessionDebug = false;

			Properties props = System.getProperties();
			
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			Session mailSession = Session.getDefaultInstance(props, null);

			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentBodyPart = new MimeBodyPart();

			
			
			//ENVIAR XML POR MAIL
			CreateXMLhere();
			File file = new File("problems.xml"); 
			DataSource source = new FileDataSource(file.getAbsolutePath());
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName("problems.xml"); // ex : "test.pdf"

			//multipart.addBodyPart(textBodyPart); // add the text part
			multipart.addBodyPart(attachmentBodyPart); // add the attachement part

			msg.setContent(multipart);
			

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("message send successfully");

		} catch (

		Exception ex) {
			System.out.println(ex);
		}
	}
}
