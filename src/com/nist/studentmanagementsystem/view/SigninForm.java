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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class SigninForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JPasswordField cofirmTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SigninForm frame = new SigninForm();
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
	public SigninForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SignUp Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(141, 29, 133, 29);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(49, 98, 99, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(49, 137, 99, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(49, 176, 99, 29);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Confirm Password");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(49, 215, 150, 29);
		contentPane.add(lblNewLabel_1_3);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(196, 101, 243, 29);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(196, 140, 243, 29);
		contentPane.add(emailTextField);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=nameTextField.getText();
				String email=emailTextField.getText();
				String password=passwordTextField.getText();
				String confirm=cofirmTextField.getText();
				if((name.isEmpty()||name==null )||( email.isEmpty()||email==null)||( password.isEmpty()||password==null)||( confirm.isEmpty()||confirm==null)) {
					JOptionPane.showConfirmDialog(SigninForm.this,"Please fill all the data","",JOptionPane.ERROR_MESSAGE);
					
				}
				else {
				CredentialDto credential=new CredentialDto();
				credential.setUserName(name);
				credential.setEmail(email);
				credential.setPassword(password);
				credential.setConfirmPass(confirm);
				
				if(password.equalsIgnoreCase(confirm)) {
				CredentialService credentialSer=new CredentialServiceImpl();
				credentialSer.signup(credential);
				
				dispose();
				LoginForm loginForm=new LoginForm();
				loginForm.setVisible(true);
			}
				else {
					JOptionPane.showMessageDialog(SigninForm.this,"Password Doesnot matcch","",JOptionPane.ERROR_MESSAGE);

				}
			
			}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(196, 268, 243, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Already have an account?");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(196, 301, 192, 37);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginForm login=new LoginForm();
				login.setVisible(true);
				
			}
		});
		lblLogin.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblLogin.setBounds(381, 300, 65, 37);
		contentPane.add(lblLogin);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(196, 176, 243, 29);
		contentPane.add(passwordTextField);
		
		cofirmTextField = new JPasswordField();
		cofirmTextField.setBounds(196, 218, 243, 29);
		contentPane.add(cofirmTextField);
	}
}
