package co.edu.uco.HumanSolution.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.HumanSolution"})
@EntityScan(basePackages = {"co.edu.uco.HumanSolution.entity"})
public class HumanSolutionApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(HumanSolutionApplication.class, args);
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 Servidor REST API HumanSolution iniciado en http://localhost:8080");
        System.out.println("=".repeat(60));
        System.out.println("📋 Endpoints disponibles:");
        System.out.println("   GET    /api/v1/roles");
        System.out.println("   GET    /api/v1/roles/{id}");
        System.out.println("   GET    /api/v1/usuarios");
        System.out.println("   POST   /api/v1/usuarios");
        System.out.println("   GET    /api/v1/contratos");
        System.out.println("   POST   /api/v1/contratos");
        System.out.println("=".repeat(60) + "\n");
    }
}

