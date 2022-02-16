import { useCallback } from "react";
import { useAppDispatch, useAppSelector } from "www/store/hooks";
import { messageDismissed } from "./actions";
import { getMessage } from "./selectors";
import { Bar } from "www/theme/Bar";
import { Spacer } from "www/theme/Spacer";

export function MessageWidget() {
  const dispatch = useAppDispatch();
  const dismiss = useCallback(() => dispatch(messageDismissed()), [dispatch]);
  const message = useAppSelector(getMessage);
  if (!message) return null;

  return (
    <Bar role="alert" color={message.isError ? "danger" : "success"}>
      {message.isError && "⚠ "} {message.text}
      <Spacer />
      <button onClick={dismiss}>dismiss</button>
    </Bar>
  );
}
