import { screenMap } from "www/screens";
import { useAppSelector } from "www/store/hooks";
import { getCurrentScreenName } from "./selectors";

export function ScreenStackWidget() {
  const screenName = useAppSelector(getCurrentScreenName) as any;
  const Screen = screenMap[screenName];
  if (!Screen) return <>`Screen "${screenName}" not found.`</>;

  return <Screen />;
}
