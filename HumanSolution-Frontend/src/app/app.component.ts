import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistroUsuarioComponent } from './components/registro-usuario/registro-usuario.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RegistroUsuarioComponent],
  template: '<app-registro-usuario></app-registro-usuario>',
  styleUrls: ['./app.css']
})
export class AppComponent {
  title = 'HumanSolution-Frontend';
}
