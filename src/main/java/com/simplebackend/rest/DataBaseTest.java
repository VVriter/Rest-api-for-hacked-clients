package com.simplebackend.rest;

import com.simplebackend.Globals;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class DataBaseTest implements Globals {
    String query = "SELECT * FROM users";

    @GetMapping("/db/")
    public @ResponseBody ResponseEntity<String> getUsrHwid() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.toString(),HttpStatus.FORBIDDEN);
        }


        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            JSONObject jsonObject = new JSONObject();
            while (result.next()) {
                jsonObject.put(result.getString(1),result.getString(2));
            }

            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.FORBIDDEN);
        }
    }
}
