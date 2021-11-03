package org.apache.camel.example.console;

import org.apache.camel.spring.Main;

public final class CamelConsoleMain {

    private CamelConsoleMain() {
    }
    
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.setApplicationContextUri("META-INF/spring/camel-context.xml");
        main.run();
    }
}
