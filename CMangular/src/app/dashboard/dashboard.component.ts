import { Component, OnInit } from '@angular/core';
import { CompetitionServiceService } from '../services/competitionService/competition-service.service';
import { CompetitionListWithRank } from '../model/competition/competition-list';
import { catchError, map, of, startWith } from 'rxjs';
import { Datastate, State } from '../state/state';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
competitionData?:State<CompetitionListWithRank>;
constructor(private competitionService: CompetitionServiceService){}
ngOnInit(): void {
    this.getcompetitionOfDayNow();
}
readonly dataState = Datastate;
getcompetitionOfDayNow(){
  this.competitionService.getDetailsCompetitionOfDateNow().pipe(
    map(response=>{
        return ({dataState: this.dataState.LOADED, data: response.body || undefined})
    }),
    startWith({dataState: this.dataState.LOADING}),
    catchError(err=> of({dataState: this.dataState.ERROR, error: err.error.message}))
  ).subscribe({
    next: data => { this.competitionData = data; 
         console.log(this.competitionData)
    }
  });
}

}
