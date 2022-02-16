import { createSelector } from "reselect";
import { makeGetAllGameEntities } from "www/store/game/selectors";

function getPropLocation(state: any, { location }: { location: number }) {
  return location;
}

export function makeGetDockByLocation() {
  const getAllGameEntities = makeGetAllGameEntities();

  return createSelector(
    getAllGameEntities,
    getPropLocation,
    (entities: any[], location) => {
      return entities.find((e) => e.isDock && e.location === location);
    }
  );
}
