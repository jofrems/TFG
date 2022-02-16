import { useCallback } from "react";
import { useAppDispatch, useDispatchForm, useInputRef } from "www/store/hooks";
import { nextPlayerAdded } from "www/store/multiplayer/actions";
import { screenPopped } from "www/widgets/ScreenStackWidget/actions";

export function AddNextPlayerScreen() {
  const playerNameRef = useInputRef();
  const passwordRef = useInputRef();
  const add = useDispatchForm(nextPlayerAdded, playerNameRef, passwordRef);
  const dispatch = useAppDispatch();
  const cancel = useCallback(() => dispatch(screenPopped()), [dispatch]);

  return (
    <main>
      <h1>Add next player!</h1>
      <label>
        Next player name:
        <br />
        <input ref={playerNameRef} />
      </label>
      <br />
      <br />
      <label>
        Next player password:
        <br />
        <input ref={passwordRef} type="password" />
      </label>
      <br />
      <br />
      <button onClick={add}>Join Next</button>{" "}
      <button onClick={cancel}>Cancel</button>
    </main>
  );
}
