package com.jhonerodrigeus.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AmazonSnsConfiguration {

    @Value("aws.acessKey")
    private String acessKey;

    @Value("aws.secretKey")
    private String secretKey;
}
