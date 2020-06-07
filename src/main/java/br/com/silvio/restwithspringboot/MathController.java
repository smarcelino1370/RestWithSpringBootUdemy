package br.com.silvio.restwithspringboot;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/sum/{number1}/{number2}", method = RequestMethod.GET)
    public BigDecimal sum(@PathVariable(value="number1") String number1, @PathVariable(value="number2") String number2) {
        if(!isNumeric(number1) || !isNumeric(number2)){
            throw new UnsupportedOperationException("Por favor, defina um valor numérico");
        }
        BigDecimal sum = convertToBigDecimal(number1).add(convertToBigDecimal(number2));
        return sum;
    }

    private boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;

        String number = strNumber.replaceAll(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private BigDecimal convertToBigDecimal(String strNumber) {
        if(strNumber == null) return BigDecimal.ZERO;

        String number = strNumber.replaceAll(",",".");

        return isNumeric(number) ? new BigDecimal(number) : BigDecimal.ZERO;
    }
}
