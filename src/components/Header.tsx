import { FC } from 'react';
import { useTranslate } from '../Localization';

export const Header:FC = (): JSX.Element => {
    const greet = useTranslate('greet')
    const name = useTranslate('name')
    return <div className={"container container-fluid"}
    > {greet + " " + name + "!"}</div>
}
