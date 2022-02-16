import { AddNextPlayerScreen } from "./AddNextPlayerScreen";
import { CreateGameScreen } from "./CreateGameScreen";
import { BlogScreen } from "./BlogScreen";
import { EntityScreen } from "./EntityScreen/EntityScreen";
import { GameScreen } from "./GameScreen/GameScreen";
import { GamesListScreen } from "./GamesListScreen";
import { LoginScreen } from "./LoginScreen";
import { PlayerScreen } from "./PlayerScreen";
import { PostScreen } from "./PostScreen";
import { SignUpScreen } from "./SignUpScreen";
import { WelcomeScreen } from "./WelcomeScreen";

export const screenMap: { [screenName: string]: Function } = {
  addNextPlayer: AddNextPlayerScreen,
  blog: BlogScreen,
  createGame: CreateGameScreen,
  entity: EntityScreen,
  game: GameScreen,
  gamesList: GamesListScreen,
  login: LoginScreen,
  player: PlayerScreen,
  post: PostScreen,
  signup: SignUpScreen,
  welcome: WelcomeScreen,
};
