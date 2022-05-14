import { componentOwnMiddleware } from "./OwnComponent/middleware";
import { componentDiceMiddleware } from "./DiceComponent/middleware";
import { componentTradeMiddleware } from "./tradeComponent/middleware";


export const componentsMiddlewares = [
  //
  componentOwnMiddleware,
  componentDiceMiddleware,
  componentTradeMiddleware,
];
