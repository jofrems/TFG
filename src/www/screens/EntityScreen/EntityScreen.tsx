import { useAppSelector } from "www/store/hooks";
import { getCurrentScreenId } from "www/widgets/ScreenStackWidget/selectors";
import { getEntityById } from "www/store/game/selectors";
import { PlayerHeader } from "../PlayerScreen/PlayerHeader";
import { GameHeader } from "../GameScreen/GameHeader";
import { EntityComponents } from "./EntityComponents";

export function EntityScreen() {
  const entityId = useAppSelector(getCurrentScreenId)!;
  const entity = useAppSelector((s) => getEntityById(s, { entityId }))!;
  if (!entity) return null;

  return (
    <div>
      <PlayerHeader />
      <GameHeader />
      <main>
        <h1>
          {entity.name} {entity.type} of {entity.owner}
        </h1>
        <EntityComponents entity={entity} />
      </main>
    </div>
  );
}
