package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.FileService;

@RestController
public class fileController {

    @Autowired FileService fileService;

    //1. 다운로드 요청 처리
    @GetMapping("/file/download")
    public void fileDownload(String filename){
        System.out.println("fileController.fileDownload");
        System.out.println("fileName = " + filename);
        fileService.fileDownload(filename);
    }
}
