import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { EvaluacionDesempenoService } from '../../services/evaluacion-desempeno.service';
import { UsuarioService } from '../../services/usuario.service';
import { EvaluacionDesempeno } from '../../models/evaluacion-desempeno.model';
import { Usuario } from '../../models/usuario.model';

@Component({
  selector: 'app-registro-evaluacion-desempeno',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './registro-evaluacion-desempeno.component.html',
  styleUrls: ['./registro-evaluacion-desempeno.component.css']
})
export class RegistroEvaluacionDesempenoComponent implements OnInit {
  evaluacionForm: FormGroup;
  usuarios: Usuario[] = [];
  mensaje: string = '';
  tipoMensaje: 'success' | 'error' = 'success';
  mostrarMensaje: boolean = false;
  fechaMaxima: string = '';

  constructor(
    private fb: FormBuilder,
    private evaluacionService: EvaluacionDesempenoService,
    private usuarioService: UsuarioService
  ) {
    this.evaluacionForm = this.fb.group({
      usuario: ['', Validators.required],
      fecha: ['', Validators.required],
      calificacion: ['', [Validators.required, Validators.min(1), Validators.max(10)]],
      observacion: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(500)]]
    });
  }

  ngOnInit(): void {
    this.cargarUsuarios();
    this.setFechaMaxima();
  }

  setFechaMaxima(): void {
    const hoy = new Date();
    this.fechaMaxima = hoy.toISOString().split('T')[0];
  }

  cargarUsuarios(): void {
    this.usuarioService.obtenerUsuarios().subscribe({
      next: (data) => {
        this.usuarios = data;
        console.log('Usuarios cargados:', this.usuarios);
      },
      error: (error) => {
        console.error('Error al cargar usuarios:', error);
        this.mostrarMensajeAlerta('Error al cargar la lista de usuarios', 'error');
      }
    });
  }

  onSubmit(): void {
    if (this.evaluacionForm.valid) {
      const formData = this.evaluacionForm.value;

      // Buscar el usuario seleccionado
      const usuarioSeleccionado = this.usuarios.find(u => u.id === formData.usuario);

      if (!usuarioSeleccionado) {
        this.mostrarMensajeAlerta('Usuario no encontrado', 'error');
        return;
      }

      const evaluacion: EvaluacionDesempeno = {
        usuario: usuarioSeleccionado,
        fecha: formData.fecha,
        calificacion: parseInt(formData.calificacion),
        observacion: formData.observacion
      };

      console.log('Registrando evaluación:', evaluacion);

      this.evaluacionService.registrar(evaluacion).subscribe({
        next: (response) => {
          console.log('Evaluación registrada exitosamente:', response);
          this.mostrarMensajeAlerta('Evaluación de desempeño registrada exitosamente', 'success');
          this.evaluacionForm.reset();
        },
        error: (error) => {
          console.error('Error al registrar evaluación:', error);
          const mensajeError = error.error?.error || 'Error al registrar la evaluación de desempeño';
          this.mostrarMensajeAlerta(mensajeError, 'error');
        }
      });
    } else {
      this.mostrarMensajeAlerta('Por favor complete todos los campos correctamente', 'error');
      this.marcarCamposComoTocados();
    }
  }

  marcarCamposComoTocados(): void {
    Object.keys(this.evaluacionForm.controls).forEach(key => {
      this.evaluacionForm.get(key)?.markAsTouched();
    });
  }

  mostrarMensajeAlerta(mensaje: string, tipo: 'success' | 'error'): void {
    this.mensaje = mensaje;
    this.tipoMensaje = tipo;
    this.mostrarMensaje = true;
    setTimeout(() => {
      this.mostrarMensaje = false;
    }, 5000);
  }

  get f() {
    return this.evaluacionForm.controls;
  }
}
