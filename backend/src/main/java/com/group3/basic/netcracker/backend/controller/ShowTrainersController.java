package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dto.TrainerInfo;
import com.group3.basic.netcracker.backend.dto.TrainersInfoDto;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class ShowTrainersController {
    private final UserService service;

    @Autowired
    public ShowTrainersController(UserService service) {
        this.service = service;
    }

    @GetMapping("trainerslist")
    public List<TrainerInfo> getTrainersInformation() {
        List<TrainerInfo> infoList = new ArrayList<>();
        TrainerInfo info;

        List trainersInfo = service.getTrainersInfo();

        for (int i = 0; i < trainersInfo.size(); i++) {
            info = new TrainerInfo();
            info.setId(((TrainersInfoDto) trainersInfo.get(i)).getId());
            info.setFname(((TrainersInfoDto) trainersInfo.get(i)).getFname());
            info.setLname(((TrainersInfoDto) trainersInfo.get(i)).getLname());
            info.setInfo(((TrainersInfoDto) trainersInfo.get(i)).getInfo());
            info.setCourses(service.getTrainerCourses(((TrainersInfoDto) trainersInfo.get(i)).getId()));
            info.setPhoto(((TrainersInfoDto) trainersInfo.get(i)).getPhoto());

            infoList.add(info);
        }

        log.debug("List of trainers is received!");

        return infoList;
    }

    @PostMapping("updateTrainer")
    public ResponseEntity<?> updateTrainerInfo(@RequestParam("id") String id, @RequestParam("fname") String fname,
                                               @RequestParam("lname") String lname, @RequestParam("info") String info) {

        service.updateName(Integer.parseInt(id), fname, lname);
        service.updateTrainerInfo(Integer.parseInt(id), info);

        log.info("Information about - {} is updated", fname, lname);

        return new ResponseEntity<>(new ResponseMessage("Trainer updated successfully!"), HttpStatus.OK);
    }

}
