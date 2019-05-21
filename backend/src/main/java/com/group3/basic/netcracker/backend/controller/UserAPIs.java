package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dao.impl.UserDaoImpl;
import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import com.group3.basic.netcracker.backend.util.file.ImageConverter;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserAPIs {
    UserService userService;

    @Autowired
    UserAPIs(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public UserForDisplay getUser(@RequestParam("username") String username) {
        UserForDisplay u = userService.getUserByUsername(username);
        return u;
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestParam("id") int id,
                                        @RequestParam("username") String username,
                                        @RequestParam("fname") String fname,
                                        @RequestParam("lname") String lname,
                                        @RequestParam("email") String email) {
        userService.updateUser(id, username, fname, lname, email);

        return new ResponseEntity<>(new ResponseMessage("User updated successfully!"), HttpStatus.CREATED);
    }

    @PostMapping("/postPhoto")
    public ResponseEntity<String> handleFileUpload(@RequestBody byte[] file,
                                                   @RequestParam("username") String username,
                                                   @RequestParam("filename") String filename,
                                                   @RequestParam("fileExtension") String fileExtension) {

        String filepath = "backend//src//main//resources//photos//"+filename;

        if (ImageConverter.convertToImage(file, filepath, fileExtension) &&
                userService.updatePhoto(username,filepath)>0){
            return ResponseEntity.status(HttpStatus.OK).body("Photo uploaded");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Photo not uploaded");
        }
    }

    @GetMapping(
            value = "/getPhoto"
            //produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<?> getImage(@RequestParam("username") String username) throws IOException {
        try {
            String filepath = userService.getPhotoByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(ImageConverter.convertToString(filepath));
        }catch (Exception e) {
            return null;
        }
    }
}
