import thunkMiddleware from "redux-thunk";
import { componentsMiddlewares } from "www/components/middleware";
import { screensMiddlewares } from "www/screens/middleware";
import { gameMiddleware } from "./game/middleware";
import { gamesListMiddleware } from "./gamesList/middleware";
import { multiplayerMiddleware } from "./multiplayer/middleware";
import { playerMiddleware } from "./player/middleware";

export const storeMiddlewares = [
  thunkMiddleware,
  ...componentsMiddlewares,
  ...screensMiddlewares,
  // Add other middleware after this line
  gameMiddleware,
  gamesListMiddleware,
  multiplayerMiddleware,
  playerMiddleware,
];
