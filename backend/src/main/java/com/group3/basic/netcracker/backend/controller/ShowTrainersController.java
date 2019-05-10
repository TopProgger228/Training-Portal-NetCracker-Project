package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dto.TrainerInfo;
import com.group3.basic.netcracker.backend.dto.TrainersInfoDto;
import com.group3.basic.netcracker.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ShowTrainersController {
    private final UserService service;

    @Autowired
    public ShowTrainersController(UserService service){
        this.service = service;
    }

    @GetMapping("trainerslist")
    public List<TrainerInfo> getTrainersInformation(){
        List<TrainerInfo> infoList = new ArrayList<>();
        TrainerInfo info;

        List trainersInfo = service.getTrainersInfo();

        for (int i = 0; i < trainersInfo.size(); i++){
            info = new TrainerInfo();

            info.setFname(((TrainersInfoDto) trainersInfo.get(i)).getFname());
            info.setLname(((TrainersInfoDto) trainersInfo.get(i)).getLname());
            info.setInfo(((TrainersInfoDto) trainersInfo.get(i)).getInfo());
            info.setCourses(service.getTrainerCourses(((TrainersInfoDto) trainersInfo.get(i)).getId()));

            infoList.add(info);
        }

        return infoList;
    }
}
