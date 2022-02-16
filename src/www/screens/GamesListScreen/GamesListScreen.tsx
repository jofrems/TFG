import { useCallback } from "react";
import { getGamesList } from "www/store/gamesList/selectors";
import { useAppDispatch, useAppSelector } from "www/store/hooks";
import { screenPopped } from "www/widgets/ScreenStackWidget/actions";
import { GamesListItem } from "./GamesListItem";

export function GamesListScreen() {
  const list = useAppSelector(getGamesList);
  const dispatch = useAppDispatch();
  const cancel = useCallback(() => dispatch(screenPopped()), [dispatch]);

  return (
    <main>
      <h1>Games List!</h1>
      <ul>
        {list?.map((item) => (
          <GamesListItem
            key={item.gameName + "#" + item.creatorName}
            item={item}
          />
        ))}
      </ul>
      <button onClick={cancel}>Cancel</button>
    </main>
  );
}
