package com.nist.studentmanagementsystem.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.nist.studentmanagementsystem.model.Student;
import com.nist.studentmanagementsystem.service.StudentService;
import com.nist.studentmanagementsystem.service.StudentServiceImpl;

public class StudentForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField nameTextField;
	public JTextField addressTextField;
	public JTextField ageTextField;
	public JTextField phoneTextField;
	public JTextField emailTextField;
	public JComboBox facultyComboBox,genderComboBox;
	public JLabel idTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentForm frame = new StudentForm();
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
	public StudentForm() {
		setTitle("Student Form");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 151, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddress.setBounds(10, 55, 151, 35);
		contentPane.add(lblAddress);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAge.setBounds(10, 105, 151, 35);
		contentPane.add(lblAge);
		
		JLabel lblPhoneno = new JLabel("Phone_No");
		lblPhoneno.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhoneno.setBounds(10, 150, 151, 35);
		contentPane.add(lblPhoneno);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGender.setBounds(10, 195, 151, 35);
		contentPane.add(lblGender);
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFaculty.setBounds(10, 240, 151, 35);
		contentPane.add(lblFaculty);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(10, 285, 151, 35);
		contentPane.add(lblEmail);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameTextField.setBounds(158, 13, 301, 35);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addressTextField.setColumns(10);
		addressTextField.setBounds(158, 55, 301, 35);
		contentPane.add(addressTextField);
		
		ageTextField = new JTextField();
		ageTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ageTextField.setColumns(10);
		ageTextField.setBounds(158, 100, 301, 35);
		contentPane.add(ageTextField);
		
		phoneTextField = new JTextField();
		phoneTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(158, 145, 301, 35);
		contentPane.add(phoneTextField);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailTextField.setColumns(10);
		emailTextField.setBounds(158, 285, 301, 35);
		contentPane.add(emailTextField);
		
		genderComboBox = new JComboBox();
		genderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Others"}));
		genderComboBox.setBounds(158, 198, 301, 35);
		contentPane.add(genderComboBox);
		
		facultyComboBox = new JComboBox();
		facultyComboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		facultyComboBox.setModel(new DefaultComboBoxModel(new String[] {"Bsc.CSIT", "BIM", "BCA", "BIT"}));
		facultyComboBox.setBounds(158, 240, 301, 35);
		contentPane.add(facultyComboBox);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=idTextField.getText();
				String name=nameTextField.getText();
				String address=addressTextField.getText();
				int age=Integer.parseInt(ageTextField.getText());
				long phone=Long.parseLong(  phoneTextField.getText());
				String email=emailTextField.getText();
				String gender=  (String) genderComboBox.getSelectedItem();
				String faculty= (String) facultyComboBox.getSelectedItem();  
				
				Student student=new Student();
				student.setName(name);
				student.setAddress(address);
				student.setAge(age);
				student.setEmail(email);
				student.setFaculty(faculty);
				student.setGender(gender);
				student.setPhone(phone);
				
				StudentService studentService=new StudentServiceImpl();
				if(id.isEmpty()|| id==null) {
					studentService.saveStudent(student);
				}
				else {
					student.setId(Integer.parseInt(id));
					studentService.updateStudent(student);
				}
				
				
				dispose();
				StudentDetail studentDetail=new StudentDetail();
				studentDetail.setVisible(true);
			}
		});
		submitBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		submitBtn.setBounds(330, 366, 137, 35);
		contentPane.add(submitBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StudentDetail studentDetail=new StudentDetail();
				studentDetail.setVisible(true);
			}
		});
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		cancelBtn.setBounds(158, 366, 137, 35);
		contentPane.add(cancelBtn);
		
		idTextField = new JLabel("");
		idTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idTextField.setBounds(469, 13, 45, 32);
		contentPane.add(idTextField);
	}
}
