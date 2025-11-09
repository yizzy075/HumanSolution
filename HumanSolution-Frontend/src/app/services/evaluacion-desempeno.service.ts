import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EvaluacionDesempeno } from '../models/evaluacion-desempeno.model';

@Injectable({
  providedIn: 'root'
})
export class EvaluacionDesempenoService {
  private apiUrl = 'http://localhost:8080/api/v1/evaluaciones-desempeno';

  constructor(private http: HttpClient) { }

  registrar(evaluacion: EvaluacionDesempeno): Observable<any> {
    return this.http.post(this.apiUrl, evaluacion);
  }

  obtenerTodas(): Observable<EvaluacionDesempeno[]> {
    return this.http.get<EvaluacionDesempeno[]>(this.apiUrl);
  }

  obtenerPorId(id: string): Observable<EvaluacionDesempeno> {
    return this.http.get<EvaluacionDesempeno>(`${this.apiUrl}/${id}`);
  }

  obtenerPorUsuario(idUsuario: string): Observable<EvaluacionDesempeno[]> {
    return this.http.get<EvaluacionDesempeno[]>(`${this.apiUrl}/usuario/${idUsuario}`);
  }

  actualizar(id: string, evaluacion: EvaluacionDesempeno): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, evaluacion);
  }

  eliminar(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
