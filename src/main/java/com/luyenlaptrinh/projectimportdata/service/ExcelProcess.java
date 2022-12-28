package com.luyenlaptrinh.projectimportdata.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelProcess {
    void importData(MultipartFile file);
}
