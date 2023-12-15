import { Component, OnDestroy, OnInit } from '@angular/core';
import { CompetitionServiceService } from '../../services/competitionService/competition-service.service';
import { Datastate, State } from '../../state/state';
import { CompetitionList } from '../../model/competition/competition-list';
import { Observable, catchError, map, of, startWith } from 'rxjs';

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrl: './competition-list.component.css'
})
export class CompetitionListComponent implements OnInit, OnDestroy {
  competitionList!: State<CompetitionList[]>;
  currentPage:number=0;
  readonly dataState = Datastate;
constructor(private competitionService: CompetitionServiceService){}
ngOnInit(): void {
  this.getAllCompetition()
}
getAllCompetition(){
this.competitionService.get(this.currentPage).pipe(
  map(response => ({ dataState: this.dataState.LOADED, dataCompetition: response.body || []})),
  startWith({ dataState:Datastate.LOADING}),
  catchError(err =>of({ dataState: this.dataState.LOADED, error: err.error}))
  ).subscribe({
    next: data =>  this.competitionList = data

  });
}
ngOnDestroy(): void {
    
}
}
