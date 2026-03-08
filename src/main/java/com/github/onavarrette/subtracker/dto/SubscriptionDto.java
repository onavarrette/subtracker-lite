package com.github.onavarrette.subtracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public record SubscriptionDto(
                @NotBlank(message = "Name is mandatory") String name,

                @NotNull(message = "Price is mandatory") @Positive(message = "Price must be strictly positive") BigDecimal price,

                @NotNull(message = "Renewal date is mandatory") LocalDate renewalDate) {
}
