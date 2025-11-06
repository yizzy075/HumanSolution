package co.edu.uco.HumanSolution.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.HumanSolution"})
@EntityScan(basePackages = {"co.edu.uco.HumanSolution.entity"})
public class HumanSolutionApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(HumanSolutionApplication.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void printEndpoints(ApplicationReadyEvent event) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 Servidor REST API HumanSolution iniciado en http://localhost:8080");
        System.out.println("=".repeat(60));
        System.out.println("📋 Endpoints REST disponibles:");
        System.out.println("   GET    /api/v1/roles");
        System.out.println("   GET    /api/v1/roles/{id}");
        System.out.println("   GET    /api/v1/usuarios");
        System.out.println("   POST   /api/v1/usuarios");
        System.out.println("   GET    /api/v1/contratos");
        System.out.println("   POST   /api/v1/contratos");
        System.out.println("=".repeat(60) + "\n");
        
        // Mostrar endpoints registrados por Spring
        try {
            RequestMappingHandlerMapping mapping = event.getApplicationContext()
                    .getBean(RequestMappingHandlerMapping.class);
            mapping.getHandlerMethods().forEach((key, value) -> {
                System.out.println("✅ Mapped: " + key.toString());
            });
        } catch (Exception e) {
            System.out.println("⚠️  No se pudieron listar los endpoints mapeados");
        }
    }
}

