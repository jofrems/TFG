import path from "path";
import fs from "fs";

export function readResource(folder: string, name: string) {
  const fullName = path.join(
    __dirname,
    "..",
    "..",
    "..",
    "..",
    "target",
    "classes",
    folder,
    name
  );

  const content = fs.readFileSync(fullName, { encoding: "utf8" });
  return content;
}
