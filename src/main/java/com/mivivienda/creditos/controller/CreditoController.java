package com.mivivienda.creditos.controller;

import com.mivivienda.creditos.model.Credito;
import com.mivivienda.creditos.service.CreditoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    private final CreditoService service;

    public CreditoController(CreditoService service) {
        this.service = service;
    }

    // Endpoint para registrar un crédito
    @PostMapping
    public ResponseEntity<Credito> registrarCredito(@Valid @RequestBody Credito credito) {
        Credito registrado = service.registrarCredito(credito);
        return ResponseEntity.ok(registrado);
    }

    // Endpoint para listar créditos
    @GetMapping
    public ResponseEntity<List<Credito>> listarCreditos() {
        return ResponseEntity.ok(service.listarCreditos());
    }

    // Endpoint para importar datos masivos desde Excel
    @PostMapping("/importar")
    public ResponseEntity<List<Credito>> importarExcel(@RequestParam("archivo") MultipartFile archivo) throws Exception {
        List<Credito> creditosImportados = service.importarExcel(archivo);
        return ResponseEntity.ok(creditosImportados);
    }
}
