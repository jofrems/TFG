export type ComponentTradeState = null;

export const TRADE = "componentTrade/TRADE";
export interface TradeAction {
  type: typeof TRADE;
  entityId: string;
  form:{
    givenResources: string,
    getResources: string,
  }
}

export type ComponentTradeActionTypes = TradeAction;
