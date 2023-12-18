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
    rankingList:RankingList[]
}
export interface RankingList{
    competitionResponseDto: CompetitionResponseDto,
    memberResponseDto: MemberResponseDto,
    rank: number,
    score: number
}
export interface CompetitionResponseDto{
    code: string,
    date: string,
    startDate: string,
    endTime: string,
    numberOfParticipants: number,
    location: string,
    amount: number
}
export interface MemberResponseDto{
    firstName: string,
    lastName: string,
    identityDocumentType: string,
    identityNumber: string,
    nationality: string,
    number: number,
    dateAccession: string
}