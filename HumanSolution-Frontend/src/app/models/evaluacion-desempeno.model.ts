import { Usuario } from './usuario.model';

export interface EvaluacionDesempeno {
  id?: string;
  usuario: Usuario;
  fecha: string; // formato YYYY-MM-DD
  calificacion: number;
  observacion: string;
}
