package com.dz.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dz.app.utility.AppUtility;

@RestController
public class FileUploadController {

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		/*
		 * System.out.println(file.getOriginalFilename());
		 * System.out.println(file.getSize());
		 * System.out.println(file.getContentType());
		 */

		try {

			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must containe a file");
			}

			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpeg content allowed");
			}
			
			boolean status = AppUtility.dynamicPathUpload(file);
			if(status) {
//				return ResponseEntity.ok("file upload successfully..");
//				ServletUriComponentsBuilder.fromCurrentContextPath().path ---> gives path to localhost
				System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString());
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something wend wrong...");
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something wend wrong...");
	}

}
