import { useAppSelector } from "www/store/hooks";
import { getPlayerName } from "www/store/player/selectors";


export function ElementsComponent({ entity }: any) {
  const currentPlayer = useAppSelector(getPlayerName);
  if(entity.owner !== currentPlayer || entity.type !== 'inventory') return null;
  return <>
        <div> Elements: </div>
        <div>&emsp;Roads: {entity.roads} 🛤</div>
        <div>&emsp;Towns: {entity.towns} 🏘</div>
        <div>&emsp;Cities: {entity.cities} 🏙</div>
        </>
}
