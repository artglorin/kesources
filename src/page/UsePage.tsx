import { useContext } from 'react';
import { useLocation } from 'react-router';
import { ArgsContext } from '../components/ComponentFactory';

export const usePage = () => {
    const id = useLocation().pathname
    return id ? id : '/home'
}

export const useArg = (argName: string): any => {
    return (
        useContext(ArgsContext) as any
    )[argName]
}
