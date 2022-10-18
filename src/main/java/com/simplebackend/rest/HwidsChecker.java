package com.simplebackend.rest;

import com.simplebackend.Globals;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

@RestController
public class HwidsChecker implements Globals {

    @GetMapping("/hwids/{user}/{id}")
    public @ResponseBody ResponseEntity<String> getUsrHwid(@PathVariable String user, @PathVariable String id) throws JSONException {
        JSONObject getHwids;
        try {
            getHwids = new JSONObject(getHwidsString());
        } catch (JSONException e) {
            return new ResponseEntity<>("JsonException", HttpStatus.FORBIDDEN);
        }

        if (getHwids.has(user) && Objects.equals(getHwids.get(user), id))
            return new ResponseEntity<>("true", HttpStatus.OK);
        else
            return new ResponseEntity<>("false", HttpStatus.NOT_FOUND);
    }

    String getHwidsString() {
        try {
            return new Scanner(new URL(getLinkJust+"db/").openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
        } catch (IOException e) {
            return "null";
        }
    }

}
