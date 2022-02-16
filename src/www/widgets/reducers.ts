import { widgetLoadingReducer } from "./LoadingWidget/reducers";
import { widgetMessageReducer } from "./MessageWidget/reducers";
import { widgetScreenStackReducer } from "./ScreenStackWidget/reducers";

export const widgetsReducers = {
  widgetLoading: widgetLoadingReducer,
  widgetMessage: widgetMessageReducer,
  widgetScreenStack: widgetScreenStackReducer,
};
