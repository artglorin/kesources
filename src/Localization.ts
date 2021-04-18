import { useSelector } from 'react-redux';
import { translatesLog } from './LoggingSettings';
import { currentLocale } from './states/locale-state';

export function useTranslate(key: string): any {
    let result: any = translate(key, useSelector(currentLocale).translates);
    if (result instanceof Function) {
        result = result()
    }
    return result
}

export function translate(key: string, translates: any): any {
    translatesLog.debug(`translate by key = ${key}`)
    translatesLog.trace(`translations: ${translates}`)
    const keys = key.split(".")
    let value: any | null = translates
    for (const key1 of keys) {
        translatesLog.trace(`key: ${key}, cursor: ${value}`)
        value = value[key1]
        translatesLog.trace(`translate: ${value}`)
        if (value == null) {
            break
        }
    }
    translatesLog.debug(`translation result = ${value}`)
    return value ?? "undefined"
}
