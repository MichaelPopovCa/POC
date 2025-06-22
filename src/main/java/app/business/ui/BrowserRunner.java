package app.business.ui;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
//@Component
//@Order(2)
public class BrowserRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(5);
        String url = "http://localhost:8080/index.html";
        ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "start", url);
        builder.inheritIO();
        builder.start();
    }
}
