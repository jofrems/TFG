const frozens = new WeakSet();

function freezeState(state: any) {
  if (!frozens.has(state)) {
    Object.keys(state).forEach((k) => {
      const val = state[k];
      if (val && typeof val === "object") {
        state[k] = freezeState(val);
      }
    });

    Object.freeze(state);
    frozens.add(state);
  }
  return state;
}

export function freezeReducer(reduce: any): any {
  return function (state: any, action: any) {
    const nextState = reduce(state, action);
    return freezeState(nextState);
  };
}
