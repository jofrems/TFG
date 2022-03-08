import { HexComponent } from "./HexComponent";


export function HexGeneratorComponent({ entities }: any) {
    return (
        <svg height="800" width="100%" viewBox="0 0 800 600">
            {entities.map((entity: any) => (
                <HexComponent key={entity.id} entity={entity} />
            ))}
        </svg>
    );
  }