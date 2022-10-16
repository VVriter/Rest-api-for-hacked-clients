package com.simplebackend.rest;

import com.simplebackend.Globals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

@RestController
public class News implements Globals {

    @GetMapping("/news")
    public @ResponseBody ResponseEntity<String> getNews() {
        String response = getResponse(getNewsLink);
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public String getResponse(String link) {
        try {
            return new Scanner(new URL(link).openStream(), "UTF-8").useDelimiter("\\A").next().toString();
        } catch (IOException e) {
            return "null";
        }
    }

}
