import { systemPostLineSteps } from "./testPost";
import { customTestSteps } from "./testSteps";

export const postLineSteps = [systemPostLineSteps, customTestSteps].flat(
  Infinity
) as any;
