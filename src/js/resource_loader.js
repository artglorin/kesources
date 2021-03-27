export async function getJson(path, onSuccess) {
    getText(path).then((text) => onSuccess(JSON.parse(text)))
}

export async function getText(path) {
    return new Promise((resolve, reject) => {
        let request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (request.status === 200 && request.readyState === 4) {
                resolve(request.responseText);
            } else if (request.status === 400) {
                reject(request)
            }
        };
        request.open("GET", path, true);
        request.send();
    });


}
