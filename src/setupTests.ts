// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom
import "@testing-library/jest-dom";
import { testGetStore } from "./www/__test__/testStore";

// Setup the front-end
import { render } from "@testing-library/react";
import React from "react";
import { App } from "./www/App";

beforeEach(() => {
  const store = testGetStore();
  render(React.createElement(App, { store }));
});
