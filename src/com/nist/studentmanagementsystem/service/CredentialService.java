package com.nist.studentmanagementsystem.service;

import com.nist.studentmanagementsystem.model.CredentialDto;

public interface CredentialService {
	public void signup(CredentialDto credentialDto); 
	public boolean isUserExist(CredentialDto credentialDto);
}
