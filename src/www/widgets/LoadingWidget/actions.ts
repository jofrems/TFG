import {
  LoadingFinishedAction,
  LoadingStartedAction,
  LOADING_FINISHED,
  LOADING_STARTED,
} from "./types";

export function loadingStarted(): LoadingStartedAction {
  return {
    type: LOADING_STARTED,
  };
}

export function loadingFinished(): LoadingFinishedAction {
  return {
    type: LOADING_FINISHED,
  };
}
