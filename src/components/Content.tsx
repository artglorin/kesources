import { FC } from 'react';
import { usePage } from '../page/UsePage';
import { useComponent } from './ComponentFactory';

export const Content: FC = (): JSX.Element => {
    const page = usePage()
    console.info("CONTENT for page %", page)
    const C = useComponent(`page.${page}`)
    return <C/>;
}
