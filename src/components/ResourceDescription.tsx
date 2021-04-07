import { FC } from 'react';
import { Card } from 'react-bootstrap';
import { useTranslate } from '../Localization';

export const ResourceDescription: FC<string> = (resource): JSX.Element => {
    const content = useTranslate(resource)
    return (
        <Card>
            <Card.Title>{content.title}</Card.Title>
            <Card.Body>{content.description}</Card.Body>
        </Card>
    )
}
