import { PostLineStep, step } from "../testPost";

export const backendTestSteps: PostLineStep[] = [
  step(/there is your player/, () => {}),
  step(/there is the player "[^"]+"( with password "[^"]+")?/, () => {}),
  step(/"[^"]+" has created the game "[^"]+"/, () => {}),
  step(/you have created the game "[^"]+"/, () => {}),
  step(/there is a game "[^"]+" created by "[^"]+"/, () => {}),
  step(/there are players "[^"]+"/, () => {}),
  step(/"[^"]+" has joined the game "[^"]+" created by "[^"]+"/, () => {}),
  step(/There should be 37 tiles/, () => {}),
  step(/There should be 54 vertex/, () => {}),
  step(/The "vertex-1" should not have an owner/, () => {}),
  step(/Own the vertex./, () => {}),
  step(/"leonard" should own "vertex-1"/, () => {}),

];
