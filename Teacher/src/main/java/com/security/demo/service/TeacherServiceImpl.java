package com.security.demo.service;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.demo.dto.TeacherRequest;
import com.security.demo.dto.TeacherResponse;
import com.security.demo.exception.InvalidEmail;
import com.security.demo.exception.InvalidId;
import com.security.demo.exception.InvalidMobileNumber;
import com.security.demo.model.Student;
import com.security.demo.model.Teacher;
import com.security.demo.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherrepository;

	@Override
	public void add(TeacherRequest teacher) {
		// TODO Auto-generated method stub
		List<Teacher> list = teacherrepository.findAll();
		String mob = teacher.getMob();

		if (mob != null) {
			mob = mob.trim();
		}

		if (mob.length() == 10) {
			if (mob.charAt(0) == '0' || mob.charAt(0) == '1' || mob.charAt(0) == '2' || mob.charAt(0) == '3'
					|| mob.charAt(0) == '4' || mob.charAt(0) == '5') {
				throw new InvalidMobileNumber(" Mobile Number Not Start With This Number");
			}
		}

		for (int i = 0; i < mob.length(); i++) {
			if (!Character.isDigit(mob.charAt(i))) {
				throw new InvalidMobileNumber(mob);
			}
		}

		Teacher existing = teacherrepository.findByMob(mob);
		if (existing != null) {
			throw new InvalidMobileNumber("Mobile Is Already Exist");
		}
//---------------------------------------------------------------------------------------------------------
		Integer id = teacher.getId();
		if (id == null || id <= 0) {
			throw new InvalidId("Id Must Be Positive");
		}

		for (Teacher teach : list) {
			if (teach.getId().equals(id)) {
				throw new InvalidId("Id Already Exist");
			}
		}
		String email = teacher.getEmail();
		if (email == null) {
			throw new InvalidEmail("Enter Email");
		}

		Teacher exist = teacherrepository.findByEmail(email);
		if (exist != null) {
			throw new InvalidEmail("Email Is Already Exist");
		}

		Teacher teacher1 = TeacherRequest.toEntity(teacher);
		teacherrepository.save(teacher1);
	}
//--------------------------------------------------------------------------------------------------------

	@Override
	public List<TeacherResponse> display() {
		// TODO Auto-generated method stub
		List<Teacher> teacher = teacherrepository.findAll();
		List<TeacherResponse> teacherresponse = new ArrayList<>();

		for (Teacher teach : teacher) {
			TeacherResponse response = new TeacherResponse();

			response.setId(teach.getId());
			response.setEmail(teach.getEmail());
			response.setMob(teach.getMob());
			response.setName(teach.getName());
			teacherresponse.add(response);
		}

		return teacherresponse;
	}

	@Override
	public TeacherResponse delete(Integer id) {
		// TODO Auto-generated method stub
		if (teacherrepository.findById(id).isPresent()) {
			Teacher temp = teacherrepository.findById(id).get();
			teacherrepository.delete(temp);
			return TeacherResponse.toDTO(temp);
		}
		return null;
	}

	@Override
	public void update(Teacher teacher, Integer id) {
		// TODO Auto-generated method stub
		teacher.setId(id);
		teacherrepository.save(teacher);
	}

	@Override
	public Teacher findByMob(String mob) {
		// TODO Auto-generated method stub
		return teacherrepository.findByMob(mob);
	}

	@Override
	public Teacher findByEmail(String email) {
		// TODO Auto-generated method stub
		return teacherrepository.findByEmail(email);
	}

	@Override
	public void addAll(List<TeacherRequest> list) {
		// TODO Auto-generated method stub
		List<Teacher> teacher = list.stream().map(TeacherRequest::toEntity).toList();
		teacherrepository.saveAll(teacher);
	}

	@Override
	public TeacherResponse update(TeacherRequest request, Integer id) {
		// TODO Auto-generated method stub
		Teacher teacher = teacherrepository.findById(id).get();
		if (teacher == null) {
			throw new InvalidId("Invalid Id");
		}

		teacher.setName(request.getName());
		teacher.setEmail(request.getEmail());
		teacher.setMob(request.getMob());
		Teacher updatedTeacher = teacherrepository.save(teacher);
		return TeacherResponse.toDTO(updatedTeacher);

	}

}
