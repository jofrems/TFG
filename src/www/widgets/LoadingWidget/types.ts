export type LoadingState = number;

export const LOADING_STARTED = "widgetLoading/STARTED";
export interface LoadingStartedAction {
  type: typeof LOADING_STARTED;
}

export const LOADING_FINISHED = "widgetLoading/FINISHED";
export interface LoadingFinishedAction {
  type: typeof LOADING_FINISHED;
}

export type LoadingActionTypes = LoadingStartedAction | LoadingFinishedAction;
