import { FC } from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { useTranslate } from '../Localization';
import { useArg } from '../page/UsePage';

export const Header: FC = (): JSX.Element => {
    let dbLink
    if (useArg('withDB')) {
        dbLink = (
            <Nav.Link href={'#database'}>База данных</Nav.Link>
        )
    }
    const appName = useTranslate('appName')
    console.info('appName' , typeof appName)
    return (
        <Container className={"bg-primary text-white"}>
            <Navbar expand="lg" variant='dark'>
                <Navbar.Brand href="#">{appName}</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="#resources">Ресурсы</Nav.Link>
                    {dbLink}
                </Nav>
                {/*<Nav className={"position-absolute  end-0"}>*/}
                {/*    <LocaleSelector/>*/}
                {/*</Nav>*/}
            </Navbar>
        </Container>
    );
}
