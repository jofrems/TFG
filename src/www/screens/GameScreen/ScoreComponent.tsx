import { EntityComponents } from "../EntityScreen/EntityComponents";



export function ScoreComponent({ entities }: any) {
    return (
        <div>
            {entities.map((entity: any) => {
                if(entity.type === "score"){
                    return <EntityComponents key={entity.id} entity={entity} />
                }
                return <></>
            })}
        </div>  
    );
  }