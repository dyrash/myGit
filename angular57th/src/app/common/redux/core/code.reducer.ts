/**
 * Counter Reducer
 */
import { Reducer, Action, AnyAction } from 'redux';

import { INSERT,INSERT_AUTH, INSERT_USER } from './codedata.action';
import { dataState } from './code.state';


const initialState: dataState = { code: [],menuAuth:[{menuAuthorityList:["a"]}],deptCode:"",empCode:"",businessPlace:"" };

// Create our reducer that will handle changes to the state
export const dataReducer: Reducer<dataState> =
  (state: dataState = initialState, action: AnyAction): dataState => {
   // alert("dataReducer 작동");
    switch (action.type) {
    case INSERT:
      return Object.assign({}, state, { code: action.diff });
      case INSERT_AUTH:
      return Object.assign({}, state, { menuAuth: action.diff });
      case INSERT_USER:
      return Object.assign({}, state, { empCode: action.diff });
    default:
      return state;
    }
  };
