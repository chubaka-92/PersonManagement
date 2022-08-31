package com.example.personmenegement;

import ch.qos.logback.classic.PatternLayout;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class MaskTest {

    public static void main(String[] args) {

        BigDecimal salary = new BigDecimal("9000000");
        String email = "4nax@stark.com";
        String password = "paradais";


        log.info("1 Маскируй это salary={} и в общем то все", salary);
        log.info("2.1  Маскируй это salary:{}", salary);
        log.info("2  Маскируй это password: там дам ");
        log.info("2  Маскируй это password:{} там дам ",password);
        log.info("3 Маскируй это \"salary\":{}", salary);
        log.info("4  Маскируй это email:{} мема нет",email);
    }
}
