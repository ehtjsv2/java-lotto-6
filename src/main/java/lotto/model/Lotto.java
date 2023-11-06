package lotto.model;

import static lotto.ErrorMessage.DUPLICATE_LOTTO_NUMBER;
import static lotto.ErrorMessage.ERROR_MESSAGE;
import static lotto.ErrorMessage.OVER_LOTTO_BOUNDARY;
import static lotto.ErrorMessage.OVER_LOTTO_SIZE;
import static lotto.configuration.LottoConfiguration.LOTTO_BOUNDARY_END_NUMBER;
import static lotto.configuration.LottoConfiguration.LOTTO_BOUNDARY_START_NUMBER;
import static lotto.configuration.LottoConfiguration.LOTTO_SIZE;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateOverSize(numbers);
        validateDuplicateNumber(numbers);
        validateInRange(numbers);
    }

    private void validateOverSize(List<Integer> numbers) {

        if (numbers.size() != LOTTO_SIZE.get()) {
            throw new IllegalArgumentException(ERROR_MESSAGE.getMessage() + OVER_LOTTO_SIZE.getMessage());
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        long distinctListSize = numbers.stream()
                .distinct()
                .count();
        if (distinctListSize != numbers.size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE.getMessage() + DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    private void validateInRange(List<Integer> numbers) {
        numbers.forEach(number -> {
            if (number < LOTTO_BOUNDARY_START_NUMBER.get() || number > LOTTO_BOUNDARY_END_NUMBER.get()) {
                    throw new IllegalArgumentException(ERROR_MESSAGE.getMessage() + OVER_LOTTO_BOUNDARY.getMessage());
                }
            });
        }
    }
