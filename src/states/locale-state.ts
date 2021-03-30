import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import enTranslates from '../i18n/en-EN/hello.json'
import ruTranslates from '../i18n/ru-RU/tr.json'
import { RootState } from './root-state'

export interface Language {
    title: string,
    locale: string,
    translates: any
}

export interface LocaleState {
    current: Language
    languages: Language[]
}

const ruLang: Language = {
    title: "Русский",
    locale: "ru-RU",
    translates: ruTranslates
}

const enLang: Language = {
    title: "English",
    locale: "en-EN",
    translates: enTranslates
}

const initialState: LocaleState = {
    current: ruLang,
    languages: [
        ruLang,
        enLang
    ]
}

export const localeSlice = createSlice({
        name: 'language',
        initialState,
        reducers: {
            changeLanguage: (state, action: PayloadAction<Language>) => {
                state.current = action.payload
            }
        }
    }
)

export const { changeLanguage } = localeSlice.actions

export const currentLocale = (state: RootState): Language => state.locale.current
export const supportedLanguages = (state: RootState): Language[] => state.locale.languages

export default localeSlice.reducer
