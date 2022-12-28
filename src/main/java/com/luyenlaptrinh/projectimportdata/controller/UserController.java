package com.luyenlaptrinh.projectimportdata.controller;

import com.luyenlaptrinh.projectimportdata.service.ExcelProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ExcelProcess excelProcess;

    @PostMapping("/import-data")
    public ResponseEntity<?> importData(@RequestParam MultipartFile file) {
        try {
            excelProcess.importData(file);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
