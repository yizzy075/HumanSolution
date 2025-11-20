import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = 'http://localhost:8080/api/v1/usuarios';
  private rolesUrl = 'http://localhost:8080/api/v1/roles';

  constructor(private http: HttpClient) {
    console.log('✅ UsuarioService inicializado');
  }

  // ✅ Registrar usuario
  registrarUsuario(usuario: any): Observable<any> {
    const usuarioDTO = {
      id: usuario.id || '',
      documento: usuario.numeroDocumento || usuario.documento || '',
      nombre: `${usuario.nombre || ''} ${usuario.apellido || ''}`.trim(),
      correo: usuario.correo || usuario.correoElectronico || '',
      contrasena: usuario.contrasenia || usuario.contrasena || '',
      rol: {
        id: usuario.rol?.id || usuario.idRol || ''
      }
    };

    console.log('📤 Enviando usuarioDTO al backend:', usuarioDTO);

    return this.http.post<any>(this.apiUrl, usuarioDTO)
      .pipe(
        catchError(this.handleError)
      );
  }

  // ✅ Obtener lista de usuarios
  obtenerUsuarios(): Observable<any> {
    console.log('📤 Solicitando usuarios desde:', this.apiUrl);
    return this.http.get<any>(this.apiUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  // ✅ Obtener usuario por ID
  obtenerUsuarioPorId(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // ✅ Actualizar usuario
  actualizarUsuario(id: string, usuario: any): Observable<any> {
    const usuarioDTO = {
      id: id,
      documento: usuario.numeroDocumento || usuario.documento || '',
      nombre: `${usuario.nombre || ''} ${usuario.apellido || ''}`.trim(),
      correo: usuario.correo || usuario.correoElectronico || '',
      contrasena: usuario.contrasenia || usuario.contrasena || '',
      rol: {
        id: typeof usuario.rol === 'string' ? usuario.rol : usuario.rol?.id || usuario.idRol || ''
      }
    };

    console.log('📤 Actualizando usuarioDTO:', usuarioDTO);

    return this.http.put<any>(`${this.apiUrl}/${id}`, usuarioDTO)
      .pipe(
        catchError(this.handleError)
      );
  }

  // ✅ Eliminar usuario
  eliminarUsuario(id: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // ✅ Obtener lista de roles
  obtenerRoles(): Observable<any> {
    console.log('📤 Solicitando roles desde:', this.rolesUrl);
    return this.http.get<any>(this.rolesUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  // ✅ Obtener rol por ID
  obtenerRolPorId(id: string): Observable<any> {
    return this.http.get<any>(`${this.rolesUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // ✅ Manejo de errores mejorado
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Error desconocido';

    if (error.error instanceof ErrorEvent) {
      // Error del lado del cliente
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Error del lado del servidor
      errorMessage = `Código de error: ${error.status}\nMensaje: ${error.message}`;

      if (error.status === 0) {
        errorMessage = '❌ No se puede conectar al servidor. Verifica que el backend esté corriendo en http://localhost:8080';
      } else if (error.status === 404) {
        errorMessage = '❌ Recurso no encontrado (404)';
      } else if (error.status === 500) {
        errorMessage = '❌ Error interno del servidor (500)';
      }
    }

    console.error('❌ Error HTTP:', errorMessage);
    console.error('Detalles completos:', error);

    return throwError(() => new Error(errorMessage));
  }
}
