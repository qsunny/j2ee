package com.aaron.springbootDemo.web;
import javafx.scene.Parent;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"com.aaron.springbootDemo.core","com.aaron.springbootDemo.web"})
public class SpringbootDemoApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(Parent.class)
                .child(SpringbootDemoApp.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
