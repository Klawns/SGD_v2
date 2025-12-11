package com.klaus.backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.klaus.backend.Model.Expenses;

public interface ExpensesRepository extends JpaRepository<Expenses, Long>, JpaSpecificationExecutor<Expenses> {
    Optional<Expenses> findByIdAndUsername(long id, String username);
}