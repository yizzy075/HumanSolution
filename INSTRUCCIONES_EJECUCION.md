# 🚀 Cómo Ejecutar la Aplicación HumanSolution

## Opción 1: Desde el IDE (Recomendado)

### IntelliJ IDEA / Eclipse / VS Code
1. Abre el proyecto en tu IDE
2. Busca la clase `HumanSolutionApplication.java` en:
   ```
   src/main/java/co/edu/uco/HumanSolution/initializer/HumanSolutionApplication.java
   ```
3. Haz clic derecho → **Run 'HumanSolutionApplication'** o presiona `Shift + F10`
4. Espera a ver el mensaje:
   ```
   🚀 Servidor REST API HumanSolution iniciado en http://localhost:8080
   ```

---

## Opción 2: Usando Maven desde Terminal

### Si tienes Maven instalado:

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn spring-boot:run
```

### Si NO tienes Maven instalado:

1. **Instala Maven:**
   - Descarga desde: https://maven.apache.org/download.cgi
   - Agrega `MAVEN_HOME/bin` a tu PATH
   - Verifica: `mvn --version`

2. **Luego ejecuta:**
   ```bash
   mvn spring-boot:run
   ```

---

## Opción 3: Usando el Script Batch (Windows)

1. **Ejecuta el archivo:**
   ```
   iniciar-servidor.bat
   ```

2. El script:
   - Verifica si Maven está instalado
   - Compila el proyecto
   - Inicia el servidor

---

## Verificar que la Aplicación Está Corriendo

### 1. Verifica en la consola:
Deberías ver mensajes como:
```
Tomcat started on port 8080 (http)
Started HumanSolutionApplication in X.XX seconds
```

### 2. Prueba en el navegador:
Abre: `http://localhost:8080/api/v1/usuarios`

### 3. Prueba con PowerShell:
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/v1/usuarios" -Method GET
```

### 4. Prueba con Bruno:
- URL: `http://localhost:8080/api/v1/usuarios`
- Método: `GET`
- Ver `BRUNO_API_COLLECTION.md` para más ejemplos

---

## ⚠️ Problemas Comunes

### Error: "Puerto 8080 ya está en uso"
```bash
# Encontrar el proceso
netstat -ano | findstr :8080

# Matar el proceso (reemplaza PID con el número que encontraste)
taskkill /PID [PID] /F
```

### Error: "No se puede conectar a la base de datos"
1. Verifica que PostgreSQL esté corriendo
2. Verifica las credenciales en `application.properties`:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_usuarios
   spring.datasource.username=postgres
   spring.datasource.password=dino2020
   ```
3. Verifica que la base de datos `sistema_usuarios` exista

### Error: "Maven no encontrado"
- Instala Maven y agrégalo al PATH
- O usa tu IDE para ejecutar la aplicación

---

## 📝 Notas

- La aplicación se ejecuta en: `http://localhost:8080`
- Los logs se muestran en la consola
- Para detener: Presiona `Ctrl + C` en la terminal
- Los cambios en el código requieren reiniciar la aplicación

---

## ✅ Una vez que la aplicación esté corriendo:

Puedes probar los endpoints con:
- **Bruno** (cliente HTTP)
- **Postman**
- **cURL**
- **PowerShell** (Invoke-RestMethod)
- **Navegador** (solo GET)

Ver `BRUNO_API_COLLECTION.md` para ejemplos completos de peticiones.

