import { createStore, applyMiddleware } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import { storeReducer } from "./storeReducer";
import { storeMiddlewares } from "./storeMiddlewares";
import { freezeReducer } from "./freezeReducer";

export function createAppStore() {
  const store = createStore(
    freezeReducer(storeReducer),
    composeWithDevTools(applyMiddleware(...storeMiddlewares))
  );

  return store;
}

export type AppStore = ReturnType<typeof createAppStore>;
export type AppDispatch = AppStore["dispatch"];
export type AppActionType = Parameters<AppStore["dispatch"]>[0];
export type AppState = ReturnType<typeof storeReducer>;
