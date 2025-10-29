package com.klaus.backend.Repository.Specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.klaus.backend.Model.Expenses;

public class ExpensesSpec {

    public static Specification<Expenses> belongsToUser(String username) {
        return (root, query, builder) -> builder.equal(root.get("user").get("username"), username);
    }

    public static Specification<Expenses> descriptionContains(String desc) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(desc))
                return null;
            return builder.like(
                    builder.lower(root.get("description")),
                    "%" + desc.toLowerCase() + "%");
        };
    }

    public static Specification<Expenses> categoryContains(String category) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(category))
                return null;
            return builder.like(
                    builder.lower(root.get("category")),
                    "%" + category.toLowerCase() + "%");
        };
    }

    public static Specification<Expenses> paymentMethodContains(String paymentMethod) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(paymentMethod))
                return null;
            return builder.like(
                    builder.lower(root.get("paymentMethod")),
                    "%" + paymentMethod.toLowerCase() + "%");
        };
    }

    public static Specification<Expenses> hasAmount(Double amount) {
        return (root, query, builder) -> amount == null ? null : builder.equal(root.get("amount"), amount);
    }

    public static Specification<Expenses> dateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, builder) -> {
            if (startDate == null && endDate == null)
                return null;
            if (startDate != null && endDate != null)
                return builder.between(root.get("date"), startDate, endDate);
            if (startDate != null)
                return builder.greaterThanOrEqualTo(root.get("date"), startDate);
            return builder.lessThanOrEqualTo(root.get("date"), endDate);
        };
    }
}
