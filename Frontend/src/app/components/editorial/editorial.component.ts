import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EditorialService } from '../../services/editorial.service';
import { EditorialFormComponent } from '../editorial-form/editorial-form.component';
import { Editorial } from '../../models/editorial';

@Component({
  selector: 'app-editorial',
  templateUrl: './editorial.component.html',
  styleUrl: './editorial.component.css'
})
export class EditorialComponent {

  public editorials: Editorial[] = [];
  server: boolean = false;

  constructor(
    private editorialService: EditorialService,
    public dialog: MatDialog,
    private router: Router,
  ) { }

  ngOnInit() {
    if (!this.server) {
      this.listEditorials();
    }
  }

  listEditorials(): void {
    this.editorialService.all().subscribe(
      (data: Editorial[]) => {
        this.editorials = data;
        // console.log('Editoriales...', this.editorials);
        this.server = true;
        this.router.navigate(['/editorial']);
      },
      (error) => {
        console.error('Error al cargar editoriales', error);
        this.server = false;
      }
    );
  }

  openEditorialDialog(editorial?: Editorial) {
    const dialogRef = this.dialog.open(EditorialFormComponent, {
      data: editorial ? { ...editorial } : {}
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (editorial) {
          const id: number = editorial?.ideditorial ?? 0;
          this.editorialService.modify(result, id).subscribe(() => {
            this.listEditorials();
          });
        } else {
          this.editorialService.add(result).subscribe(() => {
            this.listEditorials();
          });
        }
      }
    });
  }

  onDelete(id: number | any): void {
    if (confirm('¿Estás seguro?')) {
      this.editorialService.delete(id).subscribe(() => {
        this.listEditorials();
      });
      this.router.navigate(['/editorial']);
    }
  }

}
