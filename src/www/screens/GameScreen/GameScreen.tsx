import { useMemo } from "react";
import { makeGetAllGameEntities } from "www/store/game/selectors";
import { useAppSelector } from "www/store/hooks";
import { PlayerHeader } from "../PlayerScreen/PlayerHeader";
import { EntityList } from "../EntityScreen/EntityList";
import { GameHeader } from "./GameHeader";
import { ElementsComponent } from "www/components/ElementsComponent";
import { ResourcesComponent } from "www/components/ResourcesComponent";
import { EntityComponents } from "../EntityScreen/EntityComponents";
import { getEntityById } from "www/store/game/selectors";


export function GameScreen() {
  const getAllGameEntities = useMemo(makeGetAllGameEntities, []);
  const entities = useAppSelector(getAllGameEntities);
  const entityId = entities[0].id;
  const entity = useAppSelector((s) => getEntityById(s, { entityId }))!;
  if (!entity) return null;

  //console.log(entity);

  return (
    <>
      <PlayerHeader color="white" />
      <GameHeader />
      <main>
        <h1>Catan</h1>
        <EntityList entities={entities} />
        <div>
        <h4>Inventory:</h4>
            <EntityComponents entity={entity} />
        </div>
      </main>
    </>
  );
}
