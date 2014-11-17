package com.gor.sellphotos.dao;

import java.sql.SQLException;

import org.h2.tools.Console;


public class H2Client {

    public static void main(String[] args) {
        try {
            new Console().runTool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
