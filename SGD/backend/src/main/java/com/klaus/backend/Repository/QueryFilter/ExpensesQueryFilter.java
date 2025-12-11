package com.klaus.backend.Repository.QueryFilter;

import static com.klaus.backend.Repository.Specifications.ExpensesSpec.*;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.klaus.backend.Model.Expenses;
import com.klaus.backend.Model.Enum.CategoryType;
import com.klaus.backend.Model.Enum.PaymentMethod;
import com.klaus.backend.Repository.Specifications.ExpensesSpec;

import lombok.Data;

@Data
public class ExpensesQueryFilter {
    private String desc, username;
    private CategoryType category;
    private PaymentMethod paymentMethod;
    private Double amount;
    private LocalDate startDate, endDate;

    public Specification<Expenses> toSpecification() {
        return belongsToUser(username)
                .and(descriptionContains(desc))
                .and(ExpensesSpec.categoryEquals(category))
                .and(ExpensesSpec.paymentMethodEquals(paymentMethod))
                .and(ExpensesSpec.hasAmount(amount))
                .and(ExpensesSpec.dateBetween(startDate, endDate));
    }
}
