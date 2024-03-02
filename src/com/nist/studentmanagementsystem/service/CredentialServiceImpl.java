package com.nist.studentmanagementsystem.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nist.studentmanagementsystem.databaseconnection.DatabaseConnection;
import com.nist.studentmanagementsystem.model.CredentialDto;

public class CredentialServiceImpl implements CredentialService {
PreparedStatement ps=null;

	@Override
	public void signup(CredentialDto credentialDto) {
		String sql="Insert into admin_data(username,email,password,confirm_pass)values(?,?,?,?)";
		
		try {
			ps=DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setString(1, credentialDto.getUserName());
			ps.setString(2, credentialDto.getEmail());
			ps.setString(3, credentialDto.getPassword());
			ps.setString(4, credentialDto.getConfirmPass());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public boolean isUserExist(CredentialDto credentialDto) {
		String sql="select * from admin_data where username=? and password=?";
		try {
			ps=DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setString(1, credentialDto.getUserName());
			ps.setString(2, credentialDto.getPassword());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}

}
