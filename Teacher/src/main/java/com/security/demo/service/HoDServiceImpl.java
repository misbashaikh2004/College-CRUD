package com.security.demo.service;

import java.util.ArrayList;
import java.util.List;
import com.security.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.demo.dto.HoDRequest;
import com.security.demo.dto.HoDResponse;
import com.security.demo.dto.StudentRequest;
import com.security.demo.exception.InvalidDepartment;
import com.security.demo.exception.InvalidEmail;
import com.security.demo.exception.InvalidId;
import com.security.demo.exception.InvalidMobileNumber;
import com.security.demo.model.HoD;
import com.security.demo.repository.HoDRepository;

@Service
public class HoDServiceImpl implements HoDService {

	private final StudentRepository studentRepository;

	@Autowired
	private HoDRepository hodrepository;

	HoDServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public void add(HoDRequest hod) {
		// TODO Auto-generated method stub

		List<HoD> list = hodrepository.findAll();

		String mob = hod.getMob();
		if (mob != null) {
			mob = mob.trim();
		}

		if (mob.length() == 10) {
			if (mob.charAt(0) == '0' || mob.charAt(0) == '1' || mob.charAt(0) == '2' || mob.charAt(0) == '3'
					|| mob.charAt(0) == '4' || mob.charAt(0) == '5') {
				throw new InvalidMobileNumber("Invalid Mobile Number");
			}
		}

		for (int i = 0; i < mob.length(); i++) {
			if (!Character.isDigit(mob.charAt(i)))
				throw new InvalidMobileNumber("Invalid");
		}

		HoD existing = hodrepository.findByMob(mob);
		if (existing != null) {
			throw new InvalidMobileNumber("Mobile Already Exist");
		}

		Integer id = hod.getId();
		if (id == null || id <= 0) {
			throw new InvalidId("ID Must Be Positive");
		}

		for (HoD hod1 : list) {
			if (hod1.getId().equals(id)) {
				throw new InvalidId("Id Is Already Exist");
			}
		}

		String email = hod.getEmail();

		if (email == null) {
			throw new InvalidEmail("Invalid Email");
		}

		if (!hodrepository.findByEmail(email).isEmpty()) {
			throw new InvalidEmail("Email Is already Exist");
		}

		List<String> validDepartment = List.of("BCS", "BCA", "IT", "CS", "ENTC");
		if (!validDepartment.contains(hod.getDept().toUpperCase())) {
			throw new InvalidDepartment("Invalid Department");
		}

		HoD hod1 = HoDRequest.toEntity(hod);
		hodrepository.save(hod1);
	}
// ----------------------------------------------------------------------------------------------------------

	@Override
	public List<HoDResponse> display() {
		// TODO Auto-generated method stub
		List<HoD> hod = hodrepository.findAll();
		List<HoDResponse> hodresponse = new ArrayList<>();

		for (HoD hod1 : hod) {
			HoDResponse response = new HoDResponse();

			response.setId(hod1.getId());
			response.setDept(hod1.getDept());
			response.setEmail(hod1.getEmail());
			response.setExp(hod1.getExp());
			response.setMob(hod1.getMob());

			hodresponse.add(response);
		}
		return hodresponse;

	}

	@Override
	public HoDResponse update(HoDRequest request, Integer id) {
		// TODO Auto-generated method stub
		HoD hod = hodrepository.findById(id).get();
		if (hod == null) {
			throw new InvalidId("Id Is Invalid");
		}
		hod.setName(request.getName());
		hod.setDept(request.getDept());
		hod.setEmail(request.getEmail());
		hod.setExp(request.getExp());
		hod.setMob(request.getMob());
		HoD updatehod = hodrepository.save(hod);
		return HoDResponse.toDTO(updatehod);
	}

	@Override
	public HoDResponse delete(Integer id) {
		// TODO Auto-generated method stub
		if (hodrepository.findById(id).isPresent()) {
			HoD temp = hodrepository.findById(id).get();
			hodrepository.delete(temp);
			return HoDResponse.toDTO(temp);
		}
		return null;
	}

	@Override
	public List<HoD> findByEmail(String email) {
		return hodrepository.findByEmail(email);
	}

	@Override
	public boolean existsByMob(String mob) {
		// TODO Auto-generated method stub
		return hodrepository.existsByMob(mob);
	}

	@Override
	public HoD findByMob(String mob) {
		// TODO Auto-generated method stub
		return hodrepository.findByMob(mob);
	}

	@Override
	public HoD search(Integer id) {
		// TODO Auto-generated method stub
		if (hodrepository.findById(id).isPresent()) {
			HoD temp = hodrepository.findById(id).get();
			return temp;
		}
		return null;
	}

	@Override
	public void addAll(List<HoDRequest> list) {
		// TODO Auto-generated method stub
		List<HoD> hods = list.stream().map(HoDRequest::toEntity).toList();
		hodrepository.saveAll(hods);
	}

}
