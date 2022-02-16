import { componentLoadableMiddleware } from "./LoadableComponent/middleware";
import { componentNamedMiddleware } from "./NamedComponent/middleware";
import { componentSailMiddleware } from "./SailComponent/middleware";

export const componentsMiddlewares = [
  //
  componentLoadableMiddleware,
  componentNamedMiddleware,
  componentSailMiddleware,
];
