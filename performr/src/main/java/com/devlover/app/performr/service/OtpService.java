package com.devlover.app.performr.service;

import com.devlover.app.performr.model.OtpDetails;
import com.devlover.app.performr.model.Users;


public interface OtpService 
{
public OtpDetails generateOtp(Users user);
}
