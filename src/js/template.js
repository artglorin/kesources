import {getText} from "./resource_loader.js";

export async function template(ref, templateName) {
    getText("../html/" + templateName + ".html")
        .then((text) => {
        ref.innerHTML = text
    }).catch((e) => console.error(e))
}
