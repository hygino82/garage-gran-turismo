import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BrandForm } from './pages/brand-form/brand-form';

@Component({
  imports: [RouterOutlet, BrandForm],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('angular-frontend');
}
