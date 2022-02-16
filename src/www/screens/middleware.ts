import { blogMiddleware } from "./BlogScreen/middleware";
import { postMiddleware } from "./PostScreen/middleware";

export const screensMiddlewares = [
  //
  blogMiddleware,
  postMiddleware,
];
