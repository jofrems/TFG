import { useDispatchForm, useInputRef } from "www/store/hooks";
import { loggedIn } from "www/store/player/actions";

export function LoginScreen() {
  const playerNameRef = useInputRef();
  const passwordRef = useInputRef();
  const login = useDispatchForm(loggedIn, playerNameRef, passwordRef);

  return (
    <main>
      <h1>Log In!</h1>

      <label>
        Player name:
        <br />
        <input ref={playerNameRef} />
      </label>
      <br />
      <br />

      <label>
        Password:
        <br />
        <input ref={passwordRef} type="password" />
      </label>
      <br />
      <br />

      <button onClick={login}>Login</button>
    </main>
  );
}
