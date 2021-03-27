import {subscribeState} from "./state.js"
import {getJson} from "./resource_loader.js";

function text(ref, locale, file, key) {

    getJson("../i18n/" + locale + "/" + file + ".json", (json) => {
        ref.childNodes
            .forEach(
                (it) => {
                    it.remove()
                }
            )
        ref.append(json[key])
    })
}

export function localize(ref, file, key) {
    subscribeState((state) => {
        let locale = state.locale
        if (locale == null) {
            locale = "ru-RU"
        }
        text(ref, locale, file, key)
    });
}
