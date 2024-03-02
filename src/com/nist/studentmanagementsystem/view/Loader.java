package com.nist.studentmanagementsystem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.Color;

public class Loader extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loader frame = new Loader();
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
	public Loader() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to NextStep");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(53, 47, 344, 34);
		contentPane.add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 255));
		progressBar.setBackground(new Color(128, 255, 0));
		progressBar.setStringPainted(true);
		progressBar.setBounds(83, 119, 297, 24);
		contentPane.add(progressBar);
		
		Thread thread=new Thread(
				()->{
					int i=0;
					while(i<=100) {
						progressBar.setValue(i);
						try {
							Thread.sleep(30);
						} catch (InterruptedException e) {
						System.out.println(e);
						}
						if(i==100) {
							dispose();
							LoginForm login =new LoginForm();
							login.setVisible(true);
						}	
						i++;
					}
				});
		thread.start();
		
		JLabel lblNewLabel_1 = new JLabel("Loading....");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(186, 153, 95, 24);
		contentPane.add(lblNewLabel_1);
	}
}
