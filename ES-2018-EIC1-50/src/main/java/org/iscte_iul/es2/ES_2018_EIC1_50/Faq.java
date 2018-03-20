package org.iscte_iul.es2.ES_2018_EIC1_50;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


//CRIADO APENAS PARA CRIAR A FRAME CORRESPONDENTE AO POPUP DA FAQ
public class Faq extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void Faq() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Faq frame = new Faq();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Faq() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 745, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Faq.class.getResource("/images/fa2.png")));
		lblNewLabel.setBounds(0, -22, 758, 505);
		contentPane.add(lblNewLabel);
	}

}
