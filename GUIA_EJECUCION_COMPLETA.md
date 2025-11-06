# 🚀 Guía para Ejecutar Backend y Frontend

## 📁 Estructura del Proyecto

```
HumanSolution/
├── src/                    # Backend (Spring Boot)
├── HumanSolution-Frontend/ # Frontend (Angular)
├── pom.xml                 # Configuración Maven
└── (otros archivos...)
```

**El frontend está dentro del proyecto backend**, lo cual es válido pero puede ser confuso.

---

## ✅ Opción 1: Ejecutar Todo Automáticamente (Recomendado)

### Script Todo-en-Uno:

**Ejecuta el archivo:**
```
iniciar-todo.bat
```

Este script:
1. ✅ Inicia el backend en una ventana separada
2. ✅ Instala dependencias del frontend si es necesario
3. ✅ Inicia el frontend en la ventana actual

---

## ✅ Opción 2: Ejecutar por Separado

### Paso 1: Backend

**Opción A - Desde tu IDE:**
- Abre `HumanSolutionApplication.java`
- Click derecho → Run

**Opción B - Desde terminal:**
```bash
# En la raíz del proyecto
mvnw.cmd spring-boot:run
```

**Opción C - Script:**
```bash
iniciar-servidor.bat
```

**Espera a ver:**
```
🚀 Servidor REST API HumanSolution iniciado en http://localhost:8080
```

---

### Paso 2: Frontend

**Opción A - Desde terminal:**
```bash
# Navega a la carpeta del frontend
cd HumanSolution-Frontend

# Instala dependencias (solo la primera vez)
npm install

# Inicia el servidor
npm start
```

**Opción B - Script:**
```bash
cd HumanSolution-Frontend
iniciar-frontend.bat
```

**Espera a ver:**
```
** Angular Live Development Server is listening on localhost:4200 **
```

---

## 🔍 Verificar que Todo Funciona

1. **Backend corriendo:**
   - Abre: `http://localhost:8080/api/v1/usuarios`
   - Deberías ver una respuesta JSON (puede estar vacía si no hay datos)

2. **Frontend corriendo:**
   - Abre: `http://localhost:4200`
   - Deberías ver la aplicación Angular

3. **Conexión entre ambos:**
   - Usa el formulario de registro en el frontend
   - Verifica en DevTools (F12 → Network) que las peticiones lleguen al backend

---

## ❗ Problemas Comunes

### Error: "ng no se reconoce"
**Solución:**
```bash
cd HumanSolution-Frontend
npm install -g @angular/cli
```

### Error: "No se encuentra package.json"
**Solución:** Asegúrate de estar en la carpeta `HumanSolution-Frontend`

### Error: "Puerto 4200 en uso"
**Solución:**
```bash
# Encontrar proceso
netstat -ano | findstr :4200

# Matar proceso (reemplaza PID con el número que encontraste)
taskkill /PID [PID] /F
```

### Backend no inicia
**Solución:**
- Verifica que PostgreSQL esté corriendo
- Verifica las credenciales en `application.properties`
- Verifica que la base de datos `sistema_usuarios` exista

---

## 📋 Comandos Rápidos

### Backend:
```bash
# En la raíz del proyecto
mvnw.cmd spring-boot:run
```

### Frontend:
```bash
# En HumanSolution-Frontend
npm start
```

---

## 🎯 Resumen

**NO necesitas separar el frontend del backend** - pueden estar juntos.

**Para ejecutar:**
1. ✅ Ejecuta `iniciar-todo.bat` (lo más fácil)
2. ✅ O ejecuta backend y frontend por separado en terminales diferentes

¡Todo debería funcionar! 🚀

