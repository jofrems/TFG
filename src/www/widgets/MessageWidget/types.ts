export type MessageState = null | {
  text: string;
  isError: boolean;
};

export const MESSAGE_SHOWN = "messageWidget/SHOWN";
export interface MessageShownAction {
  type: typeof MESSAGE_SHOWN;
  message: {
    text: string;
    isError: boolean;
  };
}

export const MESSAGE_DISMISSED = "messageWidget/DISMISSED";
export interface MessageDismissedAction {
  type: typeof MESSAGE_DISMISSED;
}

export type MessageActionTypes = MessageShownAction | MessageDismissedAction;
