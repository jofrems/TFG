import { TradeAction, TRADE } from "./types";

export function trade(
  entityId: string,
  givenResources: string,
  getResources: string,
): TradeAction {
  return {
    type: TRADE,
    entityId,
    form:{
      givenResources,
      getResources,
    },
  };
}
