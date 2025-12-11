package com.klaus.backend.Repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.klaus.backend.Model.Expenses;
import com.klaus.backend.Model.Enum.ExpenseType;

public interface ExpensesRepository extends JpaRepository<Expenses, Long>, JpaSpecificationExecutor<Expenses> {
    Optional<Expenses> findByIdAndUsername(long id, String username);

    @Query("SELECT COALESCE(SUM(e.amount), 0) " + "FROM Expenses e " + "WHERE e.user.username = :username")
    BigDecimal sumTotalByUser(@Param("username") String username);

    @Query("SELECT COALESCE(SUM(e.amount), 0) " + "FROM Expenses e " + "WHERE e.user.username = :username AND e.type = :type")
    BigDecimal sumByType(@Param("username") String username, @Param("type") ExpenseType type);
}