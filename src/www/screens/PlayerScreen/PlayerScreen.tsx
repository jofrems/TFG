import {
  myCreatedGamesListed,
  myPlayingGamesListed,
} from "www/store/gamesList/actions";
import { LinkTo } from "www/widgets/LinkToWidget";
import { LinkDispatch } from "www/widgets/LinkDispatchWidget";
import { PlayerHeader } from "./PlayerHeader";
import { PlayerFriendGames } from "./PlayerFriendGames";

export function PlayerScreen() {
  return (
    <div>
      <PlayerHeader />
      <main>
        <h1>Welcome player!</h1>
        <LinkTo name="createGame">Create game</LinkTo>
        <br />
        <br />
        List games:
        <br />
        <LinkDispatch createAction={myPlayingGamesListed}>
          - My playing games »
        </LinkDispatch>
        <br />
        <LinkDispatch createAction={myCreatedGamesListed}>
          - My created games »
        </LinkDispatch>
        <br />
        <br />
        <PlayerFriendGames />
      </main>
    </div>
  );
}
