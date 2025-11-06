package co.edu.uco.HumanSolution.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.method.HandlerMethod;

import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.HumanSolution"})
@EntityScan(basePackages = {"co.edu.uco.HumanSolution.entity"})
public class HumanSolutionApplication {
    
    public static void main(String[] args) {
        System.out.println("=== INICIANDO HumanSolutionApplication ===");
        SpringApplication.run(HumanSolutionApplication.class, args);
<<<<<<< HEAD
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady(ApplicationReadyEvent event) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 Servidor REST API HumanSolution iniciado");
        System.out.println("📍 URL: http://localhost:8080");
        System.out.println("=".repeat(60));
        
        // Verificar que los controladores estén registrados
        ApplicationContext context = event.getApplicationContext();
        try {
            RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
            Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
            
            System.out.println("📋 Endpoints registrados (" + map.size() + "):");
            if (map.isEmpty()) {
                System.out.println("   ⚠️  NO SE ENCONTRARON ENDPOINTS REGISTRADOS");
                System.out.println("   Verifique que los controladores estén correctamente anotados con @RestController");
            } else {
                map.forEach((key, value) -> {
                    String methods = key.getMethodsCondition().getMethods().isEmpty() 
                        ? "ALL" 
                        : key.getMethodsCondition().getMethods().toString().replaceAll("[\\[\\]]", "");
                    System.out.println("   ✅ " + methods + " " + key.getPatternsCondition().getPatterns());
                });
            }
        } catch (Exception e) {
            System.out.println("   ⚠️  Error al listar endpoints: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("=".repeat(60) + "\n");
=======
        System.out.println("🚀 Servidor REST API HumanSolution iniciado en http://localhost:8080");
>>>>>>> parent of 7105c49 (errores)
    }
}

