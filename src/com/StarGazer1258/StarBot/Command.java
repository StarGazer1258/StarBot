package com.StarGazer1258.StarBot;

import org.jibble.pircbot.User;

public interface Command {
    public void handle(User user, String param);
    
    public String getName();
}
