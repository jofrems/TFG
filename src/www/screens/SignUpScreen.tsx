import { useDispatchForm, useInputRef } from "www/store/hooks";
import { signedUp } from "www/store/player/actions";

export function SignUpScreen() {
  const playerNameRef = useInputRef();
  const passwordRef = useInputRef();
  const singUp = useDispatchForm(signedUp, playerNameRef, passwordRef);

  return (
    <main>
      <h1>Sign up!</h1>

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
        <input
          ref={passwordRef}
          type="password"
          autoComplete="current-password"
        />
      </label>

      <br />
      <br />
      <button onClick={singUp}>Signup</button>
    </main>
  );
}
