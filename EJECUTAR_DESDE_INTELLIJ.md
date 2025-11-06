# 🚀 Cómo Ejecutar el Backend desde IntelliJ IDEA

## ⚡ Método Recomendado (NO requiere JAVA_HOME)

### Paso 1: Abrir el proyecto en IntelliJ IDEA
1. Abre IntelliJ IDEA
2. File → Open → Selecciona la carpeta `HumanSolution`

### Paso 2: Ejecutar la aplicación
1. Navega a: `src/main/java/co/edu/uco/HumanSolution/initializer/HumanSolutionApplication.java`
2. Haz clic derecho en el archivo → **Run 'HumanSolutionApplication.main()'**
   - O presiona `Shift + F10`
   - O haz clic en el botón ▶️ verde junto al método `main()`

### Paso 3: Verificar que funciona
1. Espera a ver el mensaje: `🚀 Servidor REST API HumanSolution iniciado`
2. Deberías ver: `📋 Endpoints registrados (X):`
3. Deberías ver listados los endpoints como:
   ```
   ✅ GET [/api/v1/roles]
   ✅ GET [/api/v1/roles/{id}]
   ✅ POST [/api/v1/usuarios]
   ```

### Paso 4: Probar el endpoint
Abre en el navegador: `http://localhost:8080/api/v1/roles`

Deberías recibir un JSON con los roles.

---

## ⚠️ Si prefieres usar la terminal (requiere JAVA_HOME)

### Configurar JAVA_HOME en Windows:

1. **Encontrar la ruta de Java:**
   - Abre PowerShell o CMD
   - Ejecuta: `where java`
   - Copia la ruta (ej: `C:\Program Files\Java\jdk-21\bin\java.exe`)
   - La ruta de JAVA_HOME será: `C:\Program Files\Java\jdk-21`

2. **Configurar JAVA_HOME:**
   - Presiona `Win + R` → escribe `sysdm.cpl` → Enter
   - Pestaña "Opciones avanzadas" → "Variables de entorno"
   - En "Variables del sistema", haz clic en "Nueva"
   - Nombre: `JAVA_HOME`
   - Valor: `C:\Program Files\Java\jdk-21` (tu ruta)
   - Aceptar → Aceptar → Aceptar

3. **Reiniciar terminal:**
   - Cierra y vuelve a abrir PowerShell/CMD
   - Verifica: `echo %JAVA_HOME%`

4. **Ejecutar:**
   ```bash
   iniciar-servidor.bat
   ```

---

## 🔍 Verificar que los endpoints están registrados

Después de iniciar el servidor, busca en la consola:

```
📋 Endpoints registrados (X):
   ✅ GET [/api/v1/roles]
   ✅ GET [/api/v1/roles/{id}]
   ✅ POST [/api/v1/usuarios]
   ✅ GET [/api/v1/usuarios]
```

Si ves `⚠️ NO SE ENCONTRARON ENDPOINTS REGISTRADOS`, hay un problema con el registro de controladores.

---

## ❓ Problemas Comunes

### Error: "JAVA_HOME environment variable is not defined"
**Solución:** Ejecuta desde IntelliJ IDEA (no requiere JAVA_HOME)

### Error: "Port 8080 already in use"
**Solución:** 
- Detén cualquier otra aplicación usando el puerto 8080
- O cambia el puerto en `application.properties`

### Errores de compilación
**Solución:**
1. En IntelliJ: Build → Clean Project
2. Build → Rebuild Project
3. Ejecuta de nuevo

