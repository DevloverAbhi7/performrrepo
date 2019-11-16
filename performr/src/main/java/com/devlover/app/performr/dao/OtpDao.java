package com.devlover.app.performr.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.devlover.app.performr.model.OtpDetails;

public interface OtpDao extends CrudRepository<OtpDetails, Integer>{

	Optional <OtpDetails> findByUserid(long id);
}
