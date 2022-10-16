package com.simplebackend.ents;

import com.simplebackend.Globals;
import jdk.nashorn.internal.objects.annotations.Getter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class User implements Globals {

    private String accountName;
    private String hwidId;
    private String pass;
    private Date creationDate;

    public User(String accountName, String hwidId) {
        this.accountName = accountName;
        this.hwidId = hwidId;
        //TODO: sql request
    }

    @Getter
    public String getAccountName() {
        return accountName;
    }

    @Getter
    public String getHwidId() {
        return this.hwidId;
    }

    @Getter
    public String getPass() {
        return pass;
    }

    @Getter
    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isUserPrecent() {
        String resp = getResponce(getLinkJust+"hwids/"+getAccountName()+"/"+getHwidId());
        System.out.println(resp);
        return Objects.equals(resp, "true");
    }


    public String getResponce(String link) {
        try {
            return new Scanner(new URL(link).openStream(), "UTF-8").useDelimiter("\\A").next().toString();
        } catch (IOException e) {
            return "null";
        }
    }

}
