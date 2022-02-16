import { useCallback } from "react";
import { AppActionType } from "www/store";
import { useAppDispatch } from "www/store/hooks";

export function ButtonDispatch({
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

  return <button onClick={go}>{children}</button>;
}
