package example.day06.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//스프링 작동
@SpringBootApplication
@ServletComponentScan
public class Appstart {
    public static void main(String[] args) {
        SpringApplication.run(Appstart.class);
    }
}
