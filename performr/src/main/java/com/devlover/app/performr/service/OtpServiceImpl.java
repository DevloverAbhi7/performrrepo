package com.devlover.app.performr.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devlover.app.performr.dao.OtpDao;
import com.devlover.app.performr.dao.UserDao;
import com.devlover.app.performr.model.OtpDetails;
import com.devlover.app.performr.model.Users;
import com.devlover.app.performr.util.CustomException;
import com.devlover.app.performr.util.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class OtpServiceImpl implements OtpService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	OtpDao otpDao;
	
	OtpDetails otpdtls;
	@Override
	public OtpDetails generateOtp(Users user) {

		Optional <Users> usr =userDao.findByUsername(user.getUsername());
		
		if(!usr.isPresent())
		{
			throw new CustomException("Invalid Request");
		}
		else
		{
			
			Optional <OtpDetails> otpdetails=	otpDao.findByUserid(usr.get().getId());
			String otp = Utils.random(6);
			otpdtls = new OtpDetails();
			if(otpdetails.isPresent())
			{
				if(otpdetails.get().getAttempts()>=3)
				{
					throw new CustomException("Otp Attempts Exceeded, contact Customer care");
				}
				else {
					otpdtls.setId(otpdetails.get().getId());
					otpdtls.setUserid(usr.get().getId());
					otpdtls.setOtp(otp);
					otpdtls.setCreateddate(otpdetails.get().getCreateddate());
					otpdtls.setAttempts(otpdetails.get().getAttempts()+1);
					otpdtls.setUpdateddate(new Date());
					return otpDao.save(otpdtls);
				}
			}
			else {
			otpdtls.setUserid(usr.get().getId());
			otpdtls.setOtp(otp);
			otpdtls.setCreateddate(new Date());
			otpdtls.setUpdateddate(new Date());
			return otpDao.save(otpdtls);
		}
		}
		
	}

}
