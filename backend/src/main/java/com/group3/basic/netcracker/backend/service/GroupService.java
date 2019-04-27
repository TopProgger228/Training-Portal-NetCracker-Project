package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.Group;

import java.util.List;

public interface GroupService {
    void createGroup(String name, int course_id);

    Group getGroupById(int id);

    List listGroups();

    void removeGroup(int id);

    void updateGroup(int id, String name, int course_id);
}
