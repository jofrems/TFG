import { componentOwnReducer } from "./OwnComponent/reducers";
import { componentDiceReducer } from "./DiceComponent/reducers";
import { componentTradeReducer } from "./tradeComponent/reducers";


export const componentsReducers = {
  componentOwn: componentOwnReducer,
  componentDice: componentDiceReducer,
  componentTrade: componentTradeReducer,


};
