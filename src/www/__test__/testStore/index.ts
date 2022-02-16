import { createAppStore, AppStore, AppActionType } from "www/store";

let store: AppStore;
beforeEach(() => {
  store = createAppStore();
});

export function testGetStore() {
  return store;
}

export function testDispatch(action: AppActionType) {
  store.dispatch(action);
}

export function testGetState() {
  return store.getState();
}
