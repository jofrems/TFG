import { useAppSelector } from "www/store/hooks";
import { isLoading } from "./selectors";

export function LoadingWidget() {
  const loading = useAppSelector(isLoading);
  if (!loading) return null;

  return <div data-testid="loading">(Loading...)</div>;
}
