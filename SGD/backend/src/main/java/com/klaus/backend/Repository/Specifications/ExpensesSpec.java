package com.klaus.backend.Repository.Specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.klaus.backend.Model.Expenses;
import com.klaus.backend.Model.Enum.CategoryType;
import com.klaus.backend.Model.Enum.PaymentMethod;

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

    public static Specification<Expenses> categoryEquals(CategoryType category) {
        return (root, query, cb) -> {
            if (category == null) return null;
            return cb.equal(root.get("category"), category);
        };
    }

    public static Specification<Expenses> paymentMethodEquals(PaymentMethod paymentMethod) {
        return (root, query, cb) -> {
            if (paymentMethod == null) return null;
            return cb.equal(root.get("paymentMethod"), paymentMethod);
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
