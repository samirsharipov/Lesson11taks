package uz.pdp.lesson11taks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson11taks.entity.Currency;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.CurrencyRepo;

import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepo currencyRepo;
    public Result addCurrency(Currency currency) {
        boolean byName = currencyRepo.existsByName(currency.getName());

        if (byName) {
            return new Result("bunday Currency mavjud!", false);
        }

        currencyRepo.save(currency);
        return new Result("Saqlandi!",true);
    }

    public Result edit(Integer id, Currency currency) {
        Optional<Currency> byId = currencyRepo.findById(id);
        if (byId.isPresent()) {
            Currency currency1 = byId.get();
            boolean existsByName = currencyRepo.existsByName(currency.getName());
            if (existsByName) {
                return new Result("bunday currency mavjud!", false);
            }
            currency1.setName(currency.getName());
            currencyRepo.save(currency1);
            return new Result("saqlandi!", true);
        }
        return new Result("bunday idlik currency topilmadi!",false);
    }
}
