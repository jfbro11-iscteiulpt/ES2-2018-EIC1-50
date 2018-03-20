package org.iscte_iul.es2.ES_2018_EIC1_50;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;

public class gui extends JFrame {

	private JPanel contentPane;
	private volatile JTextField nomeproblema;
	private volatile JTextField descricao;
	private volatile JTextField mail;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public gui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(gui.class.getResource("/images/background.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 505);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setForeground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Problem: ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(47, 116, 88, 23);
		contentPane.add(lblNewLabel);

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
		descricao.setBounds(137, 155, 294, 66);
		contentPane.add(descricao);
		descricao.setColumns(10);

		mail = new JTextField();
		mail.setText("  Type your e-mail");
		mail.setHorizontalAlignment(SwingConstants.LEFT);
		mail.setColumns(10);
		mail.setBackground(new Color(255, 255, 255));
		mail.setBounds(137, 221, 216, 26);
		contentPane.add(mail);

		JButton submit = new JButton("Submit");
		submit.setBounds(382, 282, 117, 29);
		contentPane.add(submit);

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
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					// codigo para carregar a info para os campos
				}
			}
		});

		file.add(open);

		// CODIGO REFERENTE AO SAVE AS
		final JMenuItem saveas = new JMenuItem("Save as...");
		saveas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");
				if (fileChooser.showSaveDialog(saveas) == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				}
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

		JMenuItem emailhelp = new JMenuItem("E-mail for help...");
		help.add(emailhelp);

		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon(gui.class.getResource("/images/background.png")));
		background.setBounds(0, -50, 990, 620);
		contentPane.add(background);

		// CODIGO REFERENTE A QUANDO O USER CLICA EM HELP --> SEND MAIL

		final JFrame parent = new JFrame();
		parent.pack();
		parent.setVisible(false);

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

		emailhelp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
				int OKclick = JOptionPane.showOptionDialog(parent, panel, "Authentication", JOptionPane.DEFAULT_OPTION,
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
				// AQUI
			}
		});
	}

	// PARTE DO CÓDIGO REFERENTE AO ENVIO DE MENSAGEM AO SUPORTE
	public void sendSupportMail(String userName, String passWord, String descriptiontext) {
		try {
			String host = "smtp.gmail.com";
			String user = userName;
			String pass = passWord;
			String to = "jfbro11@iscte-iul.pt";
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

	// PARTE DO CÓDIGO REFERENTE AO ENVIO DE MENSAGEM AO SUPORTE
	public void sendSubmitMail(String userName, String passWord, String descriptiontext) {
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
			transport.close();
			System.out.println("message send successfully");

		} catch (

		Exception ex) {
			System.out.println(ex);
		}
	}
}
