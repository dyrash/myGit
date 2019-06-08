import { InjectionToken } from '@angular/core';
import {
  createStore,
  Store

 } from 'redux';
 import * as InsertActions from 'src/app/common/redux/core/codedata.action';

import {
    dataReducer as reducer
} from './code.reducer';
import { dataState } from './code.state';


export const dataStore = new InjectionToken('dataStore.aaa');


export function createDataStore(): Store<dataState> {
  //alert("codeStore의createDataStore함수 작동");
  return createStore<dataState,null,null,null>(
    reducer,(window as any).__REDUX_DEVTOOLS_EXTENSION__ &&
    (window as any).__REDUX_DEVTOOLS_EXTENSION__())
}

export const datStoreProviders = [
   { provide: dataStore, useFactory: createDataStore }
];
