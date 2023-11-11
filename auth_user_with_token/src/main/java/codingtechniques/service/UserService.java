package codingtechniques.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import codingtechniques.model.Otp;
import codingtechniques.model.User;
import codingtechniques.repository.OtpRepository;
import codingtechniques.repository.UserRepository;
import codingtechniques.util.MessageUtil;
import codingtechniques.util.OtpUtil;

@Service
public class UserService implements UserServiceContract {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private OtpRepository otpRepository;

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public String authenticateUser(User user, Model model) {
		Optional<User> findUser = userRepository.findUserByEmail(user.getEmail());
		if (findUser.isPresent()) {
			User checkedUser = findUser.get();
			if (passwordEncoder.matches(user.getPassword(), checkedUser.getPassword())) {
				renewOtp(findUser.get());
				return "redirect:/otp-validation";
			} else {
				
				model.addAttribute("message", "Bad Credential");
				return "auth";
			}
			
		} else {
			model.addAttribute("message", "Bad Credential");
			return "auth";
		}
	
	}

	@Override
	public boolean checkOtpValidity(Otp otp) {
		Optional<Otp> findUser = otpRepository.findOtpByEmail(otp.getEmail());
		if (findUser.isPresent()) {
			Otp otpChecked = findUser.get();
			if (otpChecked.getCode().equals(otp.getCode())) {
				return true;
			}
			
		}
		return false;
	}
	
	public void renewOtp (User user) {
		
		Optional<Otp> findUser = otpRepository.findOtpByEmail(user.getEmail());
		String code = OtpUtil.generateOtp();
		MessageUtil.sendMessage(user.getPhoneNumber(), "Here is your OTP Code: " + code);
		if (findUser.isPresent()) {
			Otp otp = findUser.get();
			otp.setCode(code);
			otpRepository.save(otp);
		} else {
			Otp otp = new Otp();
			otp.setCode(code);
			otp.setEmail(user.getEmail());
			otpRepository.save(otp);
		}
		
	}
	
	
	
		
		
	

}
