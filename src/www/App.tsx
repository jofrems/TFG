import { Provider } from "react-redux";
import { HeaderWidget } from "./widgets/HeaderWidget";
import { MessageWidget } from "./widgets/MessageWidget";
import { ScreenStackWidget } from "./widgets/ScreenStackWidget";

export function App({ store }: { store: any }) {
  return (
    <Provider store={store}>
      <div>
        <HeaderWidget />
        <MessageWidget />
        <ScreenStackWidget />
      </div>
    </Provider>
  );
}
