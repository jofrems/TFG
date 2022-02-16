import { useStyle } from "./useStyle";

const barStyle = {
  backgroundColor: "#888",
  padding: "1rem",
  display: "flex",
};

export function Bar({
  children,
  role = "",
  color = "gray",
}: {
  children: React.ReactNode;
  role?: string;
  color?: string;
}) {
  const style = useStyle({ color }, barStyle);

  return (
    <div role={role} style={style}>
      {children}
    </div>
  );
}
