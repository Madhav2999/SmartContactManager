package com.icwd.serviceregistry.scm20;

import com.icwd.serviceregistry.scm20.Services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Scm20ApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private EmailService service;

    @Test
    void sendEmailTest() {
        service.sendEmail(
                "madhavsarangi@gmail.com",
                "Just managing the emails",
                "this is scm project working on email service");
    }

}
