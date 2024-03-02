package com.nist.studentmanagementsystem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nist.studentmanagementsystem.model.CredentialDto;
import com.nist.studentmanagementsystem.service.CredentialService;
import com.nist.studentmanagementsystem.service.CredentialServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class LoginForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel.setBounds(87, 88, 100, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password ");
		lblPassword.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblPassword.setBounds(87, 158, 100, 30);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(171, 41, 205, 37);
		contentPane.add(lblNewLabel_1);
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userNameTextField.setBounds(86, 116, 346, 37);
		contentPane.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email=userNameTextField.getText();
				String password=passwordTextField.getText();
				
				CredentialDto credential=new CredentialDto();
				credential.setUserName(email);
				credential.setPassword(password);
				
				CredentialService credentialService=new CredentialServiceImpl();
				boolean userExist=credentialService.isUserExist(credential);
				
				if(userExist) {
				dispose();
				StudentDetail std=new StudentDetail();
				std.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(LoginForm.this,"Please Enter Valid Email or Password","Invalid Credential",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		loginBtn.setBounds(87, 250, 345, 37);
		contentPane.add(loginBtn);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(87, 246, 345, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Dont have an account?");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(97, 286, 187, 37);
		contentPane.add(lblNewLabel_3);
		
		JLabel signup = new JLabel("SignUp");
		signup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				SigninForm signinForm =new SigninForm();
				signinForm.setVisible(true);
			}
		});
		signup.setFont(new Font("Tahoma", Font.ITALIC, 18));
		signup.setBounds(288, 286, 65, 37);
		contentPane.add(signup);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(87, 185, 345, 37);
		contentPane.add(passwordTextField);
	}
}
