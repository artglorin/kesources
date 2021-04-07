import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import { Provider } from 'react-redux';
import { HashRouter } from 'react-router-dom';
import './App.css';
import { useComponent } from './components/ComponentFactory';
import { Content } from './components/Content';
import { RootStore } from './states/root-state';

function App() {
    const Header = useComponent("header")
    const ResourcesList = useComponent("resourceList")
    return (
        <HashRouter>
            <Provider store={RootStore}>
                <Header/>
                <Container>
                    <Row>
                        <Col className="col-3">
                            <ResourcesList/>
                        </Col>
                        <Col><Content/>{'s'}</Col>
                    </Row>
                </Container>
            </Provider>
        </HashRouter>
    );
}

export default App;
