import { Dropdown, DropdownButton } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { useTranslate } from '../Localization';
import { changeLanguage, supportedLanguages } from '../states/locale-state';

export const LocaleSelector = (): JSX.Element => {
    const supportLanguages = useSelector(supportedLanguages)
    const dispatcher = useDispatch()
    const localization = useTranslate("localization")
    return <DropdownButton title={localization}>
        <Dropdown.Menu>
            {supportLanguages
                .map(language =>
                    <Dropdown.Item
                        onClick={_ => dispatcher(changeLanguage(language))}
                    >{language.title}
                    </Dropdown.Item>
                )
            }
        </Dropdown.Menu>
    </DropdownButton>
}
