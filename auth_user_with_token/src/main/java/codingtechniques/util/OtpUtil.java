package codingtechniques.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class OtpUtil {
	
	 private static String code;
	
	public static String generateOtp() {
		
		SecureRandom random;
		try {
			random = SecureRandom.getInstanceStrong();
			int intRandom = random.nextInt(8999) + 1000;
			code = String.valueOf(intRandom);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

}
