import { useCallback } from "react";
import { useAppDispatch } from "www/store/hooks";
import { screenPushed } from "www/widgets/ScreenStackWidget/actions";

export function LinkTo({
  name,
  id,
  children,
}: {
  name: string;
  id?: string;
  children: React.ReactChild;
}) {
  const dispatch = useAppDispatch();
  const go = useCallback(
    () => dispatch(screenPushed(name, id)),
    [dispatch, name, id]
  );

  return (
    <span role="link" onClick={go}>
      {children}
    </span>
  );
}
