package codingtechniques.service;

import org.springframework.ui.Model;

import codingtechniques.model.Otp;
import codingtechniques.model.User;

public interface UserServiceContract {

	public void saveUser(User user);
	public String authenticateUser(User user, Model model);
	public boolean checkOtpValidity (Otp otp);
	
}
