package ducchau.example.springboot2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bikini")
@Scope("prototype")
public class Bikini implements Outfit{
    @Override
    public void wear() {
        System.out.println("Da mac bikini");
    }
}
