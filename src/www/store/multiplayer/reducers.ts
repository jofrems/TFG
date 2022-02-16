import {
  MultiplayerActionTypes,
  MultiplayerState,
  NextPlayerChangedAction,
  NextPlayerPushedAction,
  NEXT_PLAYER_CHANGED,
  NEXT_PLAYER_PUSHED,
} from "./types";

const initialState: MultiplayerState = {
  currentPlayerIndex: 0,
  players: [],
};

export function multiplayerReducer(
  state: MultiplayerState = initialState,
  action: MultiplayerActionTypes
) {
  switch (action.type) {
    case NEXT_PLAYER_CHANGED:
      return reduceNextPlayerChanged(state, action);
    case NEXT_PLAYER_PUSHED:
      return reduceNextPlayerPushed(state, action);
    default:
      return state;
  }
}

function reduceNextPlayerChanged(
  state: MultiplayerState,
  action: NextPlayerChangedAction
) {
  return {
    ...state,
    currentPlayerIndex: (state.currentPlayerIndex + 1) % state.players.length,
  };
}

function reduceNextPlayerPushed(
  state: MultiplayerState,
  action: NextPlayerPushedAction
) {
  const { players } = state;

  const newPlayers = [...players];
  const isAlreadyPresent = players.some(
    (p) => p.playerName === action.player.playerName
  );
  if (!isAlreadyPresent) {
    newPlayers.push(action.player);
  }

  return {
    ...state,
    players: newPlayers,
    currentPlayerIndex: newPlayers.length - 1,
  };
}
