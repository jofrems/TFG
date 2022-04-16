import { getPlayerName } from "www/store/player/selectors";
import { useAppSelector } from "www/store/hooks";
import { EntityComponents } from "./EntityComponents";


export function EntityListItem({ entity }: any) { 

  const currentPlayer = useAppSelector(getPlayerName);
  if(currentPlayer !== entity.owner || entity.type !== 'inventory'){
        return null;
  }
  return (
     <EntityComponents entity={entity}/>
  );
}
