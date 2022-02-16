export interface ScreenDefinition {
  name: string;
  id?: string;
}

export type ScreenStackState = ScreenDefinition[];

export const SCREEN_PUSHED = "widgetScreenStack/SCREEN_PUSHED";
export interface ScreenPushedAction {
  type: typeof SCREEN_PUSHED;
  screen: ScreenDefinition;
}

export const SCREEN_POPPED = "widgetScreenStack/SCREEN_POPPED";
export interface ScreenPoppedAction {
  type: typeof SCREEN_POPPED;
}

export type ScreenStackActionTypes = ScreenPushedAction | ScreenPoppedAction;
