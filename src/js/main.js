import {changeState} from "./state.js";
import {localize} from "./i18n.js";
import {template} from "./template.js"

window.onload = () => {
    // htmlDivElement.append("HELLO")
    main()
}


function main() {
    let target = document.createElement("div");
    document.body.appendChild(target)
    let p = document.createElement("p");
    document.body.appendChild(p)
    localize(target, "hello", "hello");
    localize(p, "hello", "test");

    let list = document.createElement("div");
    document.body.appendChild(list).onload = () => {}
    template(list,"list")
        .then(() => {

            let observer = new MutationObserver(mutationRecords => {
                let localeBtn = document.getElementById("locale-btn");
                if (localeBtn != null) {
                    localeBtn.onclick = () => {
                        changeState({locale: 'en-En', test: 2})
                        list.append(document.createElement("div"))
                    };
                    observer.disconnect()
                }
            });

// наблюдать за всем, кроме атрибутов
            observer.observe(list, {
                childList: true, // наблюдать за непосредственными детьми
                subtree: true, // и более глубокими потомками
                characterDataOldValue: true // передавать старое значение в колбэк
            });

            // let listener = (e) => {
            //
            // }
            // document.addEventListener("DOMNodeInserted", listener)

            // setTimeout(()=> {
            //     console.log("list child count %", list.childNodes.length)
            //
            // }, 300)
    }).catch((e) => console.error(e));
}
