import { useDispatchForm, useInputRef } from "www/store/hooks";
import { sailOrdered, haltOrdered } from "./actions";

export function SailComponent({ entity }: any) {
  const destinationLocationRef = useInputRef();
  const sail = useDispatchForm(sailOrdered, entity.id, destinationLocationRef);
  const halt = useDispatchForm(haltOrdered, entity.id);
  if (!entity.isSail) return null;

  return (
    <div>
      {entity.destinationSail && (
        <div>Destination location: {entity.destinationLocation} (sailing).</div>
      )}
      <label>
        Destination location:
        <br />
        <input type="number" ref={destinationLocationRef} />
      </label>
      <br />
      <button onClick={sail}>Sail</button> <button onClick={halt}>Halt</button>
    </div>
  );
}
