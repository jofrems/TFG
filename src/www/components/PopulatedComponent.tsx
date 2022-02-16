export function PopulatedComponent({ entity }: any) {
  if (!entity.isPopulated) return null;
  return <div>Population: {entity.population}</div>;
}
