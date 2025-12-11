package com.klaus.backend.Service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.klaus.backend.Exception.ResourceNotFoundException;
import com.klaus.backend.Model.User;
import com.klaus.backend.Model.Enum.ExpenseType;
import com.klaus.backend.Repository.ExpensesRepository;
import com.klaus.backend.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService{
    private final ExpensesRepository expensesRepository;
    private final UserRepository userRepository;

    public BigDecimal getAvailableBalance(String usuario){
        User user = userRepository.findByUsername(usuario)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        
        BigDecimal totalExpenses = expensesRepository.sumTotalByUser(usuario);

        return user.getSalary().subtract(totalExpenses);
    }

    public BigDecimal getTotalExpenses(String usuario){
        userRepository.findByUsername(usuario)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        
        return expensesRepository.sumTotalByUser(usuario);
    }

    public BigDecimal getTotalExpensesByType(String usuario, ExpenseType type){
        userRepository.findByUsername(usuario)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return expensesRepository.sumByType(usuario, type);
    }
}