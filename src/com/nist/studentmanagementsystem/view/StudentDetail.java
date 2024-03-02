package com.nist.studentmanagementsystem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.nist.studentmanagementsystem.model.Student;
import com.nist.studentmanagementsystem.service.StudentService;
import com.nist.studentmanagementsystem.service.StudentServiceImpl;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class StudentDetail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	
	StudentService studentService=new StudentServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDetail frame = new StudentDetail();
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
	public StudentDetail() {
		setTitle("Student Details");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1069, 506);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Logout");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginForm login=new LoginForm();
				login.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_1);
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.setBounds(54, 402, 178, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StudentForm student=new StudentForm();
				student.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
				int row=table.getSelectedRow();
				Object id=tableModel.getValueAt(row, 0);
				Object name=tableModel.getValueAt(row, 1);
				Object address= tableModel.getValueAt(row, 2);
				Object age=tableModel.getValueAt(row, 3);
				Object gender=tableModel.getValueAt(row, 4);
				Object faculty=tableModel.getValueAt(row, 5);
				Object contact=tableModel.getValueAt(row, 6);
				Object email=tableModel.getValueAt(row, 7);
				
				StudentForm studentForm=new StudentForm();
				studentForm.idTextField.setText(id.toString());
				studentForm.nameTextField.setText(name.toString());
				studentForm.addressTextField.setText(address.toString());
				studentForm.ageTextField.setText(age.toString());
				studentForm.genderComboBox.setSelectedItem(gender);
				studentForm.facultyComboBox.setSelectedItem(faculty);
				studentForm.phoneTextField.setText(contact.toString());
				studentForm.emailTextField.setText(email.toString());
				dispose();
				studentForm.setVisible(true);
				
				
			}
		});
		btnEdit.setBounds(420, 402, 178, 30);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
				int row=table.getSelectedRow();
				Object id=tableModel.getValueAt(row, 0);
				int status=JOptionPane.showConfirmDialog(StudentDetail.this, "Delete","Do you really want to delete?",JOptionPane.YES_NO_OPTION);
				if(status==0) {
					studentService.deleteStudentById(Integer.parseInt(id.toString()));
					loadDataInTable();
				}
			}
		});
		btnDelete.setBounds(793, 402, 178, 30);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setBounds(670, 37, 102, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		searchTextField = new JTextField();
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String name=searchTextField.getText();
				if(name==null || name.isEmpty()) {
					loadDataInTable();
				}
				else {
					searchDataByName(name);
				}
			}
		});
		searchTextField.setBounds(772, 37, 200, 20);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		table = new JTable();
		table.setBounds(54, 79, 919, 292);
		contentPane.add(table);
		String columnName []= {"Id","Name","Address","Age","Gender","Faculty","Contact","Email"};
		DefaultTableModel tableModel=new DefaultTableModel(columnName,0);
		table.setModel(tableModel);
		
		loadDataInTable();
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(54, 79, 919, 292);
		contentPane.add(scrollPane);
			
	}
	
	public void loadDataInTable() {
		List<Student> studentDetail= studentService.getStudentList();
		DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for(Student std:studentDetail) {
			tableModel.addRow(new Object [] {
					std.getId(), std.getName(),std.getAddress(),std.getAge(),std.getGender(),std.getFaculty(),std.getPhone(),std.getEmail()
			});
		}
		
	}
	public void searchDataByName(String name) {
		List<Student> studentDetail= studentService.getStudentsByName(name);
		DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for(Student std:studentDetail) {
			tableModel.addRow(new Object [] {
					std.getId(), std.getName(),std.getAddress(),std.getAge(),std.getGender(),std.getFaculty(),std.getPhone(),std.getEmail()
			});
		}
	}	
}
