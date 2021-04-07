import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import { useLocation } from 'react-router';
import enTranslates from '../i18n/en-EN/hello.json'
import * as ruTranslates from '../i18n/ru-RU/tr'
import { PageContent } from '../page/PageContent';
import { RootState } from './root-state'

export interface PageState {
    current: PageContent
}

const initialState: PageState = {
    current: {
        title: "",
        description : ""
    }
}

export const pageSlice = createSlice({
        name: 'page',
        initialState,
        reducers: {
            changePage: (state, action: PayloadAction<string>) => {
                state.current = {
                    title: "",
                    description: ""
                }
            }
        }
    }
)
//
// export const { changeLanguage } = localeSlice.actions
//
// export const currentLocale = (state: RootState): Language => state.locale.current
// export const supportedLanguages = (state: RootState): Language[] => state.locale.languages
//
// export default localeSlice.reducer
