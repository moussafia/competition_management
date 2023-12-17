import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MemberServiceService } from '../../../services/memberService/member-service.service';
import { Datastate, State } from '../../../state/state';
import { MemberList } from '../../../model/member/member-list';
import { catchError, map, of, startWith } from 'rxjs';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {
formControlSearch?: FormGroup;

  memberList?:State<MemberList[]>;
  currentPage:number=0;
  pageTotal:number=0;

readonly dataState = Datastate

constructor(private builder: FormBuilder,
  private memberService:MemberServiceService){}
  ngOnInit(): void {
    this.formControlSearch = this.builder.group({
      keywordMember:[""]
    })
}
onSearchMember(){
  if(this.formControlSearch?.invalid) return;
  this.searchMember(this.formControlSearch?.value);
}
searchMember(keySearch: string):void{
    this.memberService.search(keySearch).pipe(
      map(response=> { console.log(response.body);
          let pageTotalHeader = response.headers.get('X-Total-Page');
          if(pageTotalHeader) this.pageTotal=  parseInt(pageTotalHeader);
          console.log(response.body);
          return ({ dataState: this.dataState.LOADED, data: response.body || []})
        }),
        startWith({ dataState:Datastate.LOADING}),
        catchError(err => of({ dataState: this.dataState.LOADED, error: err.error}))
        ).subscribe({
          next: data =>  this.memberList = data
        });
}
  
}
