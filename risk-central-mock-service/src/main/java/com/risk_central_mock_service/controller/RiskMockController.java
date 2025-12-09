package com.risk_central_mock_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class RiskMockController {

    @PostMapping("/risk-evaluation")
    public ResponseEntity<Map<String, Object>> evaluateRisk(@RequestBody Map<String, Object> request) {
        String documento = (String) request.get("documento");

        // Convertir el documento en un seed numérico (hash mod 1000)
        long seed = documento.hashCode();

        // Generar score consistente para ese documento [cite: 69]
        Random random = new Random(seed);
        int score = 300 + random.nextInt(651);


        String nivelRiesgo;
        if (score <= 500) {
            nivelRiesgo = "ALTO RIESGO"; // 300-500 [cite: 76]
        } else if (score <= 700) {
            nivelRiesgo = "MEDIO RIESGO"; // 501-700 [cite: 77]
        } else {
            nivelRiesgo = "BAJO RIESGO"; // 701-950 [cite: 78]
        }

        Map<String, Object> response = new HashMap<>();
        response.put("documento", documento);
        response.put("score", score);
        response.put("nivelRiesgo", nivelRiesgo);
        response.put("detalle", "Evaluación generada automáticamente por Mock Central.");

        return ResponseEntity.ok(response);
    }
}