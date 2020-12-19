package ducchau.example.springboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Girl {

    @Autowired
    Outfit outfit;

    public Girl(@Qualifier("naked") Outfit outfit){
        this.outfit = outfit;
    }

}
