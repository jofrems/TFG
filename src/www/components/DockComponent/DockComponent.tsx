import { useMemo } from "react";
import { EntityList } from "www/screens/EntityScreen/EntityList";
import { useAppSelector } from "www/store/hooks";
import { makeGetAllCoLocatedById } from "../LocatedComponent/selectors";

export function DockComponent({ entity }: any) {
  const getAllCoLocatedById = useMemo(makeGetAllCoLocatedById, []);
  const coLocateds = useAppSelector((state) =>
    getAllCoLocatedById(state, { entityId: entity.id })
  );
  if (!entity.isDock) return null;

  return (
    <div>
      Dockeds:
      <EntityList entities={coLocateds} />
    </div>
  );
}
