import { useSelector } from 'react-redux';
import { currentLocale } from './states/locale-state';

export function useTranslate(key: string): string {
    const translates = useSelector(currentLocale).translates
    console.debug("translate", translates)
    const keys = key.split(".")
    let value: any | null = translates
    for (const key1 of keys) {
        console.debug("key", key1)
        console.debug("cursor", value)
        value = value[key1]
        console.debug("value", value)
        if (value == null) {
            break
        }
    }
    return value ?? "undefined"
}
