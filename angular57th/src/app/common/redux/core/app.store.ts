import { InjectionToken } from '@angular/core';
import {
  createStore,
  Store,

 } from 'redux';

import {
  counterReducer as reducer
} from './counter.reducer';
import { AppState } from './app.state';

export const AppStore = new InjectionToken('App.aaa');


export function createAppStore(): Store<AppState> {
//  alert("AppStore의createAppStore함수 작동");
  return createStore<AppState,null,null,null>(
    reducer,(window as any).__REDUX_DEVTOOLS_EXTENSION__ &&
    (window as any).__REDUX_DEVTOOLS_EXTENSION__())
}

export const appStoreProviders = [
   { provide: AppStore, useFactory: createAppStore }
];
