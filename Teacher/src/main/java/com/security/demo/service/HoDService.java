package com.security.demo.service;

import java.util.List;

import com.security.demo.dto.HoDRequest;
import com.security.demo.dto.HoDResponse;
import com.security.demo.dto.StudentRequest;
import com.security.demo.model.HoD;

public interface HoDService {

	void add(HoDRequest hodreRequest);

	List<HoDResponse> display();

	HoDResponse delete(Integer id);

	HoDResponse update(HoDRequest hod, Integer id);

	List<HoD> findByEmail(String email);

	boolean existsByMob(String mob);

	HoD findByMob(String mob);

	HoD search(Integer id);

	void addAll(List<HoDRequest> list);

}
