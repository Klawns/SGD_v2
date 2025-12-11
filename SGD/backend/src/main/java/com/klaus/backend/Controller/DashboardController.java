package com.klaus.backend.Controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klaus.backend.Model.Enum.ExpenseType;
import com.klaus.backend.Service.DashboardService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/expenses/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/available-balance")
    public ResponseEntity<BigDecimal> getAvailableBalance(@RequestParam String usuario) {
        return ResponseEntity.ok(dashboardService.getAvailableBalance(usuario));
    }

    @GetMapping("/total-expenses")
    public ResponseEntity<BigDecimal> getTotalExpenses(@RequestParam String usuario) {
        return ResponseEntity.ok(dashboardService.getTotalExpenses(usuario));
    }

    
    @GetMapping("/total-expenses-by-type")
    public ResponseEntity<BigDecimal> getTotalExpensesByType(@RequestParam String usuario, @RequestParam ExpenseType type) {
        return ResponseEntity.ok(dashboardService.getTotalExpensesByType(usuario, type));
    }
}
