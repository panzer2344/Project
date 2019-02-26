package com.azino.project.service;

import javax.servlet.http.HttpSession;

public interface SignOutService {

    void signOut(String userName, HttpSession session);

}
