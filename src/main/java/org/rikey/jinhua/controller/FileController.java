package org.rikey.jinhua.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

@Controller
public class FileController {

    @ResponseBody
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file")MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            writeStream(filename, inputStream);

            return "{\"status\":\"OK\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\":\"failure\"}";
        }
    }

    @GetMapping("/shorturl/{shortpath}")
    public String redr(@PathVariable("shortpath") String shortpath) {
        System.out.println(shortpath);
        return "{\"status\":\"OK\"}";
    }


    @PostMapping(value = "/uploadRaw")
    @ResponseBody
    public String uploadRaw(HttpServletRequest request) {
        try {
            StandardMultipartHttpServletRequest multipartRequest = (StandardMultipartHttpServletRequest)request;
            Map mappedFiles = multipartRequest.getFileMap();
            Iterator<Map.Entry> filesEntryIterator = mappedFiles.entrySet().iterator();
            while (filesEntryIterator.hasNext()) {
                Map.Entry<String, MultipartFile> entry = filesEntryIterator.next();
                String filename = entry.getValue().getOriginalFilename();
                InputStream inputStream = entry.getValue().getInputStream();
                writeStream(filename, inputStream);

            }

            return "{\"status\":\"OK\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\":\"failure\"}";
        }
    }

    private void writeStream(String filename, InputStream inputStream) throws IOException{
        FileOutputStream outputStream = new FileOutputStream(new File(filename));
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.close();
        inputStream.close();
    }
}
