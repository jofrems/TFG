import { useMemo } from "react";
import { makeGetAllGameEntities } from "www/store/game/selectors";
import { useAppSelector } from "www/store/hooks";
import { PlayerHeader } from "../PlayerScreen/PlayerHeader";
import { EntityList } from "../EntityScreen/EntityList";
import { GameHeader } from "./GameHeader";
import { EntityComponents } from "../EntityScreen/EntityComponents";
import { getEntityById } from "www/store/game/selectors";
import { HexGeneratorComponent } from "./HexGeneratorComponent";



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
        <HexGeneratorComponent entities={entities}/>
        <EntityList entities={entities} />
        <div>
        <h4>Inventory:</h4>
            <EntityComponents entity={entity} />
        </div>
      </main>
    </>
  );
}
