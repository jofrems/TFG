import { MutableRefObject, useCallback, useRef } from "react";
import { TypedUseSelectorHook, useDispatch, useSelector } from "react-redux";
import type { AppState, AppDispatch } from "./";

// Use throughout your app instead of plain `useDispatch` and `useSelector`
export const useAppDispatch = () => useDispatch<AppDispatch>();
export const useAppSelector: TypedUseSelectorHook<AppState> = useSelector;

export const useInputRef = () => useRef<null | HTMLInputElement>(null);
export const useDispatchForm = (
  actionCreator: Function,
  ...refs: (string | MutableRefObject<null | HTMLInputElement>)[]
) => {
  var dispatch = useAppDispatch();
  return useCallback(() => {
    const values = refs.map((ref) =>
      typeof ref === "string" ? ref : ref.current?.value
    );
    dispatch(actionCreator(...values));
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [dispatch, actionCreator, ...refs]);
};
