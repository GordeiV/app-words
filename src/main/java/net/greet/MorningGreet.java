package net.greet;

import net.Greetable;

public class MorningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good Morning, " + userName;
    }
}
