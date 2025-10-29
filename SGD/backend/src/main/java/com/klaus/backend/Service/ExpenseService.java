package com.klaus.backend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.klaus.backend.Model.Expenses;
import com.klaus.backend.Repository.ExpensesRepository;
import com.klaus.backend.Repository.QueryFilter.ExpensesQueryFilter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpensesRepository expensesRepository;

    public Page<Expenses> listExpenses(Pageable pageable, ExpensesQueryFilter filter) {
        return expensesRepository.findAll(filter.toSpecification(), pageable);
    }

}
