package HomeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

interface Outfit {
    public void wear();
}

@Component()
@Primary
class Bikini implements Outfit {
    @Override
    public void wear() {
        System.out.println("Toi mac bikini");
    }
}

@Component("girl")
class Girl {

    private Outfit outfit;

    public Girl(Outfit outfit) {
        this.outfit = outfit;
    }

    public Outfit getOutfit() {
        return this.outfit;
    }
}
@Component
class quanDai implements Outfit{
    @Override
    public void wear() {
        System.out.println("Mac quan dai");
    }
}


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Outfit outfit = context.getBean(Outfit.class);
        System.out.println(outfit);
        Girl girl = context.getBean(Girl.class);
        System.out.println(girl.getOutfit());
        outfit.wear();
    }
}
