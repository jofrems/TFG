import { EntityListItem } from "./EntityListItem";

export function EntityList({ entities }: any) {
  return (
    <ul className="list-group">
      {entities.map((entity: any) => (
        <EntityListItem key={entity.id} entity={entity} />
      ))}
    </ul>
  );
}
