package vn.techmaster.money.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.money.reponse.MoneyConvertResult;
import vn.techmaster.money.request.MoneyConvertRequest;
import vn.techmaster.money.service.MoneyConverter;

@RestController
@RequestMapping("api/convertMoney")
public class ConverterRest {

    @Autowired
    private MoneyConverter moneyConverter;

    @PostMapping()
    public MoneyConvertResult handleConverterPost(@RequestBody MoneyConvertRequest request){
        return moneyConverter.convertRate(request);
    }
}
