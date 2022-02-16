import { useMemo } from "react";

const bgColors: any = {
  white: "#eee",
  lightgray: "#ddd",
  gray: "#888",
  success: "lightblue",
  danger: "#e58370",
};

export function useStyle({ color }: { color: string }, customStyle: any) {
  return useMemo(() => {
    const result: any = { ...customStyle };

    result.backgroundColor = bgColors[color];
    return result;
  }, [color, customStyle]);
}
