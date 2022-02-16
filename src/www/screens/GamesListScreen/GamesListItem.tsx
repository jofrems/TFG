import { useCallback, useMemo } from "react";
import { gameJoined, gamePlayed } from "www/store/game/actions";
import { GamesListItem as GLI } from "www/store/gamesList/types";
import { useAppDispatch, useAppSelector } from "www/store/hooks";
import { getPlayerName } from "www/store/player/selectors";

export function GamesListItem({ item }: { item: GLI }) {
  const dispatch = useAppDispatch();
  const join = useCallback(
    () => dispatch(gameJoined(item.gameName, item.creatorName)),
    [dispatch, item]
  );
  const play = useCallback(
    () => dispatch(gamePlayed(item.gameName, item.creatorName)),
    [dispatch, item]
  );
  const currentPlayer = useAppSelector(getPlayerName)!;
  const isPlaying = useMemo(
    () => item.joinedPlayerNames.includes(currentPlayer),
    [item, currentPlayer]
  );

  return (
    <li>
      Game {item.gameName} by {item.creatorName}
      {isPlaying && " (joined) "}
      <button onClick={join}>Join</button>
      <button onClick={play}>Play</button>
    </li>
  );
}
