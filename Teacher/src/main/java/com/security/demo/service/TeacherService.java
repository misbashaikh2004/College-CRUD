package com.security.demo.service;

import java.util.List;

import com.security.demo.dto.TeacherRequest;
import com.security.demo.dto.TeacherResponse;
import com.security.demo.model.Teacher;

public interface TeacherService {

	void add(TeacherRequest teacherrequest);

	List<TeacherResponse> display();

	TeacherResponse delete(Integer id);

	void update(Teacher teacher, Integer id);

	Teacher findByMob(String mob);

	Teacher findByEmail(String email);

	void addAll(List<TeacherRequest> list);

	TeacherResponse update(TeacherRequest request, Integer id);
}
