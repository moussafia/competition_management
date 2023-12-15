import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompetitionListComponent } from './competition/competition-list/competition-list.component';

const routes: Routes = [
  {path:"/competitions", component: CompetitionListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
