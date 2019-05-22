package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import com.group3.basic.netcracker.backend.util.file.ImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserAPIs {
    private UserService userService;

    @Autowired
    public UserAPIs(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public UserForDisplay getUser(@RequestParam("username") String username) {
        return userService.getUserByUsername(username);
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

        String filepath = "backend//src//main//resources//photos//" + filename;

        if (ImageConverter.convertToImage(file, filepath, fileExtension) &&
                userService.updatePhoto(username, filepath) > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("Photo uploaded");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Photo not uploaded");
        }
    }

    @GetMapping(
            value = "/getPhoto"
    )
    public ResponseEntity<?> getImage(@RequestParam("username") String username) {
        try {
            String filepath = userService.getPhotoByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(ImageConverter.convertToString(filepath));
        } catch (Exception e) {
            return null;
        }
    }
}
