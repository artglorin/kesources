

let state = { test: 1 };
const listeners = [];

export function subscribeState(callback) {
    callback(state);
    listeners.push(callback);
}

function notifyListeners() {
    listeners.forEach(it => {
        it(state)
    });
}

export function changeState(newState) {
    console.debug("Change state", state, newState)
    Object.assign(state, newState);
    console.debug("Merged state", state)
    notifyListeners()
}
