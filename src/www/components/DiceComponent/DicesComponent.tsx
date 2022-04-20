import { roll } from "./actions";
import { useDispatchForm } from "www/store/hooks";


export function DicesComponent({ entity }: any) {
  const rolls = useDispatchForm(roll, entity.id, "roll");
  if(entity.type !== 'dice') return null; 

  return <>
        <div> <h4>Dices:</h4> </div>
        <div>&emsp;Dice 1: {entity.dice1} 🎲</div>
        <div>&emsp;Dice 2: {entity.dice2} 🎲</div>
        <button onClick={rolls}>Roll</button>

        </>
}
