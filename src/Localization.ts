import React, { useContext } from 'react';
import * as loc from './i18n/en-EN/hello.json'

export const defaultLocale = { locale: loc };

export const LocaleContext = React.createContext(defaultLocale)

export function useTranslate(key: string): string {
    let cursor = useContext(LocaleContext).locale as any
    console.log("translate", key)
    const keys = key.split(".")
    let value: any | null = cursor["default"]
    for (const key1 of keys) {
        console.log("key", key1)
        console.log("cursor", value )
        value = value[key1]
        console.log("value", value)
        if (value == null) {
            break
        }
    }
    return value ?? "undefined"
}
