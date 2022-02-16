import { createSelector } from "reselect";
import {
  makeGetAllGameEntities,
  getEntityById,
} from "www/store/game/selectors";

function getPropEntityId(state: any, { entityId }: { entityId: string }) {
  return entityId;
}

export function makeGetAllCoLocatedById() {
  const getAllGameEntities = makeGetAllGameEntities();

  return createSelector(
    getAllGameEntities,
    getEntityById,
    getPropEntityId,
    (entities: any[], currentEntity: any, entityId) => {
      const { location } = currentEntity;
      const coLocateds = entities.filter(
        (e) => e.location === location && e.id !== entityId
      );
      return coLocateds;
    }
  );
}
