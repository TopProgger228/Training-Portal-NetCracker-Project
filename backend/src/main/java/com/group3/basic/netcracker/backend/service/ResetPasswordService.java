package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.User;

public interface ResetPasswordService {

    void sendMail(String email);

}
