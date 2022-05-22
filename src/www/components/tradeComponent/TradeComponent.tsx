import { useAppSelector , useInputRef, useDispatchForm} from "www/store/hooks";
import { getPlayerName } from "www/store/player/selectors";
import { trade } from "./actions";


export function TradeComponent({ entity }: any) {
  const currentPlayer = useAppSelector(getPlayerName);
  const givenResourcesRef = useInputRef();
  const getResourcesRef = useInputRef();

  
 
  const trading = useDispatchForm(
    trade,
    entity.id,
    givenResourcesRef,
    getResourcesRef
  );
  

  if(entity.owner !== currentPlayer || entity.type !== 'inventory') return null;
  
  return <>
          <div> Trades:</div>
          <div>&emsp;
            <label>
              Give 4 Resource of type:
              <input type="string" ref={givenResourcesRef} />
            </label>
            <label>
              Get 1 Resource of type:
              <input type="string" ref={getResourcesRef} />
            </label>
          </div>
         
          <button onClick={trading}>
            Trade
          </button>

        </>
}
