import { useAppSelector } from "www/store/hooks";
import { getPlayerName } from "www/store/player/selectors";

export function ScoreComponent({ entity }: any) {
  const currentPlayer = useAppSelector(getPlayerName);
  if(entity.owner !== currentPlayer || entity.type !== 'score') return null;
  return <>
        <div> <h4>Score:</h4> </div>
        <div>&emsp;Points: {entity.points}</div>
        </>
}
