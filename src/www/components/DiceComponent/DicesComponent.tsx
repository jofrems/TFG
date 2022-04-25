import { roll } from "./actions";
import { useAppSelector, useDispatchFormBig } from "www/store/hooks";
import { getPlayerName } from "www/store/player/selectors";


export function DicesComponent({ entity }: any) {
  const currentPlayer = useAppSelector(getPlayerName);

  const rolls = useDispatchFormBig(roll, entity.id, currentPlayer);
  if(entity.type !== 'dice') return null; 

  return <>
        <div> <h4>Dices:</h4> </div>
        <div>&emsp;Dice 1: {entity.dice1} 🎲</div>
        <div>&emsp;Dice 2: {entity.dice2} 🎲</div>
        <button onClick={rolls}>Roll</button>

        </>
}
