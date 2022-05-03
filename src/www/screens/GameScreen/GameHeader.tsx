import { gameRefreshed, roundEnded } from "www/store/game/actions";
import { getGame } from "www/store/game/selectors";
import { useAppSelector } from "www/store/hooks";
import { ButtonDispatch } from "www/widgets/ButtonDispatchWidget";
import { LinkDispatch } from "www/widgets/LinkDispatchWidget";
import { screenPopped } from "www/widgets/ScreenStackWidget/actions";
import { getCurrentScreenName } from "www/widgets/ScreenStackWidget/selectors";
import { Bar } from "www/theme/Bar";
import { Spacer } from "www/theme/Spacer";

export function GameHeader() {
  const isGameScreen = useAppSelector(getCurrentScreenName) === "game";
  const game = useAppSelector(getGame)!;
  const { gameName, creatorName, roundNumber, playerNameTurn} = game;
  console.log("ronda: " + roundNumber+" ,torn: " + playerNameTurn);
  return (
    <Bar color="lightgray">
      {!isGameScreen && (
        <>
          <LinkDispatch createAction={screenPopped}>« Back</LinkDispatch>
          &nbsp;|&nbsp;
        </>
      )}{" "}
      <span data-testid="game-header">
        Game {gameName} created by {creatorName}, round {roundNumber}, Turn of {playerNameTurn}
      </span>
      <Spacer />
      <ButtonDispatch createAction={roundEnded}>End Round</ButtonDispatch>&nbsp;
      <ButtonDispatch createAction={gameRefreshed}>Refresh</ButtonDispatch>
    </Bar>
  );
}
