import { configureStore } from '@reduxjs/toolkit';
import localeReducer from './locale-state'

export interface AppState {
    locale: string
}

export const RootStore = configureStore({
    reducer: {
        locale: localeReducer
    }
})

export type RootState = ReturnType<typeof RootStore.getState>

export type RootDispatch = typeof RootStore.dispatch
