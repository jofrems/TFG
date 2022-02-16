import { useCallback } from "react";
import { AppActionType } from "www/store";
import { useAppDispatch } from "www/store/hooks";

export function LinkDispatch({
  createAction,
  children,
}: {
  createAction: () => AppActionType;
  children: React.ReactNode;
}) {
  const dispatch = useAppDispatch();
  const go = useCallback(
    () => dispatch(createAction()),
    [dispatch, createAction]
  );

  return (
    <span role="link" onClick={go}>
      {children}
    </span>
  );
}
