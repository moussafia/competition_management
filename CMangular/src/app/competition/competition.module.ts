import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompetitionListComponent } from './competition-list/competition-list.component';
import { FiltreComponent } from './competition-list/filtre/filtre.component';
import { CardComponent } from './competition-list/card/card.component';
import { CompetitionPageComponent } from './competition-page/competition-page.component';



@NgModule({
  declarations: [
  CompetitionListComponent,
  FiltreComponent,
  CardComponent,
  CompetitionPageComponent,
  CompetitionPageComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CompetitionListComponent,
    FiltreComponent,
    CardComponent,
    CommonModule,
    CompetitionPageComponent

  ]
})
export class CompetitionModule { }
