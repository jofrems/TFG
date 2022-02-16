import { useMemo } from "react";
import { useAppSelector, useDispatchForm, useInputRef } from "www/store/hooks";
import { loadOrdered, unloadOrdered } from "./actions";
import { makeGetDockByLocation } from "../DockComponent/selectors";

export function LoadableComponent({ entity }: any) {
  const newLoadUnloadAmountRef = useInputRef();
  const getDockByLocation = useMemo(makeGetDockByLocation, []);
  const sourceEntityId = useAppSelector((state) =>
    getDockByLocation(state, entity)
  )?.id;
  const load = useDispatchForm(
    loadOrdered,
    entity.id,
    sourceEntityId,
    newLoadUnloadAmountRef
  );
  const unload = useDispatchForm(
    unloadOrdered,
    entity.id,
    sourceEntityId,
    newLoadUnloadAmountRef
  );

  if (!entity.isLoadable) return null;

  return (
    <>
      <div>Load/unload amount: {entity.loadUnloadAmount}</div>
      <label>
        Load/unload amount:
        <input type="number" ref={newLoadUnloadAmountRef} />
      </label>
      <button onClick={load} disabled={entity.loadRequested}>
        Load
      </button>
      <button onClick={unload} disabled={entity.unloadRequested}>
        Unload
      </button>
    </>
  );
}
