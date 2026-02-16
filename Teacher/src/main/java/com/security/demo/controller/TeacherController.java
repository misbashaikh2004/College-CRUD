package com.security.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.demo.dto.TeacherRequest;
import com.security.demo.dto.TeacherResponse;
import com.security.demo.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherservice;

	@PostMapping("add")
	public void add(@RequestBody TeacherRequest teacherrequest) {
		teacherservice.add(teacherrequest);
	}

	@DeleteMapping("delete/{id}")
	public TeacherResponse delete(@PathVariable Integer id) {
		return teacherservice.delete(id);
	}

	@GetMapping("display")
	public List<TeacherResponse> display() {
		return teacherservice.display();
	}

	@PostMapping("add-all")
	public void addAll(@RequestBody List<TeacherRequest> list) {
		teacherservice.addAll(list);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody TeacherRequest request) {
		teacherservice.update(request, id);
		return ResponseEntity.ok("Updated Succesfully");
	}

}
