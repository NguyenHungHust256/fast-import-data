package com.luyenlaptrinh.projectimportdata.service.imp;

import com.luyenlaptrinh.projectimportdata.entities.User;
import com.luyenlaptrinh.projectimportdata.repo.UserRepo;
import com.luyenlaptrinh.projectimportdata.service.ExcelProcess;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ExcelProcessImp implements ExcelProcess {
    @Autowired
    private UserRepo userRepo;
    @Override
    public void importData(MultipartFile file) {
        long startTime = System.currentTimeMillis();
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet worksheet = workbook.getSheetAt(0);
        List<User> dataList = new ArrayList<>();
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            XSSFRow row = worksheet.getRow(index);
            DataFormatter formatter = new DataFormatter();
            String phoneNumber = formatter.formatCellValue(row.getCell(0));

            log.info("Process for: {}", phoneNumber);

            String name= formatter.formatCellValue(row.getCell(1));

            User user = new User(UUID.randomUUID().toString(), phoneNumber, name);
            dataList.add(user);
            if(dataList.size()>4000){
                userRepo.saveAll(dataList);
                dataList.clear();
            }
        }
        if(dataList.size() >0){
            userRepo.saveAll(dataList);
            dataList.clear();
        }

        log.info("[{} ms] total process", System.currentTimeMillis() - startTime);
    }
}
