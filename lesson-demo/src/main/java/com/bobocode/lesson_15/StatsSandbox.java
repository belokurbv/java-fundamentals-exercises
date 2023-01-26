package com.bobocode.lesson_15;

import org.springframework.web.client.RestTemplate;

public class StatsSandbox {
    public static void main(String[] args) {
        var client = new RestTemplate();
        client.postForLocation("http://93.175.204.87:8080/training/stats", new Info("Bohdan",
                "Belokur", "Petros", 3
                , 60));
    }

    record Info(String firstName, String lastName, String team, int trainingDaysPerWeek,
                int minutesPerTrainingDay) {
    }

}
