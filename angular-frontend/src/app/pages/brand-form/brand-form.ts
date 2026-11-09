import { Component } from '@angular/core';
import { RequestBrandDto } from '../../../types/brand-types';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-brand-form',
  styleUrl: './brand-form.css',
  templateUrl: './brand-form.html',
  standalone: true,
})
export class BrandForm {

  brand: RequestBrandDto = {
    name: '',
    country: '',
    continent: '',
  };

  saveBrand() {
    console.log('Brand saved:', this.brand);
  }
}
