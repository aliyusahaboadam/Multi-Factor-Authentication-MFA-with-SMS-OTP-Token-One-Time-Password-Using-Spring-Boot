package codingtechniques.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import codingtechniques.model.Otp;

public interface OtpRepository  extends JpaRepository<Otp, Long>{
   
	Optional<Otp> findOtpByEmail (String email);
}
