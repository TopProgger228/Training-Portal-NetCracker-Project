package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.GroupDao;
import com.group3.basic.netcracker.backend.entity.Group;
import com.group3.basic.netcracker.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupDao groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDao groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public void createGroup(String name, int course_id) {
        groupDAO.createGroup(name, course_id);
    }

    @Override
    public Group getGroupById(int id) {
        return groupDAO.getGroupById(id);
    }

    @Override
    public List listGroups() {
        return groupDAO.listGroups();
    }

    @Override
    public void removeGroup(int id) {
        groupDAO.removeGroup(id);
    }

    @Override
    public void updateGroup(int id, String name, int course_id) {
        groupDAO.updateGroup(id, name, course_id);

    }
}
