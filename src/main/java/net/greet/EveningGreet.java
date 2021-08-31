package net.greet;

import net.Greetable;

public class EveningGreet extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good Evening, " + userName;
    }
}
