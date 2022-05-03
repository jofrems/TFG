import { useMemo } from "react";
import { makeGetAllGameEntities } from "www/store/game/selectors";
import { useAppSelector } from "www/store/hooks";
import { PlayerHeader } from "../PlayerScreen/PlayerHeader";
import { EntityList } from "../EntityScreen/EntityList";
import { GameHeader } from "./GameHeader";
import { HexGeneratorComponent } from "./HexGeneratorComponent";
import { DiceComponent} from "./DiceComponent";
import { ScoreComponent} from "./ScoreComponent";


export function GameScreen() {
  const getAllGameEntities = useMemo(makeGetAllGameEntities, []);
  const entities = useAppSelector(getAllGameEntities);
  console.log("entities");
 
  return (
    <>
      <PlayerHeader color="white" />
      <GameHeader />
      <main>
        <h1>Catan</h1>
        <HexGeneratorComponent entities={entities}/>
        <DiceComponent entities={entities}/>
        <ScoreComponent entities = {entities}/>
        <div>
        <h4>Inventory:</h4>
          <EntityList entities={entities} />
        </div>
      </main>
    </>
  );
}
