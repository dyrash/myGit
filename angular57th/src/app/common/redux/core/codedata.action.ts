
import {
    Action,
    ActionCreator,
    AnyAction
  } from 'redux';
  
  export const INSERT: string = 'INSERT';
  export const insert: ActionCreator<AnyAction> = (diff:any[]) => ({
   
    type: INSERT,
    diff:diff
  });
  export const INSERT_AUTH: string = 'INSERT_AUTH';
  export const insertAuth: ActionCreator<AnyAction> = (diff:any[]) => ({
   
    type: INSERT_AUTH,
    diff:diff
  });
  export const INSERT_USER: string = 'INSERT_USER';
  export const insertUser: ActionCreator<AnyAction> = (diff:string) => ({
   
    type: INSERT_USER,
    diff:diff
  });
//   export const DECREMENT: string = 'DECREMENT';
//   export const decrement: ActionCreator<Action> = () => ({
//     type: DECREMENT
//   });
