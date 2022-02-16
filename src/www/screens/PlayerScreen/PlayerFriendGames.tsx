import { gamesByCreatorListed } from "www/store/gamesList/actions";
import { useDispatchForm, useInputRef } from "www/store/hooks";

export function PlayerFriendGames() {
  const friendNameRef = useInputRef();
  const goFriendGames = useDispatchForm(gamesByCreatorListed, friendNameRef);

  return (
    <div>
      <label>
        Friend name:
        <br />
        <input ref={friendNameRef} />
      </label>
      <br />
      <button onClick={goFriendGames}>Friend games</button>
    </div>
  );
}
