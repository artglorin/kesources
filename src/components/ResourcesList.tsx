import { FC } from 'react';
import { Container, ListGroup, Nav } from 'react-bootstrap';
import { Route } from 'react-router';
import { usePage } from '../page/UsePage';

export const ResourcesList: FC = (): JSX.Element => {
    const page = usePage()
    return (
        <Container>
            <h1>Ресурсы</h1>
            <ListGroup  variant="flush">
                <ListGroup.Item active={page === '/resources/namespace'} href={"/resources/namespace"}>
                    Namespace
                    {/*<Route path={"#resources/namespace"}>Namespace</Route>*/}
                </ListGroup.Item>
                <ListGroup.Item active={page === '/resources/role'} href={"/resources/role"}>Role</ListGroup.Item>

                {/*<ListGroup.Item>*/}
                {/*    <Route path={"#resources/deploy"}>Deploy</Route>*/}
                    {/*<Nav.Link active={page === '/resources/namespace'} href={"#resources/namespace"}>Namespace</Nav.Link>*/}
                {/*</ListGroup.Item>*/}
                {/*<ListGroup.Item >*/}
                {/*    <Route path={"#resources/role"}>Role</Route>*/}
                    <ListGroup.Item active={page === '/resources/deploy'} href={"/resources/deploy"}>Deploy</ListGroup.Item>
                {/*</ListGroup.Item>*/}
                {/*<Nav.Item>*/}
                    {/*<Nav.Link active={page === '/resources/deploy'} href={"#resources/deploy"}>Deploy</Nav.Link><Route>{'resources'}</Route>*/}
                {/*</Nav.Item>*/}
                {/*<Nav.Item>*/}
                    {/*<Nav.Link active={page === '/resources/role'} href={"#resources/role"}>Role</Nav.Link>*/}
                {/*</Nav.Item>*/}
            </ListGroup>
        </Container>


    );
}
