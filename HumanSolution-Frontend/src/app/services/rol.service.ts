import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ResponseDTO<T> {
  success: boolean;
  message: string;
  data: T | null;
}

export interface RolDTO {
  id: string;
  nombre: string;
}

@Injectable({
  providedIn: 'root'
})
export class RolService {
  private apiUrl = 'http://localhost:8080/api/v1/roles';

  constructor(private http: HttpClient) {}

  // Listar todos los roles
  listarRoles(): Observable<ResponseDTO<RolDTO[]>> {
    return this.http.get<ResponseDTO<RolDTO[]>>(this.apiUrl);
  }

  // Obtener rol por ID
  obtenerRolPorId(id: string): Observable<ResponseDTO<RolDTO>> {
    return this.http.get<ResponseDTO<RolDTO>>(`${this.apiUrl}/${id}`);
  }
}

