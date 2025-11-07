# Solución a Errores de Compilación JUnit

Si estás viendo estos errores:
- `package org.junit.jupiter.api does not exist`
- `cannot find symbol class Test`

## Causa del Problema

Las dependencias de JUnit 5 no están siendo reconocidas por tu IDE o Maven no las ha descargado correctamente.

## Solución Rápida

### Paso 1: Actualizar el proyecto desde Git

```bash
git pull origin claude/view-directory-files-011CUsiDtV7t8iUMKbjvSstc
```

### Paso 2: Limpiar y recompilar

**En Windows (tu caso):**
```cmd
cd C:\Users\samue\OneDrive\Escritorio\HumanSolution
mvnw.cmd clean install -DskipTests
```

**En Linux/Mac:**
```bash
./mvnw clean install -DskipTests
```

### Paso 3: Recargar proyecto en tu IDE

#### IntelliJ IDEA:
1. Click derecho en el proyecto
2. Maven → Reload Project
3. File → Invalidate Caches / Restart
4. Reinicia IntelliJ

#### Eclipse:
1. Click derecho en el proyecto
2. Maven → Update Project
3. Marca "Force Update of Snapshots/Releases"
4. Project → Clean

#### VS Code:
1. Cierra VS Code
2. Elimina la carpeta `.vscode` del proyecto
3. Abre VS Code nuevamente
4. Deja que recargue el proyecto

## Verificar que Funciona

Ejecuta los tests existentes:
```bash
mvnw.cmd test
```

Deberías ver algo como:
```
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Si el Problema Persiste

### Opción 1: Eliminar caché de Maven
```bash
# Windows
rmdir /s /q %USERPROFILE%\.m2\repository\org\junit
mvnw.cmd clean install -DskipTests

# Linux/Mac
rm -rf ~/.m2/repository/org/junit
./mvnw clean install -DskipTests
```

### Opción 2: Verificar Java
Asegúrate de tener Java 21:
```bash
java -version
```

Debe mostrar: `openjdk version "21.x.x"` o similar

Si no tienes Java 21, descárgalo de:
- https://adoptium.net/temurin/releases/ (recomendado)
- https://www.oracle.com/java/technologies/downloads/#java21

### Opción 3: Configurar JAVA_HOME

**Windows:**
```cmd
setx JAVA_HOME "C:\Program Files\Java\jdk-21"
```

**Linux/Mac:**
```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

## Cambios Realizados en pom.xml

El `pom.xml` ahora incluye:

```xml
<!-- JUNIT 5 (JUnit Jupiter) - Dependencias explícitas -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <scope>test</scope>
</dependency>
```

Y el plugin:

```xml
<!-- MAVEN SUREFIRE PLUGIN (para ejecutar tests con JUnit 5) -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
</plugin>
```

## Nota Sobre UsuarioExceptionTest.java

Si tienes un archivo `UsuarioExceptionTest.java` en tu proyecto local que no está en el repositorio, asegúrate de que tenga los imports correctos:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioExceptionTest {

    @Test
    void miTest() {
        // Tu código de test aquí
    }
}
```

## Contacto

Si después de seguir todos estos pasos el problema persiste, comparte:
1. La salida completa de `mvn -version`
2. La salida completa de `java -version`
3. El contenido completo del error
