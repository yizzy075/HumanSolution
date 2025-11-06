import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ResponseDTO<T> {
  success: boolean;
  message: string;
  data: T | null;
}

export interface UsuarioDTO {
  id?: string;
  documento: string;
  nombre: string;
  correo: string;
  contrasenia: string;
  idRol: string;
}

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = 'http://localhost:8080/api/v1/usuarios';

  constructor(private http: HttpClient) {}

  // Registrar usuario
  registrarUsuario(usuario: UsuarioDTO): Observable<ResponseDTO<UsuarioDTO>> {
    return this.http.post<ResponseDTO<UsuarioDTO>>(this.apiUrl, usuario);
  }

  // Listar todos los usuarios
  listarUsuarios(): Observable<ResponseDTO<UsuarioDTO[]>> {
    return this.http.get<ResponseDTO<UsuarioDTO[]>>(this.apiUrl);
  }

  // Obtener usuario por ID
  obtenerUsuarioPorId(id: string): Observable<ResponseDTO<UsuarioDTO>> {
    return this.http.get<ResponseDTO<UsuarioDTO>>(`${this.apiUrl}/${id}`);
  }

  // Obtener usuario por email
  obtenerUsuarioPorEmail(email: string): Observable<ResponseDTO<UsuarioDTO>> {
    return this.http.get<ResponseDTO<UsuarioDTO>>(`${this.apiUrl}/email/${email}`);
  }

  // Actualizar usuario
  actualizarUsuario(id: string, usuario: UsuarioDTO): Observable<ResponseDTO<UsuarioDTO>> {
    return this.http.put<ResponseDTO<UsuarioDTO>>(`${this.apiUrl}/${id}`, usuario);
  }

  // Eliminar usuario
  eliminarUsuario(id: string): Observable<ResponseDTO<void>> {
    return this.http.delete<ResponseDTO<void>>(`${this.apiUrl}/${id}`);
  }
}
