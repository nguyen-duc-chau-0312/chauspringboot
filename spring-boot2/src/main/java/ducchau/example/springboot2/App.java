package ducchau.example.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        //ApplicationContext chinh la container, quan ly toan bo cac Bean
        ApplicationContext context =  SpringApplication.run(App.class, args);

        //Lay Bean ra bang cach
        Outfit outfit = context.getBean(Outfit.class);

        //in ra xem thu no la gi
        System.out.println("Instance: " + outfit);

        //Su dung ham wear
        outfit.wear();

        Girl girl = context.getBean(Girl.class);

        System.out.println("Instance1: " + girl);

        System.out.println("Girl outfit: " +girl.outfit);

        girl.outfit.wear();
    }
}
