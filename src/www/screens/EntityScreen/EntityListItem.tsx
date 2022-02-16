import { useCallback } from "react";
import { useAppDispatch } from "www/store/hooks";
import { screenPushed } from "www/widgets/ScreenStackWidget/actions";

export function EntityListItem({ entity }: any) {
  const dispatch = useAppDispatch();
  const go = useCallback(
    () => dispatch(screenPushed("entity", entity.id)),
    [dispatch, entity]
  );

  return (
    <li onClick={go}>
      {entity.type}: {entity.name} of {entity.owner}
    </li>
  );
}
