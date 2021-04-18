import { createContext, FC, useContext } from 'react';
import { Alert } from 'react-bootstrap';
import { Header } from './Header';
import { ResourcesList } from './ResourcesList';

export const ArgsContext = createContext({})

const ComponentNotDefined: FC = (): JSX.Element => {
    const args = useContext(ArgsContext) as any
    return (
        <Alert variant={'danger'}> {`Component by ib = '${args['componentId']}' not found.`}</Alert>
    );
}

export function useComponent(componentId: string, context: any = null): FC {

    switch (componentId) {
        case "header":
            return Header
        case 'resourceList':
            return ResourcesList;
    }
    context = context ?? {};
    context['componentId'] = componentId;
    return () => <ArgsContext.Provider value={context}>
        <ComponentNotDefined/>
    </ArgsContext.Provider>;
}
