import { FC } from 'react';
import { Container, Form, Nav, Navbar } from 'react-bootstrap';
import { useTranslate } from '../Localization';
import { LocaleSelector } from './LocaleSelector';

export const Header: FC = (): JSX.Element => {
    const resources = useTranslate('resources')
    const database = useTranslate('database')
    const appName = useTranslate("appName")
    return (
        <Container className={"bg-primary text-white"}>
            <Navbar expand="lg" variant='dark'>
                <Navbar.Brand href="#home">{appName}</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="#resources">{resources}</Nav.Link>
                    <Nav.Link href="#database">{database}</Nav.Link>
                </Nav>
                <Nav className={"position-absolute  end-0"}>
                    <LocaleSelector/>
                </Nav>
            </Navbar>
        </Container>
    );
}
