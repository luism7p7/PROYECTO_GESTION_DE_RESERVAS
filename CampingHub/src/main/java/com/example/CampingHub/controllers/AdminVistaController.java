package com.example.CampingHub.controllers;

import com.example.CampingHub.services.FincaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // <--- ¡Anotación clave!
import org.springframework.ui.Model; // Necesario para enviar datos a la vista
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    @Autowired
    private FincaService fincaService;

    // Constructor (opcional si usas @Autowired en el campo)
    public AdminViewController(FincaService fincaService) {
        this.fincaService = fincaService;
    }

    // Método que carga la lista de fincas y devuelve el index.html
    // Si tu aplicación ya tiene este método en otro lado, muévelo aquí.
    @GetMapping
    public String listarFincas(Model model) {
        // Debes implementar este método en tu FincaService:
        model.addAttribute("fincas", fincaService.obtenerTodasLasFincas());
        return "index";
    }

    // MÉTODO PARA ELIMINAR LA FINCA (LA SOLUCIÓN AL PROBLEMA)
    @PostMapping("/eliminar-finca/{id}")
    public String eliminarFinca(@PathVariable Long id) {
        // Ejecuta la eliminación en la base de datos
        fincaService.eliminarFinca(id);

        // Redirige al navegador a la URL /admin para recargar la lista
        return "redirect:/admin";
    }
}