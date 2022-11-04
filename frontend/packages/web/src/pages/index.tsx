import { useHeader } from "@/hooks";

const HomePage = () => {
  useHeader({ mode: "MAIN", headingText: undefined });
  return <div>Home</div>;
};
export default HomePage;
