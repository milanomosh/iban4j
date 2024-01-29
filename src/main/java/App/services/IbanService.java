package App.services;

import org.iban4j.*;

public class IbanService {

    public String ibanService(String iban) {
        String valid = "not valid";
        try {
            IbanUtil.validate(iban);
            valid = "valid";
            // valid
        } catch (IbanFormatException |
                 InvalidCheckDigitException |
                 UnsupportedCountryException e) {
            // invalid
        }
        return valid;
    }
}
