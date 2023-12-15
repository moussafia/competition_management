import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompetitionListComponent } from './competition-list/competition-list.component';
import { FiltreComponent } from './competition-list/filtre/filtre.component';
import { CardComponent } from './competition-list/card/card.component';
import { CompetitionPageComponent } from './competition-page/competition-page.component';
import { TableRankingComponent } from './competition-page/table-ranking/table-ranking.component';
import { PaginationComponent } from './competition-list/pagination/pagination.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
  CompetitionListComponent,
  FiltreComponent,
  CardComponent,
  CompetitionPageComponent,
  CompetitionPageComponent,
  TableRankingComponent,
  PaginationComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    CompetitionListComponent,
    FiltreComponent,
    CardComponent,
    CommonModule,
    CompetitionPageComponent,
    PaginationComponent,

  ]
})
export class CompetitionModule { }
