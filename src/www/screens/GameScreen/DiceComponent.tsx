import { EntityComponents } from "../EntityScreen/EntityComponents";



export function DiceComponent({ entities }: any) {
    return (
        <div>
            {entities.map((entity: any) => {
                if(entity.type === "dice"){
                    return <EntityComponents key={entity.id} entity={entity} />
                }
                return <></>
            })}
        </div>  
    );
  }