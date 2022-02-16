import {
  ScreenPoppedAction,
  ScreenPushedAction,
  SCREEN_POPPED,
  SCREEN_PUSHED,
} from "./types";

export function screenPushed(name: string, id?: string): ScreenPushedAction {
  return {
    type: SCREEN_PUSHED,
    screen: {
      name,
      id,
    },
  };
}

export function screenPopped(): ScreenPoppedAction {
  return {
    type: SCREEN_POPPED,
  };
}
