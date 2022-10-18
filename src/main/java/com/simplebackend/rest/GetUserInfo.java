package com.simplebackend.rest;

import com.simplebackend.ents.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserInfo {

    @GetMapping("/user/{user}/{id}")
    public @ResponseBody ResponseEntity<String> getUsrHwid(@PathVariable String user, @PathVariable String id) throws JSONException {
        User userInfo = new User(user, id);

        if (userInfo.isUserPresent()) {
            JSONObject object = new JSONObject();
            object.put("username", userInfo.getAccountName());
            object.put("hwid", userInfo.getHwidId());
        /*  object.put("pass", userInfo.getPass());
            object.put("crdate", userInfo.getCreationDate().toString()); */
            return new ResponseEntity<>(object.toString(), HttpStatus.OK);
        } else
            return new ResponseEntity<>("null", HttpStatus.NOT_FOUND);
    }

}
