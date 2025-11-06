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
                    try {
                        String methods = key.getMethodsCondition() != null && !key.getMethodsCondition().getMethods().isEmpty() 
                            ? key.getMethodsCondition().getMethods().toString().replaceAll("[\\[\\]]", "")
                            : "ALL";
                        
                        String patterns = "";
                        if (key.getPatternsCondition() != null && key.getPatternsCondition().getPatterns() != null) {
                            patterns = key.getPatternsCondition().getPatterns().toString();
                        } else if (key.getPathPatternsCondition() != null && key.getPathPatternsCondition().getPatterns() != null) {
                            // Para Spring Boot 3.x, puede usar PathPatternsCondition
                            patterns = key.getPathPatternsCondition().getPatterns().toString();
                        } else {
                            patterns = "[N/A]";
                        }
                        
                        System.out.println("   ✅ " + methods + " " + patterns);
                    } catch (Exception e) {
                        // Si hay algún error al obtener la información del endpoint, simplemente mostrar el método
                        System.out.println("   ✅ Endpoint registrado: " + value.getMethod().getName());
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("   ⚠️  Error al listar endpoints: " + e.getMessage());
            // No imprimir el stack trace completo para evitar ruido
        }
        
        System.out.println("=".repeat(60) + "\n");
    }
}

