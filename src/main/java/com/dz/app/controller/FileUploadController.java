package com.dz.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
			
			boolean status = AppUtility.fileuploadHelper(file);
			if(status) {
				return ResponseEntity.ok("file upload successfully..");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("spmething wend wrong...");
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("spmething wend wrong...");
	}

}
