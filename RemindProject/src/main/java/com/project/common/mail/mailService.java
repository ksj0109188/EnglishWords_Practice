package com.project.common.mail;

public interface mailService {
    String createKey() throws Exception;

    String getKey(boolean lowerCheck, int size) throws Exception;

    void setMail(String authKey, String userId, String userEmail, String context) throws Exception;

    void findPwdMail(String newPwd, String userId, String userName, String userEmail, String context);
}
