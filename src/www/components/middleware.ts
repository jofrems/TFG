import { componentOwnMiddleware } from "./OwnComponent/middleware";
import { componentDiceMiddleware } from "./DiceComponent/middleware";


export const componentsMiddlewares = [
  //
  componentOwnMiddleware,
  componentDiceMiddleware,
];
