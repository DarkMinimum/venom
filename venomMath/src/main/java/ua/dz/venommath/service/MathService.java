package ua.dz.venommath.service;

import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.ezylang.evalex.Expression;

@Service
public class MathService {

    public Optional<Double> calculateExpression(@NonNull String expressionRaw) {
        try {
            var result = new Expression(expressionRaw).evaluate().getNumberValue().doubleValue();
            return Optional.of(result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }
}
