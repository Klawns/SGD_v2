package com.klaus.backend.DTO.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.klaus.backend.Model.Enum.CategoryType;
import com.klaus.backend.Model.Enum.ExpenseType;
import com.klaus.backend.Model.Enum.PaymentMethod;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ExpensesRequestDTO(
        @NotBlank(message = "Descrição é obrigatória") String description,
        @NotNull(message = "Preço é obrigatório") @Positive(message = "O preço deve ser um valor positivo") BigDecimal amount,
        @NotNull(message = "Data é obrigatória") LocalDate date,
        @NotNull(message = "Categoria é obrigatória") CategoryType category,
        @NotNull(message = "Método de pagamento é obrigatório") PaymentMethod paymentMethod,
        @PositiveOrZero(message = "As parcelas devem ter um valor positivo") Integer installments,
        @NotNull(message = "O tipo da despesa é obrigatória") ExpenseType type)
{
        @AssertTrue(message = "A data não pode ser futura")
        public boolean isDateValid(){
                if(date == null) return true;
                return !date.isAfter(LocalDate.now());
        }

        @AssertTrue(message = "Pagamentos parcelados só podem ser feitos no cartão de crédito")
        public boolean isInstallmentsValid(){
                if(installments > 1){
                        return paymentMethod == PaymentMethod.CREDITO;
                }
                return true;
        }
}