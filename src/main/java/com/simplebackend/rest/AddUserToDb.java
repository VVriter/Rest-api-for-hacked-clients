package com.simplebackend.rest;

import com.simplebackend.Globals;
import com.simplebackend.ents.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class AddUserToDb implements Globals {
    @GetMapping("db/add/{user}/{id}")
    public @ResponseBody ResponseEntity<String> getUsrHwid(@PathVariable String user, @PathVariable String id) {
        User usr = new User(user, id);
        String query = "INSERT INTO `users` (`username`, `hwid`) VALUES ('"+usr.getAccountName()+"', '"+id+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.toString(),HttpStatus.FORBIDDEN);
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);

            return new ResponseEntity<>("All is ok", HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.FORBIDDEN);
        }
    }
}
