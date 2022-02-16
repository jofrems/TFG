import { useAppSelector } from "www/store/hooks";
import { nextPlayerChanged } from "www/store/multiplayer/actions";
import { getPlayer } from "www/store/player/selectors";
import { LinkDispatch } from "www/widgets/LinkDispatchWidget";
import { Bar } from "www/theme/Bar";
import { Spacer } from "www/theme/Spacer";

export function PlayerHeader({ color = "lightgray" }: { color?: string }) {
  const player = useAppSelector(getPlayer);
  if (!player) return null;

  return (
    <Bar color={color}>
      <span data-testid="player-header">Player {player.playerName}.</span>
      <Spacer />
      <LinkDispatch createAction={nextPlayerChanged}>Next</LinkDispatch>
    </Bar>
  );
}
