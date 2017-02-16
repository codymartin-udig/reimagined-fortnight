package com.udig.whiteboard;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import com.udig.whiteboard.models.Whiteboard;
import com.udig.whiteboard.repositories.WhiteboardRepository;
import jdk.nashorn.internal.runtime.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    private void populateSampleData() {
    /*	repository.save(new Whiteboard("Whiteboard Inc", "10' x 4'", 60, 8999));
      repository.save(new Whiteboard("Whiteboard Inc", "5' x 4'", 60, 5999));
      repository.save(new Whiteboard("Whiteboard Inc", "4' x 2'", 60, 1999));
      repository.save(new Whiteboard("Whiteboard Inc", "3' x 7'", 60, 2499));
      repository.save(new Whiteboard("Whiteboard Inc", "1' x 1'", 60, 899));
      repository.save(new Whiteboard("Whiteboard Inc", "2' x 2'", 60, 1299));
      repository.save(new Whiteboard("Whiteboard Inc", "3' x 3'", 60, 2999));
      repository.save(new Whiteboard("Whiteboard Inc", "6' x 6'", 60, 10999));*/
    }

    private void writeOutData () {
      /*System.out.println("Whiteboards in database:");
      repository
          .findAll()
          .forEach(System.out::println);*/
    }
}
