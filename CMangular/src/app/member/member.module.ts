import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MemberListComponent } from './member-list/member-list.component';
import { CreateMemberComponent } from './create-member/create-member.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TableComponent } from './member-list/table/table.component';
import { SearchComponent } from './member-list/search/search.component';



@NgModule({
  declarations: [
    MemberListComponent,
    TableComponent,
    SearchComponent,
    CreateMemberComponent,
    
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule

  ],
  exports: [
    MemberListComponent,
    TableComponent,
    SearchComponent,
    CreateMemberComponent
  ]
})
export class MemberModule { }
