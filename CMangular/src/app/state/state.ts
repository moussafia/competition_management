export enum Datastate{
    LOADING, LOADED, ERROR
}
export interface  State<T>{
    dataState: Datastate,
    dataCompetition?: T,
    error?: string
}