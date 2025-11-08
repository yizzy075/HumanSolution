package co.edu.uco.HumanSolution.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.HumanSolution"})
@EntityScan(basePackages = {"co.edu.uco.HumanSolution.entity"})
public class    HumanSolutionApplication {
    
    public static void main(String[] args) {
        System.out.println("=== INICIANDO HumanSolutionApplication ===");
        SpringApplication.run(HumanSolutionApplication.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady(ApplicationReadyEvent event) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 Servidor REST API HumanSolution iniciado");
        System.out.println("📍 URL: http://localhost:8080");
        System.out.println("=".repeat(60) + "\n");
    }
}

