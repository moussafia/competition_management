export interface CompetitionList {
    code:string,
    date:string,
    startDate:string,
    endTime:string,
    numberOfParticipants: number,
    location: string,
    amount: number,
}
export interface CompetitionListWithRank {
    code:string,
    date:string,
    startDate:string,
    endTime:string,
    numberOfParticipants: number,
    location: string,
    amount: number,
    rankingList?:any

}
