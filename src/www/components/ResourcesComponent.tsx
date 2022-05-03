import { useAppSelector } from "www/store/hooks";
import { getPlayerName } from "www/store/player/selectors";

export function ResourcesComponent({ entity }: any) {
  const currentPlayer = useAppSelector(getPlayerName);
  if(entity.owner !== currentPlayer || entity.type !== 'inventory') return null;
  return <>
        <div> Resources: </div>
        <div>&emsp;Lumber: {entity.lumber} 🪵</div>
        <div>&emsp;Brick: {entity.brick} 🧱</div>
        <div>&emsp;Wool: {entity.wool} 🐑</div>
        <div>&emsp;Grain: {entity.grain} 🌾</div>
        <div>&emsp;Ore: {entity.ore} 🪨</div>
        </>
}
