import { componentLoadableReducer } from "./LoadableComponent/reducers";
import { componentNamedReducer } from "./NamedComponent/reducers";
import { componentSailReducer } from "./SailComponent/reducers";

export const componentsReducers = {
  componentLoadable: componentLoadableReducer,
  componentNamed: componentNamedReducer,
  componentSail: componentSailReducer,
};
