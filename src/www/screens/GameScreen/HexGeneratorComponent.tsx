import { HexComponent } from "./HexComponent";
import { VertexComponent } from "./VertexComponent";
import { RoadComponent } from "./RoadComponent";


export function HexGeneratorComponent({ entities }: any) {
    return (
        <svg height="800" width="100%" viewBox="0 0 1280 720">
            {entities.map((entity: any) => {
                if(entity.type === "tile")
                    return <HexComponent key={entity.id} entity={entity} />
                else if(entity.type ==="vertex")
                    return <VertexComponent key={entity.id} entity={entity}  />

                return <></>
            })}

            {entities.map((entity: any) => {
                if(entity.type ==="road")
                    return <RoadComponent key={entity.id} entity={entity}  />

                return <></>
            })}
        </svg>
    );
  }