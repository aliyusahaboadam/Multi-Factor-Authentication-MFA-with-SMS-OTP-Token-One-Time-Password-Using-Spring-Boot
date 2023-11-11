package codingtechniques.util;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.AmazonSNSException;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class MessageUtil {
	
	public static void sendMessage (String phoneNumber, String message) {
		
		// credential
		// sns client
		// publish request
		
		try {
			
			BasicAWSCredentials credentials = 
					
	    new BasicAWSCredentials(System.getenv("AWS_ACCESS_KEY_ID"), System.getenv("AWS_SECRET_ACCESS_KEY"));
			
			
			AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
					           .withCredentials(new AWSStaticCredentialsProvider(credentials))
					           .withRegion(Regions.EU_NORTH_1)
					           .build();
			
			PublishResult result = snsClient.publish(
					new PublishRequest()
					.withMessage(message)
					.withPhoneNumber(phoneNumber)
					);
					
			
		} catch (AmazonSNSException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
		
	}

}
