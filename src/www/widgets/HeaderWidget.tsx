import { LinkTo } from "./LinkToWidget";
import { LinkDispatch } from "./LinkDispatchWidget";
import { LoadingWidget } from "./LoadingWidget";
import { Bar } from "www/theme/Bar";
import { Spacer } from "www/theme/Spacer";
import { blogPushed } from "www/screens/BlogScreen/actions";

export function HeaderWidget() {
  return (
    <Bar>
      <LoadingWidget />
      Els Colonitzadors de l'Illa
      <Spacer />
      <LinkTo name="welcome">Welcome</LinkTo>
      <span>&nbsp;|&nbsp;</span>
      <LinkDispatch createAction={blogPushed}>Blog</LinkDispatch>
      <Spacer />
      <LinkTo name="login">Login</LinkTo>
      <span>&nbsp;|&nbsp;</span>
      <LinkTo name="addNextPlayer">Multiplayer</LinkTo>
    </Bar>
  );
}
