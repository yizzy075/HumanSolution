# HumanSolution - Instrucciones de Configuración

## Problemas Corregidos

He analizado y corregido los siguientes problemas en tu proyecto:

### 1. RolController Faltante
- **Problema**: El frontend esperaba endpoints `/api/v1/roles` pero no existían
- **Solución**: Creado `RolController.java` con todos los endpoints CRUD necesarios

### 2. Configuración CORS
- **Problema**: El backend rechazaba peticiones desde el frontend
- **Solución**: Creado `CorsConfig.java` que permite peticiones desde `http://localhost:4200`

### 3. Documentación de Base de Datos
- **Solución**: Agregados comentarios en `application.properties` sobre la configuración de PostgreSQL

## Requisitos Previos

1. **Java 21** instalado
2. **PostgreSQL** instalado y corriendo en puerto 5432
3. **Node.js** (para Angular)
4. **Maven** (opcional, el proyecto incluye Maven Wrapper)

## Configuración de Base de Datos

1. Asegúrate de que PostgreSQL esté corriendo:
   ```bash
   sudo service postgresql start  # Linux
   ```

2. Crea la base de datos si no existe:
   ```bash
   psql -U postgres
   CREATE DATABASE sistema_usuarios;
   \q
   ```

3. Verifica/actualiza las credenciales en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=postgres
   spring.datasource.password=dino2020  # Cambia esto según tu configuración
   ```

## Ejecutar el Backend

### Opción 1: Usando Maven directamente
```bash
mvn spring-boot:run
```

### Opción 2: Usando Maven Wrapper
```bash
./mvnw spring-boot:run
```

### Opción 3: Compilar y ejecutar JAR
```bash
mvn clean package -DskipTests
java -jar target/HumanSolution-1.0-SNAPSHOT.jar
```

El backend estará disponible en: `http://localhost:8080`

## Ejecutar el Frontend

1. Navega al directorio del frontend:
   ```bash
   cd HumanSolution-Frontend
   ```

2. Instala las dependencias (primera vez):
   ```bash
   npm install
   ```

3. Ejecuta el servidor de desarrollo:
   ```bash
   npm start
   ```

El frontend estará disponible en: `http://localhost:4200`

## Verificar Conexión

1. **Backend funcionando**: Abre `http://localhost:8080/api/v1/usuarios` en el navegador
   - Deberías ver un JSON (puede estar vacío `[]` si no hay usuarios)

2. **Frontend funcionando**: Abre `http://localhost:4200`
   - Deberías ver la interfaz de la aplicación
   - Verifica en la consola del navegador (F12) que no haya errores de CORS

## Endpoints Disponibles

### Usuarios
- `GET /api/v1/usuarios` - Listar usuarios
- `POST /api/v1/usuarios` - Crear usuario
- `GET /api/v1/usuarios/{id}` - Obtener por ID
- `GET /api/v1/usuarios/email/{email}` - Obtener por email
- `PUT /api/v1/usuarios/{id}` - Actualizar
- `DELETE /api/v1/usuarios/{id}` - Eliminar

### Roles
- `GET /api/v1/roles` - Listar roles
- `POST /api/v1/roles` - Crear rol
- `GET /api/v1/roles/{id}` - Obtener por ID
- `PUT /api/v1/roles/{id}` - Actualizar
- `DELETE /api/v1/roles/{id}` - Eliminar

### Contratos
- `GET /api/v1/contratos` - Listar contratos
- `POST /api/v1/contratos` - Crear contrato
- `GET /api/v1/contratos/{id}` - Obtener por ID
- `GET /api/v1/contratos/usuario/{idUsuario}` - Obtener por usuario
- `PUT /api/v1/contratos/{id}` - Actualizar
- `DELETE /api/v1/contratos/{id}` - Eliminar

## Solución de Problemas

### Error: "package org.junit.jupiter.api does not exist" o "cannot find symbol class Test"

Este error ocurre cuando las dependencias de JUnit no están descargadas correctamente. **Solución:**

1. **Limpiar proyecto y descargar dependencias:**
   ```bash
   # En la raíz del proyecto
   mvn clean install -DskipTests
   ```

2. **Si usas IntelliJ IDEA:**
   - Click derecho en el proyecto → Maven → Reload Project
   - File → Invalidate Caches / Restart
   - Asegúrate de que el JDK configurado sea Java 21

3. **Si usas Eclipse:**
   - Click derecho en el proyecto → Maven → Update Project
   - Project → Clean

4. **Si el problema persiste:**
   ```bash
   # Eliminar caché de Maven y recompilar
   mvn dependency:purge-local-repository
   mvn clean install -DskipTests
   ```

**Nota**: El `pom.xml` ahora incluye las dependencias de JUnit 5 explícitamente para evitar este problema.

### Error: "No se pudo conectar al servidor"
- Verifica que el backend esté corriendo en `http://localhost:8080`
- Verifica que no haya otro proceso usando el puerto 8080

### Error: "Connection refused" en PostgreSQL
- Verifica que PostgreSQL esté corriendo
- Verifica el puerto (por defecto 5432)
- Verifica usuario y contraseña en `application.properties`

### Error: "Database does not exist"
- Crea la base de datos: `CREATE DATABASE sistema_usuarios;`

### Error de CORS
- Verifica que `CorsConfig.java` esté en el classpath
- Verifica que el frontend corra en `http://localhost:4200`

### Error: "Port already in use"
- Backend: Cambia el puerto en `application.properties`: `server.port=8081`
- Frontend: Usa `ng serve --port 4201`

## Archivos Creados/Modificados

### Nuevos Archivos
1. `src/main/java/co/edu/uco/HumanSolution/controller/RolController.java`
2. `src/main/java/co/edu/uco/HumanSolution/config/CorsConfig.java`

### Archivos Modificados
1. `src/main/resources/application.properties` - Agregados comentarios

## Próximos Pasos

1. Inicia PostgreSQL y crea la base de datos
2. Ejecuta el backend
3. Ejecuta el frontend
4. Prueba registrar un usuario desde la interfaz web

Si encuentras más problemas, revisa los logs del backend en la consola donde lo ejecutaste.
